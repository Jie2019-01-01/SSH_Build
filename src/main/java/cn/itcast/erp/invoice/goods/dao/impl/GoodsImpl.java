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
		// TODO 添加自定义查询条件
	}

}
