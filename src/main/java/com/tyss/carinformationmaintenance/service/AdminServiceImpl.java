package com.tyss.carinformationmaintenance.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.carinformationmaintenance.dao.AdminDAO;
import com.tyss.carinformationmaintenance.dto.AdminInfoBean;

@Service
public class AdminServiceImpl implements AdminService {


	@Autowired
	AdminDAO dao;

	@Override
	public boolean addAdmin(AdminInfoBean bean) {

		final Pattern VALID_EMAIL_ADDRESS_REGEX = 
				Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(bean.getEmail());
		System.out.println("matcher"+matcher.find());

		if(matcher.find()) {
			
			return dao.addAdmin(bean);


		}else {

			return false;

		}



	}

	@Override
	public AdminInfoBean auth(String email, String password) {

		return dao.auth(email, password);
	}

}
