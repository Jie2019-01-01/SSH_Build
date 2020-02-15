package cn.itcast.erp.invoice.goods.dao.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class GoodsImpl extends BaseDaoImpl<GoodsModel> implements GoodsDao{

	public void doQbc(BaseQueryModel qm, DetachedCriteria dc){
		// 传实体类
		dc.createAlias("gtm", "g");
		Order order = Order.asc("g.uuid");
		dc.addOrder(order);
		GoodsQueryModel gqm = (GoodsQueryModel)qm;
		// 按供应商查询
		if(gqm.getGtm()!=null && gqm.getGtm().getSm()!=null 
				&& gqm.getGtm().getSm().getUuid()!=null
				&& gqm.getGtm().getSm().getUuid()!=-1) {
			// 传参uuid
//			dc.createAlias("gtm", "g");
//			dc.createAlias("g.sm", "s");
//			dc.add(Restrictions.eq("s.uuid", gqm.getGtm().getSm().getUuid()));
			dc.add(Restrictions.eq("g.sm", gqm.getGtm().getSm()));
		}
		// 按商品名称查询
		if(gqm.getName()!=null && gqm.getName().trim().length()>0) {
			dc.add(Restrictions.like("name", "%"+gqm.getName().trim()+"%"));
		}
		// 按生产厂家查询
		if(gqm.getProducer()!=null && gqm.getProducer().trim().length()>0) {
			dc.add(Restrictions.like("producer", "%"+gqm.getProducer().trim()+"%"));
		}
		// 按商品单位查询
		if(gqm.getUnit()!=null && gqm.getUnit().trim().length()>0) {
			dc.add(Restrictions.like("unit", "%"+gqm.getUnit().trim()+"%"));
		}
		// 进货价格范围查询
		if(gqm.getInPrice()!=null) {
			dc.add(Restrictions.ge("inPrice", gqm.getInPrice()));
		}
		if(gqm.getMaxInPrice()!=null) {
			dc.add(Restrictions.le("inPrice", gqm.getMaxInPrice()));
		}
		// 销售价格范围查询
		if(gqm.getOutPrice()!=null) {
			dc.add(Restrictions.ge("outPrice", gqm.getOutPrice()));
		}
		if(gqm.getMaxOutPrice()!=null) {
			dc.add(Restrictions.le("outPrice", gqm.getMaxOutPrice()));
		}
	}

	public List<GoodsModel> getByGtm(Long gtmUuid) {
		String hql = "from GoodsModel where gtm.uuid=:gtmUuid";
		return (List<GoodsModel>) this.getHibernateTemplate().findByNamedParam(hql, "gtmUuid", gtmUuid);
	}

}
