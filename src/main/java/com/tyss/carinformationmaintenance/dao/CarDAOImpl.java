package com.tyss.carinformationmaintenance.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.tyss.carinformationmaintenance.dto.CarInfoBean;


import lombok.extern.java.Log;


@Log
@Repository
public class CarDAOImpl implements CarDAO {


	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public boolean addCar(CarInfoBean bean) {

		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {
			manager.persist(bean);
			transaction.commit();
			return true;

		} catch (Exception e) {
			log.info(e.getMessage());

			for (StackTraceElement element : e.getStackTrace()) {
				log.info(e.toString());
			}

			return false;
			//			throw new NameAlreadyExistException("Name already exist");

		}
	}

	@Override
	public boolean deleteCar(int id) {

		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		CarInfoBean bean = manager.find(CarInfoBean.class, id);
		manager.remove(bean);
		transaction.commit();
		return true;
	}


	@Override
	public boolean modifyCar(CarInfoBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		CarInfoBean infoBean = manager.find(CarInfoBean.class, bean.getId());

		infoBean.setName(bean.getName());
		infoBean.setCompany(bean.getCompany());
		infoBean.setFuel_type(bean.getFuel_type());
		infoBean.setPower_steering(bean.isPower_steering());
		infoBean.setBreak_system(bean.getBreak_system());
		infoBean.setShowroom_price(bean.getShowroom_price());
		infoBean.setOnroad_price(bean.getOnroad_price());
		infoBean.setImage_url(bean.getImage_url());
		infoBean.setMilege(bean.getMilege());
		infoBean.setSeating_capacity(bean.getSeating_capacity());
		infoBean.setEngine_capacity(bean.getEngine_capacity());
		infoBean.setGear_type(bean.getBreak_system());

		manager.persist(infoBean);
		transaction.commit();
		return true;
	}

	@Override
	public List<CarInfoBean> getCarByName(String name) {

		String jpql = "select p from CarInfoBean p where p.name=:name";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<CarInfoBean> query = 
				manager.createQuery(jpql,CarInfoBean.class);


		query.setParameter("name", name);

		return query.getResultList();



	}

	@Override
	public List<CarInfoBean> getCarByCompanyName(String company) {
		
		String jpql = "select p from CarInfoBean p where p.company=:company";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<CarInfoBean> query = 
				manager.createQuery(jpql,CarInfoBean.class);


		query.setParameter("company", company);

		return query.getResultList();

	}

	@Override
	public List<CarInfoBean> getCarByFuelType(String fuel_type) {
		
		String jpql = "select p from CarInfoBean p where p.fuel_type=:fuel_type";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<CarInfoBean> query = 
				manager.createQuery(jpql,CarInfoBean.class);


		query.setParameter("fuel_type", fuel_type);

		return query.getResultList();

	}

	@Override
	public List<CarInfoBean> getAllCars() {
		
		String jpql = "select p from CarInfoBean p";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<CarInfoBean> query = 
				manager.createQuery(jpql,CarInfoBean.class);


		
		
		return query.getResultList();

	}







}//End of the class
