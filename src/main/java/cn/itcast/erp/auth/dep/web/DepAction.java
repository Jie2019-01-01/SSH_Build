package cn.itcast.erp.auth.dep.web;

import com.opensymphony.xwork2.ActionSupport;
import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.vo.DepModel;

public class DepAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	public DepModel dm = new DepModel();
	
	// 跳转部门管理首页
	public String list() {
		return "list";
	}
	
	// 跳转添加页面
	public String input() {
		return "input";
	}
	
	// 信息输入之后的保存操作
	public String save() {
		depEbi.save(dm);
		return list();
	}
}
