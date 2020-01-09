package cn.itcast.erp.auth.emp.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.auth.emp.dao.dao.EmpDao;
import cn.itcast.erp.auth.emp.vo.EmpModel;

public class EmpImpl extends HibernateDaoSupport implements EmpDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String hql = "from EmpModel where userName=:userName and pwd=:pwd";
		String[] loginName = new String[] {"userName", "pwd"};
		String[] loginPwd = new String[] {userName, pwd};
		System.out.println("开始执行模板");
		List<EmpModel> temp = (List<EmpModel>) this.getHibernateTemplate().findByNamedParam(hql, loginName, loginPwd);
		return temp.size()>0? temp.get(0):null;
	}

}
