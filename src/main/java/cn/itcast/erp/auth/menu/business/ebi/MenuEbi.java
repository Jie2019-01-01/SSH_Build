package cn.itcast.erp.auth.menu.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
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

}
