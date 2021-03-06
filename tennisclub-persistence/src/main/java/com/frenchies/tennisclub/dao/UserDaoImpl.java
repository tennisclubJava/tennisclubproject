package com.frenchies.tennisclub.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.frenchies.tennisclub.entity.User;

/**
 * Implemantation of User Dao
 * 
 * @author Meon Thomas 473449
 *
 */
@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public void create(User u) throws IllegalArgumentException {
		if (u == null) {
            throw new IllegalArgumentException("User is null.");
        }
		em.persist(u);
	}

	@Override
	public User findUserByName(String name) {
		if (name == null || name.isEmpty())
			throw new IllegalArgumentException("Cannot search for null name");

		try {
			return em.createQuery("select u from User u where name=:name", User.class).setParameter("name", name)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	

	@Override
	public User findUserByEmail(String email) {
		if (email == null || email.isEmpty())
			throw new IllegalArgumentException("Cannot search for null email");

		try {
			return em.createQuery("select u from User u where mail=:email", User.class).setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public User findById(Long id) {
		return em.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		return (List<User>) query.getResultList();
	}

	@Override
	public User update(User b) throws IllegalArgumentException {
		if (b == null) {
            throw new IllegalArgumentException("User is null.");
        }
		return em.merge(b);
	}

	@Override
	public void remove(User b) throws IllegalArgumentException {
		if (b == null) {
            throw new IllegalArgumentException("User is null.");
        }
		em.remove(b);
	}

}
