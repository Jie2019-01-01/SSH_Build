package cn.itcast.erp.auth.menu.dao.dao;

import java.util.List;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {

	/**
	 * 获取puuid=0或者puuid=1的数据
	 * @return
	 */
	public List<MenuModel> getPuuidIsOneOrZero();

	/**
	 * 获取员工对应可见的一级菜单数据，通过员工的uuid
	 * @param uuid 员工uuid
	 * @return
	 */
	public List<MenuModel> getAllOneLevelByEmp(Long uuid);

	/**
	 * 获取员工对应的可见的子菜单，通过员工的uuid、菜单的父uuid
	 * @param uuid 员工的uuid
	 * @param puuid 菜单的父uuid
	 * @return
	 */
	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid);

}
