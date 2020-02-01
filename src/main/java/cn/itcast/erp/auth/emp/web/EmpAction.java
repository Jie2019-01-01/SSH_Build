package cn.itcast.erp.auth.emp.web;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.utils.base.BaseAction;
import cn.itcast.erp.utils.format.MD5Utils;
import cn.itcast.erp.utils.ip.IpUtils;

public class EmpAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();
	
	// 注入业务层接口
	private EmpEbi empEbi;
	public void setEmpEbi(EmpEbi empEbi) {this.empEbi = empEbi;}
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {this.depEbi = depEbi;}

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
//			loginEm.setLastLoginTime(System.currentTimeMillis());
//			int times = Integer.parseInt(loginEm.getLoginTimes())+1;
//			loginEm.setLoginTimes(String.valueOf(times));
//			String ip = IpUtils.getIpAddr(ServletActionContext.getRequest());
//			loginEm.setLastLoginIp(ip);
//			empEbi.update(loginEm);
			return "loginSuccess";
		}
	}
	public String logout() {
		removeSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		return "noLogin";
	}
	
	
	// 跳转到列表页
	public String list() {
		List<DepModel> depList = depEbi.getAll();
		put("depList", depList);
		List<EmpModel> empList = empEbi.getAll(eqm, pageNum, pageCount);
		put("empList", empList);
		return LIST;
	}

	// 跳转到input.jsp
	public String input() {
		if(em.getUuid()!=null) {
			em = empEbi.get(em.getUuid());
		}
		List<DepModel> depList = depEbi.getAll();
		put("depList", depList);
		return INPUT;
	}

	// 添加
	public String save() {
		if(em.getUuid()==null) {
			em.setLastLoginIp(IpUtils.getIpAddr(ServletActionContext.getRequest()));
			em.setLoginTimes("1");
			em.setLastLoginTime(System.currentTimeMillis());
			empEbi.save(em);
		}else {
			empEbi.update(em);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		empEbi.delete(em);
		return TO_LIST;
	}
}
