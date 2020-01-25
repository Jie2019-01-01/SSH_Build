package cn.itcast.erp.auth.emp.dao.impl;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import cn.itcast.erp.auth.emp.dao.dao.EmpDao;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class EmpImpl extends BaseDaoImpl<EmpModel> implements EmpDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		// TODO: 自定义查询条件
	}
	
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
