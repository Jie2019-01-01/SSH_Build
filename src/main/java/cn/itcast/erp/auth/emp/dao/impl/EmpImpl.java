package cn.itcast.erp.auth.emp.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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
	
	public EmpModel getByUserNameAndPwd(String userName, String pwd) {
		String hql = "from EmpModel where userName=:userName and pwd=:pwd";
		System.out.println("开始执行模板");
		List<EmpModel> temp = (List<EmpModel>) this.getHibernateTemplate().findByNamedParam(hql, new String[] {"userName", "pwd"}, new String[] {userName, pwd});
		return temp.size()>0? temp.get(0):null;
	}

	public boolean changePwdByUserNameAndPwd(String userName, String pwd, String newPwd) {
		String hql = "update EmpModel set pwd=:newPwd where userName=:userName and pwd=:pwd";
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("newPwd", newPwd);
		query.setParameter("userName", userName);
		query.setParameter("pwd", pwd);
		int row = query.executeUpdate();
		return row>0;
	}

	public List<EmpModel> getByDep(Long depUuid) {
		String hql = "from EmpModel where dm.uuid=:depUuid";
		return (List<EmpModel>) this.getHibernateTemplate().findByNamedParam(hql, "depUuid", depUuid);
	}
}
