package com.tyss.carinformationmaintenance.dto;

import java.util.List;

import lombok.Data;


@Data
public class CarResponse {
	private int statusCode;
	private String message;
	private String description;
	private List<CarInfoBean> beans;
}
