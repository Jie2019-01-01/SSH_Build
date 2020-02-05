package cn.itcast.erp.auth.emp.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.utils.base.BaseAction;

public class EmpAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	public EmpModel em = new EmpModel();
	public EmpQueryModel eqm = new EmpQueryModel();
	
	// 注入业务层接口
	private EmpEbi empEbi;
	private DepEbi depEbi;
	private RoleEbi roleEbi;
	public void setEmpEbi(EmpEbi empEbi) {this.empEbi = empEbi;}
	public void setRoleEbi(RoleEbi roleEbi) {this.roleEbi = roleEbi;}
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
			return "loginSuccess";
		}
	}
	// 注销
	public String logout() {
		putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, null);
		return "loginFail";
	}
	// 修改密码
	public String newPwd;
	public String toChangePwd() {
		return "toChangePwd";
	}
	public String changePwd() {
		boolean flag = empEbi.changePwd(getLogin().getUserName(), eqm.getPwd(), newPwd);
		if(flag) {
			// 修改成功
			// 注销
			putSession(EmpModel.EMP_LOGIN_USER_OBJECT_NAME, null);
			return "loginFail";
			
		}else {
			// 修改失败：可能是密码不对
			ActionContext.getContext().put("error", "密码输入错误...");
			return "toChangePwd";
		}
	}
	
	
	
	// 跳转到列表页
	public String list() {
		List<DepModel> depList = depEbi.getAll();
		put("depList", depList);
		setRecords(empEbi.getCount(eqm));
		List<EmpModel> empList = empEbi.getAll(eqm, pageNum, pageCount);
		put("empList", empList);
		return LIST;
	}

	// 跳转到input.jsp
	public Long[] roleUuids;
	public String input() {
		List<DepModel> depList = depEbi.getAll();
		put("depList", depList);
		List<RoleModel> roleList = roleEbi.getAll();
		put("roleList", roleList);
		if(em.getUuid()!=null) {
			em = empEbi.get(em.getUuid());
			roleUuids = new Long[em.getRoles().size()];
			int i = 0;
			for(RoleModel temp: em.getRoles()) {
				roleUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}
	
	// 添加
	public String save() {
		if(em.getUuid()==null) {
			empEbi.save(em, roleUuids);
		}else {
			empEbi.update(em,roleUuids);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		empEbi.delete(em);
		return TO_LIST;
	}
}
