package cn.itcast.erp.auth.dep.dao.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import cn.itcast.erp.auth.dep.dao.dao.DepDao;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.dep.vo.DepQueryModel;

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

	public List<DepModel> getAll(DepQueryModel dqm) {
		// QBC查询，下面代码等同于查询全部
//		DetachedCriteria dc = DetachedCriteria.forClass(DepModel.class);
//		this.getHibernateTemplate().findByCriteria(dc);
		
		DetachedCriteria dc = DetachedCriteria.forClass(DepModel.class);
		if(dqm.getName()!=null && dqm.getName().trim().length()>0) {
			// name和tele的字段必须在xxx.hbm.xml中存在
			dc.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		}
		if(dqm.getTele()!=null && dqm.getTele().trim().length()>0) {
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}
		return (List<DepModel>) this.getHibernateTemplate().findByCriteria(dc);
	}

	public List<DepModel> getAll(DepQueryModel dqm, Integer pageNum, Integer pageCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(DepModel.class);
		if(dqm.getName()!=null && dqm.getName().trim().length()>0) {
			dc.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		}
		if(dqm.getTele()!=null && dqm.getTele().trim().length()>0) {
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}
		return (List<DepModel>) this.getHibernateTemplate().findByCriteria(dc, (pageNum*1-1)*pageCount, pageCount);
	}

	public Integer getCount(DepQueryModel dqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(DepModel.class);
		dc.setProjection(Projections.rowCount());
		if(dqm.getName()!=null && dqm.getName().trim().length()>0) {
			dc.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		}
		if (dqm.getTele()!=null && dqm.getTele().trim().length()>0) {
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}
		Long size = (Long)this.getHibernateTemplate().findByCriteria(dc).get(0);
		return size.intValue();
	}
}
