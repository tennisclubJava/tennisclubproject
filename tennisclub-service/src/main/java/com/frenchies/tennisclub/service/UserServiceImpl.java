package com.frenchies.tennisclub.service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frenchies.tennisclub.dao.UserDao;
import com.frenchies.tennisclub.entity.User;

/**
 * 
 * @author Corentin Dore 473308
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User registerUser(User user, String unencryptedPassword) throws IllegalArgumentException {
		if (!isValidUser(user)) {
            throw new IllegalArgumentException(UserServiceImpl.class +
                    " - User argument is not valid.");
        }
		user.setPasswordHash(createHash(unencryptedPassword));
		userDao.create(user);
		return user;
	}
	
	@Override
	public void update(User user) throws IllegalArgumentException {
		if (!isValidUser(user)) {
            throw new IllegalArgumentException(UserServiceImpl.class +
                    " - User argument is not valid.");
        }
		userDao.update(user);
	}
	
	@Override
	public void delete(User user) throws IllegalArgumentException {
		if (user == null){
            throw new IllegalArgumentException(UserServiceImpl.class +
                    " - Null User.");
        }
        if (userDao.findById(user.getId()) == null) {
            throw new IllegalArgumentException(UserServiceImpl.class +
                    " - Not found User: " + user);
        }
		userDao.remove(user);
	}

	@Override
	public List<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public boolean authenticate(User u, String password) {
		return validatePassword(password, u.getPasswordHash());
	}

	@Override
	public boolean isAdmin(User u) {
		// must get a fresh copy from database
		return getUserById(u.getId()).isAdmin();
	}
	
	@Override
	public boolean isAdmin(Long id) {
		// must get a fresh copy from database
		return getUserById(id).isAdmin();
	}
	
	@Override
	public boolean isTeacher(User u) {
		// must get a fresh copy from database
		return getUserById(u.getId()).isTeacher();
	}
	
	@Override
	public boolean isTeacher(Long id) {
		// must get a fresh copy from database
		return getUserById(id).isTeacher();
	}

	@Override
	public User getUserById(Long userId) {
		if (userId == null) {
            throw new IllegalArgumentException(UserServiceImpl.class +
                    " - Passed id paramenter is null");
        }
		return userDao.findById(userId);
	}

	@Override
	public User getUserByName(String name) {
		if (name == null) {
            throw new IllegalArgumentException(UserServiceImpl.class +
                    " - Name paramenter is null");
        }
		return userDao.findUserByName(name);
	}
	
	@Override
	public User getUserByEmail(String email) {
		if (email == null) {
            throw new IllegalArgumentException(UserServiceImpl.class +
                    " - Name paramenter is null");
        }
		return userDao.findUserByEmail(email);
	}

		
	 
	// see https://crackstation.net/hashing-security.htm#javasourcecode
	private static String createHash(String password) {
		final int SALT_BYTE_SIZE = 24;
		final int HASH_BYTE_SIZE = 24;
		final int PBKDF2_ITERATIONS = 1000;
		// Generate a random salt
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		random.nextBytes(salt);
		// Hash the password
		byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
		// format iterations:salt:hash
		return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
		try {
			PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
			return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static boolean validatePassword(String password, String correctHash) {
		if (password == null)
			return false;
		if (correctHash == null)
			throw new IllegalArgumentException("password hash is null");
		String[] params = correctHash.split(":");
		int iterations = Integer.parseInt(params[0]);
		byte[] salt = fromHex(params[1]);
		byte[] hash = fromHex(params[2]);
		byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
		return slowEquals(hash, testHash);
	}

	/**
	 * Compares two byte arrays in length-constant time. This comparison method is
	 * used so that password hashes cannot be extracted from an on-line system using
	 * a timing attack and then attacked off-line.
	 *
	 * @param a
	 *            the first byte array
	 * @param b
	 *            the second byte array
	 * @return true if both byte arrays are the same, false if not
	 */
	private static boolean slowEquals(byte[] a, byte[] b) {
		int diff = a.length ^ b.length;
		for (int i = 0; i < a.length && i < b.length; i++)
			diff |= a[i] ^ b[i];
		return diff == 0;
	}

	private static byte[] fromHex(String hex) {
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length; i++) {
			binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return binary;
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
	}
	
	private boolean isValidUser(User user) {
        if (user == null) {
            return false;
        }
        String name = user.getName();
        if (name == null || name.length() == 0) {
            return false;
        }
        String surname = user.getSurname();
        if (surname == null || surname.length() == 0) {
            return false;
        }
        String mail = user.getMail();
        if (mail == null || mail.length() == 0) {
            return false;
        }
        String phone = user.getPhone();
        if (phone == null || phone.length() == 0) {
            return false;
        }
        Date dateOfBirth = user.getDateOfBirth();
        if (dateOfBirth == null) {
            return false;
        }
        return true;
    }

}
