package cn.itcast.erp.invoice.orderdetail.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailModel;
import cn.itcast.erp.invoice.orderdetail.vo.OrderDetailQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel, OrderDetailQueryModel>{

}
