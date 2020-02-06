package cn.itcast.erp.auth.menu.dao.dao;

import java.util.List;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface MenuDao extends BaseDao<MenuModel> {

	/**
	 * 获取puuid=0或者puuid=1的数据
	 * @return
	 */
	public List<MenuModel> getPuuidIsOneOrZero();

}
