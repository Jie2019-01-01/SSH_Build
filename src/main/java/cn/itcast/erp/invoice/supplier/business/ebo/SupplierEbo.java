package cn.itcast.erp.invoice.supplier.business.ebo;

import java.util.List;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.dao.dao.SupplierDao;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.invoice.supplier.vo.SupplierQueryModel;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class SupplierEbo implements SupplierEbi{
	private SupplierDao supplierDao;
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void save(SupplierModel sm) {
		supplierDao.save(sm);
	}

	public void update(SupplierModel sm) {
		supplierDao.update(sm);
	}

	public void delete(SupplierModel sm) {
		supplierDao.delete(sm);
	}
	public List<SupplierModel> getAll() {
		return supplierDao.getAll();
	}

	public List<SupplierModel> getAll(SupplierQueryModel sqm, Integer pageNum,Integer pageCount) {
		return supplierDao.getAll(sqm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return supplierDao.getCount(qm);
	}

	public SupplierModel get(Long uuid) {
		return supplierDao.get(uuid);
	}

	public Integer getCount(SupplierQueryModel sqm) {
		return supplierDao.getCount(sqm);
	}

	public List<SupplierModel> getHasType() {
		return supplierDao.getHasType();
	}

	public List<SupplierModel> getHasTypeAndGoods() {
		return supplierDao.getHasTypeAndGoods();
	}

}
