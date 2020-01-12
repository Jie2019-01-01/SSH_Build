package cn.itcast.erp.auth.dep.dao.impl;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.auth.dep.dao.dao.DepDao;
import cn.itcast.erp.auth.dep.vo.DepModel;

public class DepImpl extends HibernateDaoSupport implements DepDao{

	@Override
	public void save(DepModel dm) {
		this.getHibernateTemplate().save(dm);
	}
}
