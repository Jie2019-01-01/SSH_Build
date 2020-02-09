package cn.itcast.erp.invoice.order.web;

import java.util.List;
import cn.itcast.erp.invoice.order.business.ebi.OrderEbi;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.utils.base.BaseAction;

public class OrderAction extends BaseAction{

	private static final long serialVersionUID = 756695487440L;
	
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	private OrderEbi orderEbi;
	public void setOrderEbi(OrderEbi orderEbi) {
		this.orderEbi = orderEbi;
	}

	//列表
	public String list(){
		setRecords(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return LIST;
	}

	//到添加
	public String input(){
		if(om.getUuid()!=null){
			om = orderEbi.get(om.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(om.getUuid() == null){
			orderEbi.save(om);
		}else{
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		orderEbi.delete(om);
		return TO_LIST;
	}

	//----------采购------------------------
	public String buyList() {
		return "buyList";
	}
	
	public String buyInput() {
		return "buyInput";
	}
}
