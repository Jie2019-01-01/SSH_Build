package cn.itcast.erp.auth.role.business.ebo;

import java.util.List;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.dao.dao.RoleDao;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.auth.role.vo.RoleQueryModel;

public class RoleEbo implements RoleEbi{

	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {this.roleDao = roleDao;}

	public void save(RoleModel dm) {
		roleDao.save(dm);
	}

	public void update(RoleModel dm) {
		roleDao.update(dm);
	}

	public void delete(RoleModel dm) {
		roleDao.delete(dm);
	}

	public List<RoleModel> getAll() {
		return roleDao.getAll();
	}

	public RoleModel get(Long uuid) {
		return roleDao.get(uuid);
	}

	public List<RoleModel> getAll(RoleQueryModel dqm, Integer pageNum, Integer pageCount) {
		return roleDao.getAll(dqm,pageNum,pageCount);
	}

	public Integer getCount(RoleQueryModel dqm) {
		return roleDao.getCount(dqm);
	}

}