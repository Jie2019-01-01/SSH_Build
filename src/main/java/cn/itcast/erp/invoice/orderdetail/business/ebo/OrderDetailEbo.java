package cn.itcast.erp.invoice.orderdetail.business.ebo;

import java.util.List;
import cn.itcast.erp.invoice.orderdetail.business.ebi.OrderDetailEbi;
import cn.itcast.erp.invoice.orderdetail.dao.dao.OrderDetailDao;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailQueryModel;

public class OrderDetailEbo implements OrderDetailEbi{
	
	private OrderDetailDao orderDetailDao;
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public void save(OrderDetailModel om) {
		orderDetailDao.save(om);
	}

	public void update(OrderDetailModel om) {
		orderDetailDao.update(om);
	}

	public void delete(OrderDetailModel om) {
		orderDetailDao.delete(om);
	}

	public OrderDetailModel get(Long uuid) {
		return orderDetailDao.get(uuid);
	}

	public List<OrderDetailModel> getAll() {
		return orderDetailDao.getAll();
	}

	public List<OrderDetailModel> getAll(OrderDetailQueryModel oqm, Integer pageNum,Integer pageCount) {
		return orderDetailDao.getAll(oqm, pageNum, pageCount);
	}

	public Integer getCount(OrderDetailQueryModel oqm) {
		return orderDetailDao.getCount(oqm);
	}
}
