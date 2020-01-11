package cn.itcast.erp.auth.dep.business.ebo;

import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.dao.dao.DepDao;

public class DepEbo implements DepEbi{

	private DepDao depDao;

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
	}
}
