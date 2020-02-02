package cn.itcast.erp.auth.emp.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.auth.emp.dao.dao.EmpDao;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class EmpImpl extends BaseDaoImpl<EmpModel> implements EmpDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		EmpQueryModel eqm = (EmpQueryModel) bqm;
		if(eqm.getUserName()!=null && eqm.getUserName().trim().length()>0) {
			dc.add(Restrictions.eq("userName", eqm.getUserName().trim()));
		}
		if(eqm.getName()!=null && eqm.getName().trim().length()>0) {
			dc.add(Restrictions.eq("name", eqm.getName().trim()));
		}
		if(eqm.getTele()!=null && eqm.getTele().trim().length()>0) {
			dc.add(Restrictions.like("tele", "%"+eqm.getTele().trim()+"%"));
		}
		if(eqm.getGender()!=null && eqm.getGender()!=-1) {
			dc.add(Restrictions.eq("gender", eqm.getGender()));
		}
		if(eqm.getEmail()!=null && eqm.getEmail().trim().length()>0) {
			dc.add(Restrictions.like("email", "%"+eqm.getEmail().trim()+"%"));
		}
		if(eqm.getDm()!=null && eqm.getDm().getUuid()!=null && eqm.getDm().getUuid()!=-1) {
			// 传入一个对象，默认使用uuid作为查询条件
			dc.add(Restrictions.eqOrIsNull("dm", eqm.getDm()));
		}
		if(eqm.getBirthDay()!=null) {
			dc.add(Restrictions.ge("birthDay", eqm.getBirthDay()));
		}
		if(eqm.getBirthDay2()!=null) {
			dc.add(Restrictions.le("birthDay", eqm.getBirthDay2()));
		}
	}
	
	public static void main(String[] args) {
		// 当前毫秒: 1580639628612
		
		// 去除小时、分钟、秒后的时间，也就是早上的时间: 1580601600000
//		System.out.println(curMills/1000/60/60/24*24*60*60*1000);
		
		// 格式化早晨时间
//		System.out.println(new Date(1580601600000l));
		// 格式化当前时间
//		System.out.println(new Date(1580639628612l));
		
		// 计算早晨到当前时间的小时
//		Long result = (1580639628612l-1580601600000l)/1000/60/60;
	}
	
	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String hql = "from EmpModel where userName=:userName and pwd=:pwd";
		String[] loginName = new String[] {"userName", "pwd"};
		String[] loginPwd = new String[] {userName, pwd};
		System.out.println("开始执行模板");
		List<EmpModel> temp = (List<EmpModel>) this.getHibernateTemplate().findByNamedParam(hql, loginName, loginPwd);
		return temp.size()>0? temp.get(0):null;
	}
}
