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

	/**
	 * 显示运输任务订单数
	 * @param oqm 自定义条件模型
	 * @return 运输任务订单数
	 */
	public Integer getCountTask(OrderQueryModel oqm);

	/**
	 * 显示运输任务列表
	 * @param oqm 自定义条件模型
	 * @return 运输任务列表
	 */
	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount);

	/**
	 * 指派任务
	 * @param uuid 订单uuid
	 * @param completer 跟单人
	 */
	public void assignTask(Long uuid, EmpModel completer);

	/**
	 * 获取当前登录人的任务数
	 * @param oqm 条件模型
	 * @param loginer 登录人
	 * @return 登录人的任务数
	 */
	public Integer getCountQuery(OrderQueryModel oqm, EmpModel loginer);

	/**
	 * 获取当前登录人的全部任务
	 * @param oqm 条件模型
	 * @param pageNum 当前页
	 * @param pageCount 每页显示数
	 * @param loginer 登录人
	 * @return 登录人的全部任务
	 */
	public List<OrderModel> getAllQuery(OrderQueryModel oqm, Integer pageNum, Integer pageCount, EmpModel loginer);

	/**
	 * 结单
	 * @param om 订单模型
	 */
	public void statement(OrderModel om);

	/**
	 * 获取需要入库的订单数
	 * @param oqm 条件模型
	 * @return 订单数
	 */
	public Integer getCountInStore(OrderQueryModel oqm);

	/**
	 * 获取需要入库的订单集合
	 * @param oqm 条件模型
	 * @param pageNum 当前页
	 * @param pageCount 每页显示数
	 * @return 订单集合
	 */
	public List<OrderModel> getAllInStore(OrderQueryModel oqm, Integer pageNum, Integer pageCount);

}
