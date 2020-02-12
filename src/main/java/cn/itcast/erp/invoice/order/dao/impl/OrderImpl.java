package cn.itcast.erp.invoice.order.dao.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.invoice.order.dao.dao.OrderDao;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class OrderImpl extends BaseDaoImpl<OrderModel> implements OrderDao{

	// 自定义查询
	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc){
		OrderQueryModel oqm = (OrderQueryModel)bqm;
		if(oqm.getOrderType()!=null && oqm.getOrderType()!=-1) {
			dc.add(Restrictions.eq("orderType", oqm.getOrderType()));
		}
		if(oqm.getCreater()!=null && oqm.getCreater().getName()!=null
				&& oqm.getCreater().getName().trim().length()>0) {
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"+oqm.getCreater().getName().trim()+"%"));
		}
	}
	
	// 采购审批的自定义查询条件
	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc, Integer[] orderTypes){
		dc.add(Restrictions.in("orderType", orderTypes));
		doQbc(bqm, dc);
	}

	public Integer getCountBuyCheck(OrderQueryModel oqm, Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		dc.setProjection(Projections.rowCount());
		doQbc(oqm, dc, orderTypes);
		Long count = (Long) this.getHibernateTemplate().findByCriteria(dc).get(0);
		return count.intValue();
	}

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm, Integer[] orderTypes) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		doQbc(oqm, dc, orderTypes);
		return (List<OrderModel>) this.getHibernateTemplate().findByCriteria(dc);
	}

}
