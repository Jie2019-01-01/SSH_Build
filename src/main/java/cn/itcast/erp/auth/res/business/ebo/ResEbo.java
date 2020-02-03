package cn.itcast.erp.auth.res.business.ebo;

import java.util.List;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.dao.dao.ResDao;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.res.vo.ResQueryModel;

public class ResEbo implements ResEbi{

	private ResDao resDao;

	public void setResDao(ResDao resDao) {this.resDao = resDao;}

	public void save(ResModel dm) {
		resDao.save(dm);
	}

	public void update(ResModel dm) {
		resDao.update(dm);
	}

	public void delete(ResModel dm) {
		resDao.delete(dm);
	}

	public List<ResModel> getAll() {
		return resDao.getAll();
	}

	public ResModel get(Long uuid) {
		return resDao.get(uuid);
	}

	public List<ResModel> getAll(ResQueryModel dqm, Integer pageNum, Integer pageCount) {
		return resDao.getAll(dqm,pageNum,pageCount);
	}

	public Integer getCount(ResQueryModel dqm) {
		return resDao.getCount(dqm);
	}

}