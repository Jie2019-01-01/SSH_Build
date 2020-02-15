package cn.itcast.erp.invoice.goodstype.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface GoodsTypeDao extends BaseDao<GoodsTypeModel> {

	/**
	 * 获取指定供应商下的全部类别信息
	 * @param uuid 供应商uuid
	 * @return 商品类别集合
	 */
	public List<GoodsTypeModel> getAllBySm(Long uuid);
	
	/**
	 * 获取指定供应商下拥有商品的类别信息
	 * @param uuid 供应商uuid
	 * @return 商品类别集合
	 */
	public List<GoodsTypeModel> getBySm(Long uuid);

}
