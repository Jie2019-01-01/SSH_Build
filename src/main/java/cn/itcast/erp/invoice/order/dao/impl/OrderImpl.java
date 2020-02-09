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
		// TODO 添加自定义查询条件
	}

}
