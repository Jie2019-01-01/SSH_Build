package cn.itcast.erp.invoice.order.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel, OrderQueryModel>{

	/**
	 * 保存采购订单
	 * @param om 订单模型（封装了供应商uuid）
	 * @param goodsUuids 商品uuid数组
	 * @param nums 商品数量数组
	 * @param prices 商品单价数组
	 * @param creater 制单人
	 */
	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums, Double[] prices, EmpModel creater);

	/**
	 * 获取全部采购订单
	 * @param oqm 封装条件模型
	 * @param pageNum 起始数
	 * @param pageCount 每页显示数量
	 * @return
	 */
	public List<OrderModel> getBuyAll(OrderQueryModel oqm, Integer pageNum, Integer pageCount);

	/**
	 * 获取采购审批的订单总量
	 * @param oqm
	 * @return
	 */
	public Integer getCountBuyCheck(OrderQueryModel oqm);

	/**
	 * 获取采购审批的订单
	 * @param oqm
	 * @param pageNum
	 * @param pageCount
	 * @return
	 */
	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm, Integer pageNum, Integer pageCount);

	/**
	 * 修改订单状态-审核通过
	 * @param uuid 订单uuid
	 * @param checker 审核人
	 */ 
	public void buyCheckPass(Long uuid, EmpModel checker);

	public void buyCheckNoPass(Long uuid, EmpModel checker);

}
