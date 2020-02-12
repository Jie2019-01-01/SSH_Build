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

}







