package com.frenchies.tennisclub.facade;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.frenchies.tennisclub.dto.BookingCreateDTO;
import com.frenchies.tennisclub.dto.BookingDTO;
import com.frenchies.tennisclub.dto.UserDTO;
import com.frenchies.tennisclub.enums.Hour24;
import com.frenchies.tennisclub.facade.BookingFacade;
import com.frenchies.tennisclub.facade.UserFacade;
import com.frenchies.tennisclub.service.config.ServiceConfiguration;

@ContextConfiguration(classes = ServiceConfiguration.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class BookingFacadeTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private BookingFacade bookingFacade;

	@Autowired
	private UserFacade userFacade;

	private BookingCreateDTO bookingCreateDTO;

	private BookingDTO bookingDTO;

	private UserDTO userDTO1;
	private UserDTO userDTO2;

	private Calendar cal1;

	@BeforeClass
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	// @BeforeMethod
	// public void userDTOCreation() {
	//
	// }

	@BeforeMethod
	public void prepareTestBooking() {
		cal1 = Calendar.getInstance();
		cal1.set(1999, 11, 10);

		userDTO1 = new UserDTO();
		userDTO1.setDateOfBirth(cal1.getTime());
		userDTO1.setEmail("jean.françois@mail.com");
		userDTO1.setName("François");
		userDTO1.setSurname("jean");
		userDTO1.setPhone("+33720362718");

		userDTO2 = new UserDTO();
		userDTO2.setDateOfBirth(cal1.getTime());
		userDTO2.setEmail("paul.pierre@mail.com");
		userDTO2.setName("Pierre");
		userDTO2.setSurname("Paul");
		userDTO2.setPhone("+33728962718");

		cal1 = Calendar.getInstance();
		cal1.set(2017, 1, 1);
		Date date1 = cal1.getTime();

		userFacade.createUser(userDTO1, "blabla");
		userFacade.createUser(userDTO2, "blabla2");

		bookingCreateDTO = new BookingCreateDTO();
		bookingCreateDTO.setIdCourt((long) 1);
		bookingCreateDTO.setDateOfBooking(date1);
		bookingCreateDTO.setHourOfBooking(Hour24.EIGHT);
		bookingCreateDTO.setUser1(userDTO1);
		bookingCreateDTO.setUser2(userDTO2);

		bookingDTO = new BookingDTO();
	}

	@Test
	public void createBookingTest() {
		Long bookingId = bookingFacade.createBooking(bookingCreateDTO);
		Assert.assertTrue((bookingFacade.getBookingById(bookingId).getIdCourt()).equals(bookingCreateDTO.getIdCourt()));
		Assert.assertTrue((bookingFacade.getBookingById(bookingId).getDateOfBooking())
				.equals(bookingCreateDTO.getDateOfBooking()));
		Assert.assertTrue((bookingFacade.getBookingById(bookingId).getHourOfBooking())
				.equals(bookingCreateDTO.getHourOfBooking()));
	}

	@Test
	public void deleteBookingTest() {
		Long bookingId = bookingFacade.createBooking(bookingCreateDTO);
		bookingFacade.deleteBooking(bookingId);
		Assert.assertTrue((bookingFacade.getBookingById(bookingId)).equals(null));
	}

	@Test
	public void getAllBookingTest() {
		Long bookingId = bookingFacade.createBooking(bookingCreateDTO);
		List<BookingDTO> bookings = bookingFacade.getAllBookings();
		BookingDTO bookingDTO = bookingFacade.getBookingById(bookingId);
		Assert.assertTrue(bookings.contains(bookingDTO));
	}

	@Test
	public void getBookingsByDateTest() {
		Long bookingId = bookingFacade.createBooking(bookingCreateDTO);
		Assert.assertTrue(bookingFacade.getBookingsByDate(bookingFacade.getBookingById(bookingId).getDateOfBooking())
				.equals(bookingCreateDTO.getDateOfBooking()));
	}

	@Test
	public void getBookingsByUserTest() {
		Long bookingId = bookingFacade.createBooking(bookingCreateDTO);
		bookingDTO.setIdBooking(bookingId);
		Assert.assertTrue(bookingFacade.getBookingsByUser(userDTO1).contains(bookingDTO));
		Assert.assertTrue(bookingFacade.getBookingsByUser(userDTO2).contains(bookingDTO));
	}

	// @Test
	// public void findByCompetitionCountryBookingTest(){
	// Long bookingId = bookingFacade.createBooking(bookingCreateDTO);
	// List<BookingDTO> bookings =
	// bookingFacade.getBookingsByCountry(CompetitionCountry.CZECH_REPUBLIC);
	// BookingDTO bookingDTO = bookingFacade.getBookingById(bookingId);
	// assertThat(bookings).containsExactly(bookingDTO);
	// }

}