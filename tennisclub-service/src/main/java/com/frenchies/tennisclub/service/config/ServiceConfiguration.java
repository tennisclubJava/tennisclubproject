package com.frenchies.tennisclub.service.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.frenchies.tennisclub.PersistenceSampleApplicationContext;
import com.frenchies.tennisclub.dto.CourtDTO;
import com.frenchies.tennisclub.entity.Court;
import com.frenchies.tennisclub.service.BookingServiceImpl;
import com.frenchies.tennisclub.service.facade.CourtFacadeImpl;

/**
 * 
 * @author Meon Thomas 473449
 *
 */


@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={BookingServiceImpl.class, CourtFacadeImpl.class})
public class ServiceConfiguration {
	

	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();		
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}
	
	/**
	 * Custom config for Dozer if needed
	 *
	 */
	public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
	        mapping(Court.class, CourtDTO.class);
	    }
	}
	
}

