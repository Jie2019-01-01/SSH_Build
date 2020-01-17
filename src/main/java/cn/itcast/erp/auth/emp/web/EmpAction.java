package cn.itcast.erp.auth.emp.web;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.utils.base.BaseAction;

public class EmpAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	public EmpModel em = new EmpModel();
	
	// 注入业务层接口
	private EmpEbi empEbi;
	public void setEmpEbi(EmpEbi empEbi) {this.empEbi = empEbi;}
	
	public String login() {
		// 获取前台的用户名和密码, 传参到业务层
		EmpModel loginEm = empEbi.login(em.getUserName(), em.getPwd());
		// 查询结果返回
		// 判断查询结果，如果查询到，则登录成功；否则登录失败
		if(loginEm==null) {
			// 登录失败
			this.addActionError("对不起，用户名或密码错误");
			return "loginFail";
		}else {
			// 登录成功
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, loginEm);
			return "loginSuccess";
		}
	}

}
