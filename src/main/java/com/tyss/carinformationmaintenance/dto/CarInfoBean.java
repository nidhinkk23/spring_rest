package com.tyss.carinformationmaintenance.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



import lombok.Data;

@Data
@Entity
@Table(name = "car_details")
public class CarInfoBean {
	

	@Id
	@Column
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private String company;
	@Column
	private String fuel_type;
	@Column
	private boolean power_steering;
	@Column
	private String break_system;
	@Column
	private double showroom_price;
	@Column
	private double onroad_price;
	@Column
	private String image_url;
	@Column
	private double milege;
	@Column
	private int seating_capacity;
	@Column
	private int engine_capacity;
	@Column
	private String gear_type;
}//End of the class
