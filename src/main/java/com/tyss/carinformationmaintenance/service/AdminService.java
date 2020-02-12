package com.tyss.carinformationmaintenance.service;

import com.tyss.carinformationmaintenance.dto.AdminInfoBean;

public interface AdminService {
	public boolean addAdmin(AdminInfoBean bean);
	public AdminInfoBean auth(String email,String password);
}
