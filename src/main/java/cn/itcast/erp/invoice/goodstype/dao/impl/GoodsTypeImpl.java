package cn.itcast.erp.invoice.goodstype.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;

import cn.itcast.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseDaoImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQbc(BaseQueryModel qm, DetachedCriteria dc){
		// TODO 添加自定义查询条件
	}

	public List<GoodsTypeModel> getAllBySm(Long uuid) {
		String hql = "from GoodsTypeModel where supplierUuid=:supplierUuid";
		Session session = this.getSessionFactory().getCurrentSession();
		if(session==null) {
			session = this.getSessionFactory().openSession();
		}
		Query<GoodsTypeModel> query = session.createQuery(hql);
		query.setParameter("supplierUuid", uuid);
		List<GoodsTypeModel> gtmList = query.list();
		return gtmList;
	}

}
