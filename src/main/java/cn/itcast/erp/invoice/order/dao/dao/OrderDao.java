package cn.itcast.erp.invoice.order.dao.dao;

import java.util.List;

import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface OrderDao extends BaseDao<OrderModel> {

	/**
	 * 获取采购审批的订单总量
	 * @param oqm 自定义查询条件模型
	 * @param orderTypes 封装了需要采购审批的类型（采购、采购退货）
	 * @return 采购审批的订单总量
	 */
	public Integer getCountBuyCheck(OrderQueryModel oqm, Integer[] orderTypes);

	/**
	 * 获取采购审批的订单
	 * @param oqm 自定义查询条件模型
	 * @param orderTypes 封装了需要采购审批的类型（采购、采购退货）
	 * @return 采购审批的全部订单
	 */
	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm, Integer[] orderTypes);

}
