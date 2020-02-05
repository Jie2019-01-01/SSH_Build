package cn.itcast.erp.auth.role.business.ebo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.dao.dao.RoleDao;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.auth.role.vo.RoleQueryModel;

public class RoleEbo implements RoleEbi{

	private RoleDao roleDao;

	public void setRoleDao(RoleDao roleDao) {this.roleDao = roleDao;}

	public void delete(RoleModel rm) {
		roleDao.delete(rm);
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

	public void save(RoleModel rm, Long[] resesUuids) {
		Set<ResModel> reses = new HashSet<ResModel>();
		for(Long uuid: resesUuids) {
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		roleDao.save(rm);
	}

	public void update(RoleModel rm, Long[] resesUuids) {
		Set<ResModel> reses = new HashSet<ResModel>();
		for(Long uuid: resesUuids) {
			ResModel temp = new ResModel();
			temp.setUuid(uuid);
			reses.add(temp);
		}
		rm.setReses(reses);
		roleDao.update(rm);
	}
	// 废弃
	public void save(RoleModel rm) {}
	public void update(RoleModel rm) {}

}