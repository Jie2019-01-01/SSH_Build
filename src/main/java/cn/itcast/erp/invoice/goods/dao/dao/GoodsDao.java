package cn.itcast.erp.invoice.goods.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface GoodsDao extends BaseDao<GoodsModel> {

	/**
	 * 获取商品分类对应的商品信息
	 * @param gtmUuid 商品分类id
	 * @return 商品信息集合
	 */
	public List<GoodsModel> getByGtm(Long gtmUuid);

}
