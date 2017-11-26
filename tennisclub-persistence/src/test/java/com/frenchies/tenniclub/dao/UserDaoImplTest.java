package com.frenchies.tenniclub.dao;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.frenchies.tennisclub.PersistenceSampleApplicationContext;
import com.frenchies.tennisclub.dao.UserDao;
import com.frenchies.tennisclub.entity.User;

/**
 * Test of UserDaoImpl
 * 
 * @author ValentinJacquet 473362
 *
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserDaoImplTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserDao UserDao;

	private User u1;
	private User u2;
	private User u3;
	private Calendar cal1;

	@BeforeTest
	public void init() {
		cal1 = Calendar.getInstance();
		cal1.set(1999, 11, 10);
	}

	@Test
	public void testCreate() {
		create1People();

		UserDao.create(u1);

		Assert.assertEquals(UserDao.findById(u1.getId()), u1);
	}

	@Test
	public void testUpdate() {
		create1People();

		UserDao.create(u1);
		u1.setName("Jacques Patrick");
		;
		UserDao.update(u1);

		Assert.assertEquals(UserDao.findById(u1.getId()).getName(), u1.getName());
	}

	@Test
	public void testRemove() {
		create1People();

		UserDao.create(u1);

		Assert.assertEquals(UserDao.findById(u1.getId()), u1);

		UserDao.remove(u1);

		Assert.assertNull(UserDao.findById(u1.getId()));
	}

	@Test
	public void testFindById() {
		create2People();

		UserDao.create(u1);
		UserDao.create(u2);

		Assert.assertEquals(UserDao.findById(u1.getId()), u1);
		Assert.assertEquals(UserDao.findById(u2.getId()), u2);
	}
	
	@Test
	public void testFindByName() {
		create2People();

		UserDao.create(u1);
		UserDao.create(u2);

		Assert.assertEquals(UserDao.findUserByName(u1.getName()), u1);
		Assert.assertEquals(UserDao.findUserByName(u2.getName()), u2);
	}

	@Test
	public void testFindAll() {
		create3People();

		UserDao.create(u1);
		UserDao.create(u2);
		UserDao.create(u3);

		Assert.assertEquals(UserDao.findAll().size(), 3);
		Assert.assertTrue(UserDao.findAll().contains(u1));
		Assert.assertTrue(UserDao.findAll().contains(u2));
		Assert.assertTrue(UserDao.findAll().contains(u3));
	}

	public void create1People() {

		u1 = new User();
		u1.setDateOfBirth(cal1.getTime());
		u1.setMail("jacques.henry@mail.com");
		u1.setName("Henry");
		u1.setSurname("Jacques");
		u1.setPasswordHash("blabla");
		u1.setPhone("+33625362718");
		u1.setAdmin(false);
	}

	public void create2People() {
		create1People();

		u2 = new User();
		u2.setDateOfBirth(cal1.getTime());
		u2.setMail("jean.pierre@mail.com");
		u2.setName("Pierre");
		u2.setSurname("Jean");
		u2.setPasswordHash("blabla");
		u2.setPhone("+33725362718");
		u2.setAdmin(false);
	}

	public void create3People() {
		create2People();

		u3 = new User();
		u3.setDateOfBirth(cal1.getTime());
		u3.setMail("mathieu.françois@mail.com");
		u3.setName("François");
		u3.setSurname("Mathieu");
		u3.setPasswordHash("blabla");
		u3.setPhone("+33729362718");
		u3.setAdmin(false);
	}
	
	/**
	 * Just helper method to create a valid user
	 * 
	 * @return
	 */
	public static User getSimpleUser() {
		User user = new User();
		user.setDateOfBirth(new Date(12091998));
		user.setMail("jean.françois@mail.com");
		user.setName("François");
		user.setSurname("jean");
		user.setPasswordHash("blabla");
		user.setPhone("+33720362718");
		user.setAdmin(false);
		return user;
	}

	public static User getSimpleUser2() {
		User user = new User();
		user.setDateOfBirth(new Date(12091988));
		user.setMail("jean.louis@mail.com");
		user.setName("Louis");
		user.setSurname("Jean");
		user.setPasswordHash("blabla");
		user.setPhone("+33725398718");
		user.setAdmin(false);
		return user;
	}
}
