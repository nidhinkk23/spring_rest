package com.tyss.carinformationmaintenance.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.tyss.carinformationmaintenance.dto.AdminInfoBean;
import com.tyss.carinformationmaintenance.exception.EmailAlreadyExistException;

import lombok.extern.java.Log;


@Log
@Repository
public class AdminDAOImpl implements AdminDAO {


	@PersistenceUnit
	private EntityManagerFactory factory;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public boolean addAdmin(AdminInfoBean bean) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
		try {

			bean.setPassword(encoder.encode(bean.getPassword()));

			manager.persist(bean);
			transaction.commit();
			return true;

		} catch (Exception e) {
			log.info(e.getMessage());

			for (StackTraceElement element : e.getStackTrace()) {
				log.info(e.toString());
			}
			throw new EmailAlreadyExistException("email already exists");
//			return false;

		}



	}

	@Override
	public AdminInfoBean auth(String email, String password) {

		String jpql = "select e from AdminInfoBean e where e.email=:email";
		EntityManager manager = factory.createEntityManager();
		TypedQuery<AdminInfoBean> query = manager.createQuery(jpql,AdminInfoBean.class);
		query.setParameter("email", email);
		try {

			AdminInfoBean bean =query.getSingleResult();

			if(encoder.matches(password, bean.getPassword())) {
				return bean;
			}else {
				return null;
			}
		} catch (Exception e) {
			log.info(e.getMessage());

			for (StackTraceElement element : e.getStackTrace()) {
				log.info(e.toString());
			}

			return null;
		}


	}

}//End of the class
