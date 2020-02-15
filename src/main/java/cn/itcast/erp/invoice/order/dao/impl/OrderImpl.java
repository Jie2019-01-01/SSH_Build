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
		
		dc.createAlias("sm", "s");
		if(oqm.getSm()!=null && oqm.getSm().getUuid()!=null) {
			dc.add(Restrictions.eq("s.uuid", oqm.getSm()));
		}
		// 发货方式、下单人、审核人、跟单人
		if(oqm.getSm()!=null && oqm.getSm().getNeeds()!=null && oqm.getSm().getNeeds()!=-1) {
			dc.add(Restrictions.eq("s.needs", oqm.getSm().getNeeds()));
		}
		if(oqm.getCreater()!=null && oqm.getCreater().getName()!=null
				&& oqm.getCreater().getName().trim().length()>0) {
			dc.createAlias("creater", "c1");
			dc.add(Restrictions.like("c1.name", "%"+oqm.getCreater().getName().trim()+"%"));
		}
		if(oqm.getChecker()!=null && oqm.getChecker().getName()!=null
				&& oqm.getChecker().getName().trim().length()>0) {
			dc.createAlias("checker", "c2");
			dc.add(Restrictions.like("c2.name", "%"+oqm.getChecker().getName().trim()+"%"));
		}
		if(oqm.getCompleter()!=null && oqm.getCompleter().getName()!=null 
				&& oqm.getCompleter().getName().trim().length()>0) {
			dc.createAlias("completer", "c3");
			dc.add(Restrictions.like("c3.name", "%"+oqm.getCompleter().getName().trim()+"%"));
		}
		// 根据跟单人查询
		if(oqm.getCompleter()!=null && oqm.getCompleter().getUuid()!=null && oqm.getCompleter().getUuid()!=-1) {
			dc.add(Restrictions.eq("completer", oqm.getCompleter()));
		}
	}
	
	// 采购审批的自定义查询条件
	private void doQbc(BaseQueryModel bqm, DetachedCriteria dc, Integer[] orderTypes){
		dc.add(Restrictions.in("orderType", orderTypes));
		doQbc(bqm, dc);
	}
	// 运输任务的自定义查询条件
	private void doQbcTask(BaseQueryModel bqm, DetachedCriteria dc, Integer[] orderTypes){
		dc.add(Restrictions.in("type", orderTypes));
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

	public Integer getCountTask(OrderQueryModel oqm, Integer[] types) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		dc.setProjection(Projections.rowCount());
		doQbcTask(oqm, dc, types);
		Long count = (Long) this.getHibernateTemplate().findByCriteria(dc).get(0);
		return count.intValue();
	}

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount, Integer[] types) {
		DetachedCriteria dc = DetachedCriteria.forClass(OrderModel.class);
		doQbcTask(oqm, dc, types);
		return (List<OrderModel>) this.getHibernateTemplate().findByCriteria(dc, (pageNum-1)*pageCount, pageCount);
	}
}
