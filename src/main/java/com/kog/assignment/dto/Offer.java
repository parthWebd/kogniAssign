package com.kog.assignment.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;


//@XmlRootElement
@Entity
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	

	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate validFrom;
	
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate validTo;
	
	private String location;
	
	@Column(name = "Image", length = 100000)
	private byte[] image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(LocalDate validFrom) {
		this.validFrom = validFrom;
	}

	public LocalDate getValidTo() {
		return validTo;
	}

	public void setValidTo(LocalDate validTo) {
		this.validTo = validTo;
	}

	
	public Offer(String name, String location, LocalDate validFrom, LocalDate validTo) {
		super();
		this.name = name;
		this.location = location;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}

	public byte[] getPicByte() {
		return image;
	}

	public void setPicByte(byte[] picByte) {
		this.image = picByte;
	}

	public Offer() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", location=" + location + ", validFrom=" + validFrom
				+ ", validTo=" + validTo + "]";
	}
	
	
	

}
