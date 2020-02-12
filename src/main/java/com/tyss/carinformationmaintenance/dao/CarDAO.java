package com.tyss.carinformationmaintenance.dao;

import java.util.List;

import com.tyss.carinformationmaintenance.dto.CarInfoBean;

public interface CarDAO {
	
	public boolean addCar(CarInfoBean bean);
	public boolean deleteCar(int id);
	public boolean modifyCar(CarInfoBean bean);
	public List<CarInfoBean> getCarByName(String name);
	public List<CarInfoBean> getCarByCompanyName(String company);
	public List<CarInfoBean> getCarByFuelType(String fuel_type);
	public List<CarInfoBean> getAllCars();
}
