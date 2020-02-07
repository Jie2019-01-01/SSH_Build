package cn.itcast.erp.invoice.supplier.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import cn.itcast.erp.invoice.supplier.dao.dao.SupplierDao;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class SupplierImpl extends BaseDaoImpl<SupplierModel> implements SupplierDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		
	}

}
