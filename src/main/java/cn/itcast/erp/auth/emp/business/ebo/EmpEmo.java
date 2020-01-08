package cn.itcast.erp.auth.emp.business.ebo;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.dao.dao.EmpDao;

// Enterprice Business Object
public class EmpEmo implements EmpEbi{

	// 注入数据层接口
	private EmpDao empDao;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}
	
	
}
