package com.frenchies.tennisclub.sampledata;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.frenchies.tennisclub.entity.Booking;
import com.frenchies.tennisclub.entity.Court;
import com.frenchies.tennisclub.entity.User;
import com.frenchies.tennisclub.enums.CourtType;
import com.frenchies.tennisclub.enums.Hour24;
import com.frenchies.tennisclub.enums.Status;
import com.frenchies.tennisclub.service.BookingService;
import com.frenchies.tennisclub.service.CourtService;
import com.frenchies.tennisclub.service.UserService;

/**
 * Loads some sample data to populate the database.
 *
 * @author Meon Thomas 473449
 */

@Component
@Transactional // transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

	final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

	@Inject
	private UserService userService;
	@Inject
	private CourtService courtService;
	@Inject
	private BookingService bookingService;

	@SuppressWarnings("unused")
	public void loadData() throws IOException {
		Calendar cal = Calendar.getInstance();
		cal.set(2017, 1, 1);
		Date date1 = cal.getTime();
		cal.set(2017, 2, 2);
		Date date2 = cal.getTime();
		cal.set(2017, 3, 2);
		Date date3 = cal.getTime();
		cal.set(2017, 4, 2);
		Date date4 = cal.getTime();
		cal.set(2017, 5, 2);
		Date date5 = cal.getTime();
		cal.set(2017, 12, 2);
		Date date6 = cal.getTime();
		cal.set(2017, 12, 25);
		Date date7 = cal.getTime();
		cal.set(2017, 1, 5);
		Date date8 = cal.getTime();
		cal.set(2018, 1, 8);
		Date date9 = cal.getTime();
		cal.set(2010, 1, 18);
		Date date10 = cal.getTime();
		cal.set(1980, 11, 2);
		Date date11 = cal.getTime();
		cal.set(1980, 2, 3);
		Date date12 = cal.getTime();
		cal.set(1980, 3, 3);
		Date date13 = cal.getTime();
		cal.set(1980, 4, 3);
		Date date14 = cal.getTime();
		cal.set(1980, 5, 3);
		Date date15 = cal.getTime();
		cal.set(1980, 6, 3);
		Date date16 = cal.getTime();
		cal.set(1980, 7, 3);
		Date date17 = cal.getTime();
		cal.set(1980, 8, 3);
		Date date18 = cal.getTime();
		cal.set(1980, 9, 3);
		Date date19 = cal.getTime();
		cal.set(1980, 10, 3);
		Date date20 = cal.getTime();
		cal.set(1980, 11, 3);
		Date date21 = cal.getTime();

		Court court1 = court(CourtType.GRASS, Status.AVAILABLE, 5, 4);
		Court court2 = court(CourtType.CLAY, Status.AVAILABLE, 4, 8);
		Court court3 = court(CourtType.HARD, Status.AVAILABLE, 7, 8);
		Court court4 = court(CourtType.GRASS, Status.AVAILABLE, 9, 9);
		log.info("Loaded court.");

		User admin = user("admin", "admin", "Admin", "admin@admin.com", "611775389", date21, false);
		User HanSolo = user("hanshotsfirst", "Han", "Solo", "millenium@falcon.com", "603123456", date12, false);
		User LukeSkywalker = user("heismyfather", "Luke", "Skywlaker", "love@porgs.com", "656738925", date13, true);
		User Chewbacca = user("arrgh", "Chewbacca", "LastWookie", "chewie@arrgh.com", "678093677", date14, false);
		User ObiwanKenobi = user("badfeeling", "Obiwan", "Kenobi", "hello@there.com", "609874815", date15, true);
		User R2D2 = user("bip", "R2", "D2", "C3@PO.com", "612345678", date16, false);
		User BenSolo = user("papy4ever", "Ben", "Solo", "ilove@mydad.com", "634171189", date17, false);
		User Palpatine = user("pleiguis", "Senator", "Palpatine", "order@66.com", "688663091", date18, true);
		User JarJarBinks = user("clumpsy", "JarJar", "Binks", "missa@naboo.com", "609800053", date19, false);
		User BobaFeet = user("carbonite", "Boba", "Feet", "sarlac@jetpack.com", "690748920", date20, false);
		log.info("Loaded users.");

		Booking b1 = booking(date1, 1L, Hour24.EIGHT, HanSolo, LukeSkywalker, false, false);
		Booking b2 = booking(date2, 1L, Hour24.SEVEN, Palpatine, JarJarBinks, false, false);
		Booking b3 = booking(date3, 2L, Hour24.SIXTEEN, ObiwanKenobi, BobaFeet, false, false);
		Booking b4 = booking(date4, 1L, Hour24.FOURTEEN, BenSolo, HanSolo, false, false);
		Booking b5 = booking(date5, 4L, Hour24.TWELVE, Chewbacca, R2D2, false, false);
		Booking b6 = booking(date6, 3L, Hour24.TEN, Palpatine, LukeSkywalker, false, false);
		Booking b7 = booking(date7, 1L, Hour24.ELEVEN, JarJarBinks, Chewbacca, false, false);
		Booking b8 = booking(date8, 2L, Hour24.TWENTY_ONE, BenSolo, ObiwanKenobi, false, false);
		Booking b9 = booking(date6, 3L, Hour24.EIGHT, Palpatine, admin, true, false);
		Booking b10 = booking(date6, 1L, Hour24.EIGHT, LukeSkywalker, admin, true, false);
		Booking b11 = booking(date6, 2L, Hour24.SIX, admin, admin, false, true);
		log.info("Loaded bookings.");

	}

	private Booking booking(Date dateOfBooking, Long idCourt, Hour24 hourOfBooking, User user1, User user2,
			boolean lesson, boolean tournament) throws IOException {
		Booking b = new Booking();
		b.setDateOfBooking(dateOfBooking);
		b.setHourOfBooking(hourOfBooking);
		b.setUser1(user1);
		b.setUser2(user2);
		b.setIdCourt(idCourt);
		b.setLesson(lesson);
		b.setTournament(tournament);
		bookingService.createBooking(b);
		return b;
	}

	private Court court(CourtType type, Status status, float longitude, float latitude) {
		Court c = new Court();
		c.setType(type);
		c.setStatus(status);
		c.setLongitude(longitude);
		c.setLatitude(latitude);
		courtService.createCourt(c);
		return c;
	}

	private User user(String password, String name, String surname, String email, String phone, Date dateOfBirth, boolean teacher) {
		User u = new User();
		u.setName(name);
		u.setSurname(surname);
		u.setMail(email);
		u.setPhone(phone);
		u.setDateOfBirth(dateOfBirth);
		if (password.equals("admin"))
			u.setAdmin(true);
		else
			u.setAdmin(false);
		u.setTeacher(teacher);
		userService.registerUser(u, password);
		return u;
	}
}