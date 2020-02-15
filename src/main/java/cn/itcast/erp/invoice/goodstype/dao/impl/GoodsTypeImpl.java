package cn.itcast.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import cn.itcast.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseDaoImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc){
		GoodsTypeQueryModel gtqm = (GoodsTypeQueryModel) bqm;
		if(gtqm.getSm()!=null && gtqm.getSm().getUuid()!=null 
				&& gtqm.getSm().getUuid()!=-1) {
			dc.createAlias("sm", "s");
			dc.add(Restrictions.eq("s.uuid", gtqm.getSm().getUuid()));
		}
		if(gtqm.getName()!=null && gtqm.getName().trim().length()>0) {
			dc.add(Restrictions.like("name", "%"+gtqm.getName().trim()+"%"));
		}
	}

	public List<GoodsTypeModel> getAllBySm(Long uuid) {
		String hql = "select distinct gtm from GoodsTypeModel gtm join gtm.sm where supplierUuid=:supplierUuid";
		Session session = this.getSessionFactory().getCurrentSession();
		if(session==null) {
			session = this.getSessionFactory().openSession();
		}
		Query<GoodsTypeModel> query = session.createQuery(hql);
		query.setParameter("supplierUuid", uuid);
		List<GoodsTypeModel> gtmList = query.list();
		return gtmList;
	}
	
	public List<GoodsTypeModel> getBySm(Long uuid) {
		String hql = "select distinct g from GoodsModel gm join gm.gtm g where g.sm.uuid=:uuid";
		Session session = this.getSessionFactory().getCurrentSession();
		if(session==null) {
			session = this.getSessionFactory().openSession();
		}
		Query<GoodsTypeModel> query = session.createQuery(hql);
		query.setParameter("uuid", uuid);
		List<GoodsTypeModel> gtmList = query.list();
		return gtmList;
	}

}
