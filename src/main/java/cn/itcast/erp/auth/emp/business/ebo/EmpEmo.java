package cn.itcast.erp.auth.emp.business.ebo;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.dao.dao.EmpDao;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.utils.format.MD5Utils;

// Enterprice Business Object
public class EmpEmo implements EmpEbi{

	// 注入数据层接口
	private EmpDao empDao;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}
	
	/**
	 * 根据用户名和密码登录
	 * @param userName	用户名
	 * @param pwd 密码
	 * @return 登录成功的用户信息，失败则返回null
	 */
	@Override
	public EmpModel login(String userName, String pwd) {
		// 密码加密
		pwd = MD5Utils.md5(pwd);
		// 调用数据层
		EmpModel empModel = empDao.getByUserNameAndPwd(userName, pwd);
		return empModel;
	}
	
	
}
