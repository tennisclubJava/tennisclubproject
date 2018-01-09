package com.frenchies.tennisclub.mvc.forms;

import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.frenchies.tennisclub.dto.BookingCreateDTO;
import com.frenchies.tennisclub.dto.BookingDTO;
import com.frenchies.tennisclub.dto.UserDTO;
import com.frenchies.tennisclub.facade.BookingFacade;
import com.frenchies.tennisclub.facade.UserFacade;

/**
 * The place for validation checks. Useful for checks involving multiple
 * properties at once. This SpringMVC validation is <b>additional</b> to the
 * JSR-303 validation specified by annotations in CourtCreateDTO.
 *
 * @author Meon Thomas 473349
 */
public class BookingCreateDTOValidator implements Validator {

	private BookingFacade bookingFacade;

	private UserFacade userFacade;
	private int cpt = 0;
	private UserDTO u;


	@Override
	public boolean supports(Class<?> clazz) {
		return BookingCreateDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BookingCreateDTO bookingCreateDTO = (BookingCreateDTO) target;

		if (bookingCreateDTO.getIdCourt() == null)
			return;
		if (bookingCreateDTO.getIdUser1() == null)
			return;
		if (bookingCreateDTO.getDateOfBooking() == null)
			return;
		if (bookingCreateDTO.getHourOfBooking() == null)
			return;

		if (bookingCreateDTO.isLesson() || bookingCreateDTO.isTournament()) {
			bookingCreateDTO.setIdUser1(1L);
			bookingCreateDTO.setIdUser2(1L);
		}

//		List<BookingDTO> ListB = bookingFacade.getAllBookings();
//		for (BookingDTO btemp : ListB) {
//			if (btemp.getDateOfBooking() == bookingCreateDTO.getDateOfBooking()) {
//				if (btemp.getHourOfBooking() == bookingCreateDTO.getHourOfBooking()) {
//					return;
//				}
//			}
//		}
//
//		 u = userFacade.getUserById(bookingCreateDTO.getIdUser1());
//		 List<BookingDTO> ListC = bookingFacade.getBookingsByUser(u);
//		
//		 for (BookingDTO btemp : ListC) {
//		 if (btemp.getDateOfBooking() == bookingCreateDTO.getDateOfBooking()) {
//		 cpt++;
//		 }
//		 }
//		 if (cpt >= 2) {
//		 return;
//		 }


		if (bookingCreateDTO.isLesson() && bookingCreateDTO.getIdUser2() == null) {
			bookingCreateDTO.setIdUser2(1L);
		}
		if (bookingCreateDTO.isTournament() && bookingCreateDTO.getIdUser1() == null
				&& bookingCreateDTO.getIdUser2() == null) {
			bookingCreateDTO.setIdUser1(1L);
			bookingCreateDTO.setIdUser2(1L);
		}

		// if(!testDuplication(bookingCreateDTO))
		// errors.rejectValue("dateOfBooking", "BookingCreateDTOValidator.duplication");

	}

	// public boolean testDuplication(BookingCreateDTO b) {
	// List<BookingDTO> listBookingsSameCourt =
	// bookingFacade.getBookingsByCourt(b.getIdCourt());
	// for(BookingDTO bTemp : listBookingsSameCourt)
	// {
	// if(bTemp.getDateOfBooking()==b.getDateOfBooking()) {
	// if(bTemp.getHourOfBooking()==b.getHourOfBooking()) {
	// return false;
	// }
	//
	// }
	// }
	// return true;
	// }
}
