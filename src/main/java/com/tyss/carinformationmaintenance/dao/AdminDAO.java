package com.tyss.carinformationmaintenance.dao;

import com.tyss.carinformationmaintenance.dto.AdminInfoBean;

public interface AdminDAO {
	public boolean addAdmin(AdminInfoBean bean);
	public AdminInfoBean auth(String email,String password);
}
