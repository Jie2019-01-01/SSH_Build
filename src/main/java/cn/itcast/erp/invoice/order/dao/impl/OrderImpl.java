package cn.itcast.erp.invoice.order.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.invoice.order.dao.dao.OrderDao;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class OrderImpl extends BaseDaoImpl<OrderModel> implements OrderDao{

	public void doQbc(BaseQueryModel qm, DetachedCriteria dc){
		OrderQueryModel oqm = (OrderQueryModel)qm;
		if(oqm.getOrderType()!=null && oqm.getOrderType()!=-1) {
			dc.add(Restrictions.eq("orderType", oqm.getOrderType()));
		}
		if(oqm.getCreater()!=null && oqm.getCreater().getName()!=null
				&& oqm.getCreater().getName().trim().length()>0) {
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"+oqm.getCreater().getName().trim()+"%"));
		}
	}

}
