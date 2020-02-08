package cn.itcast.erp.invoice.supplier.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.invoice.supplier.vo.SupplierQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface SupplierEbi extends BaseEbi<SupplierModel, SupplierQueryModel>{

	/**
	 * 获取所有有对应商品类别的供应商信息
	 * @return 有商品类别的供应商信息
	 */
	public List<SupplierModel> getHasType();

}
