package cn.itcast.erp.auth.emp.web;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;

public class EmpAction extends ActionSupport{

	public EmpModel em = new EmpModel();
	
	// 注入业务层接口
	private EmpEbi empEbi;
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	
	public String login() {
		System.out.println("haah....." + em.getUserName() + "\t" + em.getPwd());
		return SUCCESS;
	}
}
