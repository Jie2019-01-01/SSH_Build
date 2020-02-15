package cn.itcast.erp.invoice.order.business.ebo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.order.business.ebi.OrderEbi;
import cn.itcast.erp.invoice.order.dao.dao.OrderDao;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.utils.exception.AppException;
import cn.itcast.erp.utils.num.NumUtil;

public class OrderEbo implements OrderEbi{
	
	private OrderDao orderDao;
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(OrderModel om) {
		orderDao.save(om);
	}

	public void update(OrderModel om) {
		orderDao.update(om);
	}

	public void delete(OrderModel om) {
		orderDao.delete(om);
	}

	public OrderModel get(Long uuid) {
		return orderDao.get(uuid);
	}

	public List<OrderModel> getAll() {
		return orderDao.getAll();
	}

	public List<OrderModel> getAll(OrderQueryModel qqm, Integer pageNum,Integer pageCount) {
		return orderDao.getAll(qqm,pageNum,pageCount);
	}

	public Integer getCount(OrderQueryModel qqm) {
		return orderDao.getCount(qqm);
	}

	public void saveBuyOrder(OrderModel om, Long[] goodsUuids, Integer[] nums, Double[] prices, EmpModel creater) {
		// 封装om（只存在一个sm.uuid）
		// 保存订单创建时间
		om.setCreaterTime(System.currentTimeMillis());
		// 订单类型为“采购订单”
		om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		// 订单状态为“未审核”
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK);
		// 制单人
		om.setCreater(creater);
		// 供应商的uuid已经存在,无需封装
		
		int totalNum = 0;
		Double totalPrice = 0.0d;
		// 封装明细信息, 订单与明细级联添加
		Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
		for(int i=0; i<goodsUuids.length; i++) {
			OrderDetailModel odm = new OrderDetailModel();
			// 封装某一类商品的数量
			odm.setNum(nums[i]);
			// 封装某一类商品的单价
			odm.setPrice(prices[i]);
			// 封装商品外键
			GoodsModel gm = new GoodsModel();
			gm.setUuid(goodsUuids[i]);
			odm.setGm(gm);
			// 封装订单外键
			odm.setOm(om);
			
			odms.add(odm);
			totalNum += nums[i];
			totalPrice += nums[i] * prices[i];
		}
		om.setOdms(odms);
		// 商品总量
		om.setTotalNum(totalNum);
		// 商品总价格
		om.setTotalPrice(totalPrice);
		// 订单号
		om.setOrderNum(NumUtil.orderNumUtil());
		
		orderDao.save(om);
	}

	public List<OrderModel> getBuyAll(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
		oqm.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	private Integer[] orderTypes = {OrderModel.ORDER_ORDERTYPE_OF_BUY, OrderModel.ORDER_ORDERTYPE_RETURN_OF_BUY};
	public Integer getCountBuyCheck(OrderQueryModel oqm) {
		return orderDao.getCountBuyCheck(oqm, orderTypes);
	}

	public List<OrderModel> getAllBuyCheck(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
		return orderDao.getAllBuyCheck(oqm, orderTypes);
	}

	public void buyCheckPass(Long uuid, EmpModel checker) {
		// 使用快照完成
		OrderModel om = orderDao.get(uuid);
		// 逻辑校验：非“未审核状态”执行到这里都是非法操作
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)) {
			throw new AppException("对不起，请不要进行非法操作！");
		}
		// 需要修改的字段
		// type > 审核通过
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS);
		// checker > loginEm
		om.setChecker(checker);
		// checkerTime
		om.setCheckerTime(System.currentTimeMillis());
	}

	public void buyCheckNoPass(Long uuid, EmpModel checker) {
		OrderModel om = orderDao.get(uuid);
		// 逻辑校验：非“未审核状态”执行到这里都是非法操作
		if(!om.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_NO_CHECK)) {
			throw new AppException("对不起，请不要进行非法操作！");
		}
		om.setType(OrderModel.ORDER_TYPE_OF_BUY_CHECK_NO_PASS);
		om.setChecker(checker);
		om.setCheckerTime(System.currentTimeMillis());
	}

	private Integer[] types = new Integer[] {
		OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS,
		OrderModel.ORDER_TYPE_OF_BUY_BUYING,
		OrderModel.ORDER_TYPE_OF_BUY_IN_STORE,
		OrderModel.ORDER_TYPE_OF_BUY_COMPLETE
		};
	public Integer getCountTask(OrderQueryModel oqm) {
		// 哪些订单可以运输任务的列表中显示
		//1.审核通过
		//2.采购中
		//3.入库中
		//4.结单
		return orderDao.getCountTask(oqm, types);
	}

	public List<OrderModel> getAllTask(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
		return orderDao.getAllTask(oqm, pageNum, pageCount, types);
	}

	public void assignTask(Long uuid, EmpModel completer) {
		// 快照更新
		OrderModel temp = orderDao.get(uuid);
		// 逻辑校验(集合判定)
		// 这里为了方便，只用了一个，剩余判定：
		// 		OrderModel.ORDER_TYPE_OF_BUY_BUYING， 
		//		OrderModel.ORDER_TYPE_OF_BUY_IN_STORE
		//		OrderModel.ORDER_TYPE_OF_BUY_COMPLETE
		if(!temp.getType().equals(OrderModel.ORDER_TYPE_OF_BUY_CHECK_PASS)) {
			throw new AppException("对不起，请不要进行非法操作！");
		}
		
		temp.setType(OrderModel.ORDER_TYPE_OF_BUY_BUYING);
		temp.setCompleter(completer);
	}

	public Integer getCountQuery(OrderQueryModel oqm, EmpModel loginer) {
		// 当前登录人作为跟单人，显示出对应的任务数
		oqm.setCompleter(loginer);
		return orderDao.getCount(oqm);
	}

	public List<OrderModel> getAllQuery(OrderQueryModel oqm, Integer pageNum, Integer pageCount, EmpModel loginer) {
		// 当前登录人作为跟单人，显示出对应的任务数
		oqm.setCompleter(loginer);
		return orderDao.getAll(oqm, pageNum, pageCount);
	}

	public void statement(OrderModel om) {
		OrderModel temp = orderDao.get(om.getUuid());
		// 修改订单状态(快照)
		temp.setEndTime(System.currentTimeMillis());
//		temp.setType(OrderModel);
	}

}







