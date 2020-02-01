package cn.itcast.erp.auth.emp.business.ebo;

import java.util.List;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.dao.dao.EmpDao;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.utils.format.MD5Utils;

// Enterprice Business Object
public class EmpEbo implements EmpEbi{

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
	
	public EmpModel login(String userName, String pwd) {
		// 密码加密
		pwd = MD5Utils.md5(pwd);
		// 调用数据层
		EmpModel empModel = empDao.getByUserNameAndPwd(userName, pwd);
		return empModel;
	}

	
	public void save(EmpModel em) {
		
		
	}

	
	public List<EmpModel> getAll() {
		return empDao.getAll();
	}

	
	public EmpModel get(Long uuid) {
		
		return null;
	}

	
	public void update(EmpModel em) {
		empDao.update(em);
	}

	
	public void delete(EmpModel em) {
		
		
	}

	
	public List<EmpModel> getAll(EmpQueryModel q, Integer pageNum, Integer pageCount) {
		return empDao.getAll();
	}

	
	public Integer getCount(EmpQueryModel q) {
		
		return null;
	}
}
