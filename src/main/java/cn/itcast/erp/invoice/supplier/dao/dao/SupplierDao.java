package cn.itcast.erp.invoice.supplier.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface SupplierDao extends BaseDao<SupplierModel> {

	/**
	 * 获取有商品分类的供应商信息
	 * @return 供应商信息
	 */
	public List<SupplierModel> getHasType();

}
