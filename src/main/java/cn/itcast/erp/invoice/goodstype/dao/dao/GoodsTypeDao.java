package cn.itcast.erp.invoice.goodstype.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel> {

	public List<GoodsTypeModel> getAllBySm(Long uuid);

}
