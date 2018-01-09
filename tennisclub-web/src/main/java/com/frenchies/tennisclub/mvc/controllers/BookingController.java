package com.frenchies.tennisclub.mvc.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.frenchies.tennisclub.dto.BookingCreateDTO;
import com.frenchies.tennisclub.dto.BookingDTO;
import com.frenchies.tennisclub.dto.CourtDTO;
import com.frenchies.tennisclub.dto.UserDTO;
import com.frenchies.tennisclub.enums.Hour24;
import com.frenchies.tennisclub.facade.BookingFacade;
import com.frenchies.tennisclub.facade.CourtFacade;
import com.frenchies.tennisclub.facade.UserFacade;
import com.frenchies.tennisclub.mvc.forms.BookingCreateDTOValidator;

/**
 * Web MVC Controller for administering bookings.
 *
 * @author Dore Corentin 473308
 */
@Controller
@RequestMapping("/booking")
public class BookingController {

	final static Logger log = LoggerFactory.getLogger(BookingController.class);

	@Autowired
	private BookingFacade bookingFacade;

	@Autowired
	private CourtFacade courtFacade;
	
	@Autowired
	private UserFacade userFacade;

	/**
	 * Shows a list of bookings with the ability to add, delete or edit.
	 *
	 * @param model
	 *            data to display
	 * @return JSP page name
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("bookings", bookingFacade.getAllBookings());
		return "booking/list";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
			RedirectAttributes redirectAttributes) {
		BookingDTO booking = bookingFacade.getBookingById(id);
		bookingFacade.deleteBooking(id);
		log.debug("delete({})", id);
		redirectAttributes.addFlashAttribute("alert_success",
				"Booking \"" + booking.getIdBooking() + "\" was deleted.");
		return "redirect:" + uriBuilder.path("/booking/list").toUriString();
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable long id, Model model) {
		log.debug("view({})", id);
		model.addAttribute("booking", bookingFacade.getBookingById(id));
		return "booking/view";
	}
	

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable long id, Model model, UriComponentsBuilder uriBuilder,
			RedirectAttributes redirectAttributes) {
		BookingDTO booking = bookingFacade.getBookingById(id);
		bookingFacade.deleteBooking(id);
		log.debug("delete({})", id);
		redirectAttributes.addFlashAttribute("alert_success",
				"Booking \"" + booking.getIdBooking() + "\" is editing.");
		log.debug("new()");
		model.addAttribute("bookingCreate", new BookingCreateDTO());
		return "booking/edit";
	}
	/**
	 * Prepares an empty form.
	 *
	 * @param model
	 *            data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String newBooking(Model model) {
		log.debug("new()");
		model.addAttribute("bookingCreate", new BookingCreateDTO());
		return "booking/new";
	}
	
	/**
	 * Prepares an empty form. for lesson
	 *
	 * @param model
	 *            data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/newLesson", method = RequestMethod.GET)
	public String newBookingLesson(Model model) {
		log.debug("new()");
		BookingCreateDTO b = new BookingCreateDTO();
		model.addAttribute("bookingCreate", b);
		return "booking/newLesson";
	}
	
	/**
	 * Prepares an empty form. for tournament
	 *
	 * @param model
	 *            data to be displayed
	 * @return JSP page
	 */
	@RequestMapping(value = "/newTournament", method = RequestMethod.GET)
	public String newBookingTournament(Model model) {
		log.debug("new()");
		BookingCreateDTO b = new BookingCreateDTO();
		model.addAttribute("bookingCreate", b);
		return "booking/newTournament";
	}
	
	@ModelAttribute("users")
	public List<UserDTO> users() {
		log.debug("users()");
		return userFacade.getAllUsers();
	}
	
	@ModelAttribute("hour24")
	public Hour24[] hour24() {
		log.debug("hour24()");
		return Hour24.values();
	}

	@ModelAttribute("courts")
	public List<CourtDTO> courts() {
		log.debug("courts()");
		return courtFacade.getAllCourts();
	}

	

	/**
	 * Spring Validator added to JSR-303 Validator for this @Controller only. It is
	 * useful for custom validations that are not defined on the form bean by
	 * annotations.
	 * http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		if (binder.getTarget() instanceof BookingCreateDTO) {
			binder.addValidators(new BookingCreateDTOValidator());
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat, false));  
		}
		

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("bookingCreate") BookingCreateDTO formBean, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes, UriComponentsBuilder uriBuilder) {
		log.debug("create(bookingCreate={})", formBean);
		// in case of validation error forward back to the the form
		if (bindingResult.hasErrors()) {
			for (ObjectError ge : bindingResult.getGlobalErrors()) {
				log.trace("ObjectError: {}", ge);
			}
			for (FieldError fe : bindingResult.getFieldErrors()) {
				model.addAttribute(fe.getField() + "_error", true);
				log.trace("FieldError: {}", fe);
			}
			return "booking/new";
		}
		// create booking
		Long id = bookingFacade.createBooking(formBean);
		// report success
		redirectAttributes.addFlashAttribute("alert_success", "Booking " + id + " was created");
		return "redirect:" + uriBuilder.path("/booking/view/{id}").buildAndExpand(id).encode().toUriString();
	}
}