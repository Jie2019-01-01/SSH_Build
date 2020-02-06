package cn.itcast.erp.auth.menu.web;

import java.util.List;
import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.utils.base.BaseAction;

public class MenuAction extends BaseAction{
	public MenuModel mm = new MenuModel();
	public MenuQueryModel mqm = new MenuQueryModel();

	private MenuEbi menuEbi;
	private RoleEbi roleEbi;
	public void setRoleEbi(RoleEbi roleEbi) {
		this.roleEbi = roleEbi;
	}
	public void setMenuEbi(MenuEbi menuEbi) {
		this.menuEbi = menuEbi;
	}

	//列表
	public String list(){
		setRecords(menuEbi.getCount(mqm));
		List<MenuModel> menuList = menuEbi.getAll(mqm,pageNum,pageCount);
		put("menuList", menuList);
		put("menuAll",menuEbi.getAllOneLevel());
		return LIST;
	}

	//到添加
	public String input(){
		List<MenuModel> menuList = menuEbi.getAllOneLevel();
		put("menuList", menuList);
		put("roleList", roleEbi.getAll());
		if(mm.getUuid()!=null){
			mm = menuEbi.get(mm.getUuid());
			// mm>array
			roleUuids = new Long[mm.getRoles().size()];
			int i = 0;
			for(RoleModel rm: mm.getRoles()) {
				roleUuids[i++] = rm.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] roleUuids;
	//添加
	public String save(){
		if(mm.getUuid() == null){
			menuEbi.save(mm,roleUuids);
		}else{
			menuEbi.update(mm,roleUuids);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		menuEbi.delete(mm);
		return TO_LIST;
	}

}
