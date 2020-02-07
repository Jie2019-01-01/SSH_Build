package cn.itcast.erp.auth.menu.business.ebo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.dao.dao.MenuDao;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class MenuEbo implements MenuEbi{
	private MenuDao menuDao;
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void delete(MenuModel mm) {
		MenuModel temp = menuDao.get(mm.getUuid());
		menuDao.delete(temp);
	}

	public MenuModel get(Long uuid) {
		return menuDao.get(uuid);
	}

	public List<MenuModel> getAll() {
		return menuDao.getAll();
	}

	public List<MenuModel> getAll(MenuQueryModel qm, Integer pageNum,Integer pageCount) {
		return menuDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(MenuQueryModel qm) {
		return menuDao.getCount(qm);
	}

	public List<MenuModel> getAllOneLevel() {
		return menuDao.getPuuidIsOneOrZero();
	}

	// 废弃
	public void save(MenuModel mm) {
		menuDao.save(mm);
	}
	public void save(MenuModel mm, Long[] roleUuids) {
		//Long>set
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid: roleUuids) {
			RoleModel temp = new RoleModel();
			temp.setUuid(uuid);
			roles.add(temp);
		}
		mm.setRoles(roles);
		menuDao.save(mm);
	}
	
	// 废弃
	public void update(MenuModel mm) {
		menuDao.update(mm);
	}
	public void update(MenuModel mm, Long[] roleUuids) {
		MenuModel temp = menuDao.get(mm.getUuid());
		temp.setName(mm.getName());
		temp.setUrl(mm.getUrl());
		Set<RoleModel> roles = new HashSet<RoleModel>();
		for(Long uuid: roleUuids) {
			RoleModel rm = new RoleModel();
			rm.setUuid(uuid);
			roles.add(rm);
		}
		temp.setRoles(roles);
	}

	public List<MenuModel> getAllOneLevelByEmp(EmpModel emp) {
		return menuDao.getAllOneLevelByEmp(emp.getUuid());
	}

	public List<MenuModel> getByEmpAndPuuid(EmpModel emp, Long puuid) {
		return menuDao.getByEmpAndPuuid(emp.getUuid(), puuid);
	}
}
