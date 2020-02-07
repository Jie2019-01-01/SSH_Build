package cn.itcast.erp.invoice.goodstype.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class GoodsTypeImpl extends BaseDaoImpl<GoodsTypeModel> implements GoodsTypeDao{

	public void doQbc(BaseQueryModel qm, DetachedCriteria dc){
		GoodsTypeQueryModel gqm = (GoodsTypeQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}
