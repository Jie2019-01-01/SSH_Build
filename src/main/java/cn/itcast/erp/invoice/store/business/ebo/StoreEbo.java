package cn.itcast.erp.invoice.store.business.ebo;

import java.util.List;
import cn.itcast.erp.invoice.store.business.ebi.StoreEbi;
import cn.itcast.erp.invoice.store.dao.dao.StoreDao;
import cn.itcast.erp.invoice.store.vo.StoreModel;
import cn.itcast.erp.invoice.store.vo.StoreQueryModel;

public class StoreEbo implements StoreEbi{
	
	private StoreDao storeDao;
	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void save(StoreModel sm) {
		storeDao.save(sm);
	}

	public void update(StoreModel sm) {
		storeDao.update(sm);
	}

	public void delete(StoreModel sm) {
		storeDao.delete(sm);
	}

	public StoreModel get(Long uuid) {
		return storeDao.get(uuid);
	}

	public List<StoreModel> getAll() {
		return storeDao.getAll();
	}

	public List<StoreModel> getAll(StoreQueryModel qm, Integer pageNum,Integer pageCount) {
		return storeDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(StoreQueryModel qm) {
		return storeDao.getCount(qm);
	}
}
