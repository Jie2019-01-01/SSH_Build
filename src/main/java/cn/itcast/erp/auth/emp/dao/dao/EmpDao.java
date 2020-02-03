package cn.itcast.erp.auth.emp.dao.dao;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface EmpDao extends BaseDao<EmpModel>{
	
	public EmpModel getByUserNameAndPwd(String userName, String pwd);

	public boolean changePwdByUserNameAndPwd(String userName, String pwd, String newPwd);
}	
