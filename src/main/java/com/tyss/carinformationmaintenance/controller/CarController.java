package com.tyss.carinformationmaintenance.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.carinformationmaintenance.dto.AdminInfoBean;
import com.tyss.carinformationmaintenance.dto.CarInfoBean;
import com.tyss.carinformationmaintenance.dto.CarResponse;
import com.tyss.carinformationmaintenance.service.AdminService;
import com.tyss.carinformationmaintenance.service.CarService;


@RestController
public class CarController {
	
	
	@Autowired
	private CarService service;
	@Autowired
	private AdminService adminService; 
	
	@PostMapping(path = "/add-car",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes =MediaType.APPLICATION_JSON_VALUE )
	public CarResponse addCar(@RequestBody CarInfoBean bean) {
		
		CarResponse response = new CarResponse();
		double showroom_price = bean.getShowroom_price();
		String fuel_type = bean.getFuel_type();
		
		if(fuel_type.equals("electric")) {
			double onroad_price = (showroom_price*4)/100;
			bean.setOnroad_price(onroad_price);
		}
		else if(showroom_price<500000) {
			double onroad_price = (showroom_price*13)/100;
			bean.setOnroad_price(onroad_price);
		}else if(showroom_price>=500000&&showroom_price<1000000) {
			double onroad_price = (showroom_price*14)/100;
			bean.setOnroad_price(onroad_price);
		}else if(showroom_price>=1000000&&showroom_price<2000000) {
			double onroad_price = (showroom_price*17)/100;
			bean.setOnroad_price(onroad_price);
		}else if(showroom_price >= 2000000) {
			double onroad_price = (showroom_price*18)/100;
			bean.setOnroad_price(onroad_price);
		}
		
		if(service.addCar(bean)) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Data inserted in to DB ");
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Data is not inserted in to DB ");
			
		}
		return response;
	}
	
	
	@DeleteMapping(path = "/delete/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public CarResponse deleteCar(@PathVariable("id")int id) {
		CarResponse response =new CarResponse();
		if(service.deleteCar(id)) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product is Removed");
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Product is not removed ");
			
		}
		return response;
	}
	
	@PostMapping(path = "/modify-car",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes =MediaType.APPLICATION_JSON_VALUE
			)
	public CarResponse modifyCar(@RequestBody CarInfoBean bean) {
		System.out.println("bean"+bean);
		CarResponse response =new CarResponse();
		if(service.modifyCar(bean)) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Product Modified");
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Product is not Modified ");
			
		}
		return response;
	}
	
	@GetMapping(path = "/search-by-name",produces = MediaType.APPLICATION_JSON_VALUE)
	public CarResponse getCarByName(@RequestParam(name="name",required = false)String name) {
		CarResponse response =new CarResponse();
		List<CarInfoBean> beans = service.getCarByName(name);
		if(beans!=null && !beans.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Data Found in DB for the requested name");
			response.setBeans(beans);
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Data Not Found in DB for the requested name");
//			response.setBeans(null);
		}
		return response;
		
	}
	
	
	@GetMapping(path = "/search-by-companyname",produces = MediaType.APPLICATION_JSON_VALUE)
	public CarResponse getCarByCompanyName(@RequestParam(name="company",required = false)String company) {
		CarResponse response =new CarResponse();
		List<CarInfoBean> beans = service.getCarByCompanyName(company);
		if(beans!=null && !beans.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Data Found in DB for the requested car");
			response.setBeans(beans);
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Data Not Found in DB for the requested car");
//			response.setBeans(null);
		}
		return response;
		
	}
	
	
	
	
	@GetMapping(path = "/search-by-fueltype",produces = MediaType.APPLICATION_JSON_VALUE)
	public CarResponse getCarByFuelType(@RequestParam(name="fuel_type",required = false)String fuel_type) {
		CarResponse response =new CarResponse();
		List<CarInfoBean> beans = service.getCarByFuelType(fuel_type);
		if(beans!=null && !beans.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Data Found in DB for the requested car");
			response.setBeans(beans);
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Data Not Found in DB for the requested car");
//			response.setBeans(null);
		}
		return response;
		
	}
	
	
	
	
	@GetMapping(path = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
	public CarResponse getAllCar() {
		CarResponse response =new CarResponse();
		List<CarInfoBean> beans = service.getAllCars();
		if(beans!=null && !beans.isEmpty()) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Data Founded");
			response.setBeans(beans);
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Data Not Founded");
//			response.setBeans(null);
		}
		return response;
		
	}
	

	@PostMapping(path = "/add-admin",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes =MediaType.APPLICATION_JSON_VALUE )
	public CarResponse registerAdmin(@RequestBody AdminInfoBean adminInfoBean) {
		CarResponse response =new CarResponse();
		if(adminService.addAdmin(adminInfoBean)) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Admin Registered");
			
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("Admin Not Registered");
		}
		return response;
	}
	
	
	@PostMapping(path = "/auth",
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes =MediaType.APPLICATION_JSON_VALUE
			)
	public CarResponse authentication(@RequestBody AdminInfoBean adminInfoBean) {
		CarResponse response =new CarResponse();
		AdminInfoBean infoBean = adminService.auth(adminInfoBean.getEmail(), adminInfoBean.getPassword());
		if(infoBean != null) {
			response.setStatusCode(201);
			response.setMessage("Success");
			response.setDescription("Valid Credential ");
			
			
		}else {
			response.setStatusCode(401);
			response.setMessage("Failure");
			response.setDescription("InValid Credential");
		}
		return response;
	}
	
	
}//End of the class
