package cn.itcast.erp.invoice.supplier.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.supplier.dao.dao.SupplierDao;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.invoice.supplier.vo.SupplierQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class SupplierImpl extends BaseDaoImpl<SupplierModel> implements SupplierDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		SupplierQueryModel sqm = (SupplierQueryModel) bqm;
		if(sqm.getName()!=null && sqm.getName().trim().length()>0) {
			dc.add(Restrictions.like("name", "%"+sqm.getName().trim()+"%"));
		}
		if(sqm.getContact()!=null && sqm.getContact().trim().length()>0) {
			dc.add(Restrictions.like("contact", "%"+sqm.getContact().trim()+"%"));
		}
		if(sqm.getTele()!=null && sqm.getTele().trim().length()>0) {
			dc.add(Restrictions.like("tele", "%"+sqm.getTele().trim()+"%"));
		}
		if(sqm.getNeeds()!=null && sqm.getNeeds()!=-1) {
			dc.add(Restrictions.eq("needs", sqm.getNeeds()));
		}
	}

	public List<SupplierModel> getHasType() {
		String hql = "select distinct s from GoodsTypeModel gtm join gtm.sm s";
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		if(session!=null) {
			session = sessionFactory.openSession();
		}
		Query<SupplierModel> query = session.createQuery(hql);
		return query.list();
	}

	public List<SupplierModel> getHasTypeAndGoods() {
		String hql = "select distinct s from GoodsModel gm join gm.gtm g join g.sm s";
		return (List<SupplierModel>) this.getHibernateTemplate().find(hql);
	}

}
