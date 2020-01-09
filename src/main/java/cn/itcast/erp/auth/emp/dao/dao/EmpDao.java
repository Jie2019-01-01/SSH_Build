package cn.itcast.erp.auth.emp.dao.dao;

import cn.itcast.erp.auth.emp.vo.EmpModel;

public interface EmpDao {

	public EmpModel getByUserNameAndPwd(String userName, String pwd);

}
