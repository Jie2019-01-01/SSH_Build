package cn.itcast.erp.auth.dep.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.itcast.erp.auth.dep.dao.dao.DepDao;
import cn.itcast.erp.auth.dep.vo.DepModel;

public class DepImpl extends HibernateDaoSupport implements DepDao{

	public void save(DepModel dm) {
		this.getHibernateTemplate().save(dm);
	}

	public List<DepModel> getAll() {
		String hql = "from DepModel";
		return (List<DepModel>) this.getHibernateTemplate().find(hql);
	}

	public DepModel get(Long uuid) {
		return this.getHibernateTemplate().get(DepModel.class, uuid);
	}

	public void update(DepModel dm) {
		this.getHibernateTemplate().update(dm);
	}

	public void delete(DepModel dm) {
		this.getHibernateTemplate().delete(dm);
	}
}
