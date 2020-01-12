package cn.itcast.erp.auth.dep.web;

import com.opensymphony.xwork2.ActionSupport;
import cn.itcast.erp.auth.dep.business.ebi.DepEbi;

public class DepAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private DepEbi depEbi;

	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	/**
	 * 跳转部门管理首页
	 */
	public String list() {
		return SUCCESS;
	}
}
