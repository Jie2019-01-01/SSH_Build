package cn.itcast.erp.invoice.goods.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class GoodsImpl extends BaseDaoImpl<GoodsModel> implements GoodsDao{

	public void doQbc(BaseQueryModel qm, DetachedCriteria dc){
		GoodsQueryModel gqm = (GoodsQueryModel)qm;
		// 按商品单位查询
		if(gqm.getUnit()!=null && gqm.getUnit().trim().length()>0) {
			dc.add(Restrictions.eq("unit", gqm.getUnit().trim()));
		}
		// 按供应商名称查询
		if(gqm.getGtm()!=null && gqm.getGtm().getSm()!=null && gqm.getGtm().getSm().getUuid()!=null
				&& gqm.getGtm().getSm().getUuid()!=-1) {
			// 传参uuid
//			dc.createAlias("gtm", "g");
//			dc.createAlias("g.sm", "s");
//			dc.add(Restrictions.eq("s.uuid", gqm.getGtm().getSm().getUuid()));
			// 传实体类
			dc.createAlias("gtm", "g");
			dc.add(Restrictions.eq("g.sm", gqm.getGtm().getSm()));
		}
	}

}
