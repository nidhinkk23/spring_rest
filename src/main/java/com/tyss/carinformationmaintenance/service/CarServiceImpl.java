package com.tyss.carinformationmaintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.carinformationmaintenance.dao.CarDAO;
import com.tyss.carinformationmaintenance.dto.CarInfoBean;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarDAO dao;
	
	
	@Override
	public boolean addCar(CarInfoBean bean) {
		
		return dao.addCar(bean);
	}


	@Override
	public boolean deleteCar(int id) {
	
		return dao.deleteCar(id);
	}


	@Override
	public boolean modifyCar(CarInfoBean bean) {
		
		return dao.modifyCar(bean);
	}


	@Override
	public List<CarInfoBean> getCarByName(String name) {
		
		return dao.getCarByName(name);
	}


	@Override
	public List<CarInfoBean> getCarByCompanyName(String company) {
		
		return dao.getCarByCompanyName(company);
	}


	@Override
	public List<CarInfoBean> getCarByFuelType(String fuel_type) {
		
		return dao.getCarByFuelType(fuel_type);
	}


	@Override
	public List<CarInfoBean> getAllCars() {
		
		return dao.getAllCars();
	}

}
