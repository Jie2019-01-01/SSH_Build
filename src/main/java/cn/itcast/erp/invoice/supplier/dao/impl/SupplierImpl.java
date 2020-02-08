package cn.itcast.erp.invoice.supplier.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;

import cn.itcast.erp.invoice.supplier.dao.dao.SupplierDao;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class SupplierImpl extends BaseDaoImpl<SupplierModel> implements SupplierDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		
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

}
