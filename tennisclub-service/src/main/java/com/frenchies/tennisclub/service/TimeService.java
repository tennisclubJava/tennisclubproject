package com.frenchies.tennisclub.service;

import java.util.Date;

import org.springframework.stereotype.Service;

/**
 * An interface that defines a service access to the {@link Product} entity.
 */

//@Author Meon Thomas UCO 473449


@Service
public interface TimeService {
	public Date getCurrentTime();
}
