package com.frenchies.tennisclub.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.frenchies.tennisclub.enums.Hour24;

//@Author Dore Corentin UCO 473308

@Entity
@Table(name = "BOOKING")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBooking;
<<<<<<< HEAD
	
	@ManyToOne
	@NotNull
	private People player1;
	
	@ManyToOne
	@NotNull
	private People player2;
	
//	@OneToMany
//	@NotNull
//	private List<Court> court = new ArrayList<Court>();
	
=======

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCourt;

	@ManyToOne(optional = false)
	@NotNull
	private People player1;

	@ManyToOne(optional = false)
	@NotNull
	private People player2;

>>>>>>> a66702fc7ef6c9f8449b763accf19e081da34556
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateOfBooking;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Hour24 hourOfBooking;
<<<<<<< HEAD
	

	public Booking(People player1, People player2, Date dateOfBooking, Hour24 hourOfBooking ) {
		//this.idBooking=idBooking;
		this.player1=player1;
		this.player2=player2;
		this.dateOfBooking=dateOfBooking;
		this.hourOfBooking=hourOfBooking;
		
=======

	public Booking(Long idBooking, Long idCourt, People player1, People player2, Date dateOfBooking,
			Hour24 hourOfBooking) {
		this.idBooking = idBooking;
		this.idCourt = idCourt;
		this.player1 = player1;
		this.player2 = player2;
		this.dateOfBooking = dateOfBooking;
		this.hourOfBooking = hourOfBooking;

	}

	public Long getIdCourt() {
		return idCourt;
	}

	public void setIdCourt(Long idCourt) {
		this.idCourt = idCourt;
>>>>>>> a66702fc7ef6c9f8449b763accf19e081da34556
	}

	public People getPlayer1() {
		return player1;
	}

	public void setPlayer1(People player1) {
		this.player1 = player1;
	}

	public People getPlayer2() {
		return player2;
	}

	public void setPlayer2(People player2) {
		this.player2 = player2;
	}
<<<<<<< HEAD
	
//	public List<Court> getCourt() {
//		return Collections.unmodifiableList(court);
//	}
//
//	public void addBookingItem(Court p) {
//		court.add(p);
//	}

=======
>>>>>>> a66702fc7ef6c9f8449b763accf19e081da34556

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public Hour24 getHourOfBooking() {
		return hourOfBooking;
	}

	public void setHourOfBooking(Hour24 hourOfBooking) {
		this.hourOfBooking = hourOfBooking;
	}

	public Long getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(Long idBooking) {
		this.idBooking = idBooking;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
<<<<<<< HEAD
//		result = prime * result + ((court == null) ? 0 : court.hashCode());
=======
>>>>>>> a66702fc7ef6c9f8449b763accf19e081da34556
		result = prime * result + ((dateOfBooking == null) ? 0 : dateOfBooking.hashCode());
		result = prime * result + ((hourOfBooking == null) ? 0 : hourOfBooking.hashCode());
		result = prime * result + ((idBooking == null) ? 0 : idBooking.hashCode());
		result = prime * result + ((idCourt == null) ? 0 : idCourt.hashCode());
		result = prime * result + ((player1 == null) ? 0 : player1.hashCode());
		result = prime * result + ((player2 == null) ? 0 : player2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
<<<<<<< HEAD
//		if (court == null) {
//			if (other.court != null)
//				return false;
//		} else if (!court.equals(other.court))
//			return false;
=======
>>>>>>> a66702fc7ef6c9f8449b763accf19e081da34556
		if (dateOfBooking == null) {
			if (other.dateOfBooking != null)
				return false;
		} else if (!dateOfBooking.equals(other.dateOfBooking))
			return false;
		if (hourOfBooking != other.hourOfBooking)
			return false;
		if (idBooking == null) {
			if (other.idBooking != null)
				return false;
		} else if (!idBooking.equals(other.idBooking))
			return false;
		if (idCourt == null) {
			if (other.idCourt != null)
				return false;
		} else if (!idCourt.equals(other.idCourt))
			return false;
		if (player1 == null) {
			if (other.player1 != null)
				return false;
		} else if (!player1.equals(other.player1))
			return false;
		if (player2 == null) {
			if (other.player2 != null)
				return false;
		} else if (!player2.equals(other.player2))
			return false;
		return true;
	}

}
