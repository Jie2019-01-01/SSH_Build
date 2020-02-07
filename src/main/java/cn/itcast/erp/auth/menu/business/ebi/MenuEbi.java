package cn.itcast.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel,MenuQueryModel>{

	/**
	 * 获取所有的一级菜单
	 * @return
	 */
	public List<MenuModel> getAllOneLevel();

	public void update(MenuModel mm, Long[] roleUuids);

	public void save(MenuModel mm, Long[] roleUuids);

	/**
	 * 通过员工获取所有的一级菜单，不包含系统菜单
	 * @return
	 */
	public List<MenuModel> getAllOneLevelByEmp(EmpModel emp);

	/**
	 * 获取员工对应菜单下的子菜单，通过员工和菜单的父uuid
	 * @param emp 员工数据模型
	 * @param puuid 菜单的父uuid
	 * @return
	 */
	public List<MenuModel> getByEmpAndPuuid(EmpModel emp, Long puuid);

}
