package cn.itcast.erp.invoice.order.business.ebo;

import java.util.List;
import cn.itcast.erp.invoice.order.business.ebi.OrderEbi;
import cn.itcast.erp.invoice.order.dao.dao.OrderDao;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;

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

}
