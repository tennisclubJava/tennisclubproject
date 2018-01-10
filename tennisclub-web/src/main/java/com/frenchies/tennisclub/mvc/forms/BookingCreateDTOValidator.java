package com.frenchies.tennisclub.mvc.forms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.frenchies.tennisclub.dto.BookingCreateDTO;
import com.frenchies.tennisclub.dto.BookingDTO;
import com.frenchies.tennisclub.facade.BookingFacade;

/**
 * The place for validation checks. Useful for checks involving multiple
 * properties at once. This SpringMVC validation is <b>additional</b> to the
 * JSR-303 validation specified by annotations in CourtCreateDTO.
 *
 * @author Meon Thomas 473349
 */
public class BookingCreateDTOValidator implements Validator {

	private BookingFacade bookingFacade;

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

	}
}
