package cn.itcast.erp.invoice.supplier.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.invoice.supplier.vo.SupplierQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel, SupplierQueryModel>{

}
