package cn.itcast.erp.auth.dep.business.ebo;

import java.util.List;

import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.dao.dao.DepDao;
import cn.itcast.erp.auth.dep.vo.DepModel;

public class DepEbo implements DepEbi{

	private DepDao depDao;

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}

	public void save(DepModel dm) {
		depDao.save(dm);
	}

	public List<DepModel> getAll() {
		return depDao.getAll();
	}
}
