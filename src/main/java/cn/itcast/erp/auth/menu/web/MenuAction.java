package cn.itcast.erp.auth.menu.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	
	//菜单列表
	public void showMenu() throws IOException {
		HttpServletResponse res = getResponse();
		res.setContentType("text/html;charset=utf-8");
		StringBuilder json = new StringBuilder();
		json.append("[");
		String root = getRequest().getParameter("root");
		if("source".equals(root)) {
				// 一级菜单
			List<MenuModel> menuList = menuEbi.getAllOneLevelByEmp(getLogin());
			for(MenuModel menu:menuList) {
				json.append("{\"text\":\"");
				json.append(menu.getName());
				json.append("\", \"hasChildren\": true, \"classes\":\"folder\",\"id\":");
				json.append(menu.getUuid());
				json.append("},");
			}
		}else {
			List<MenuModel> menuList = menuEbi.getByEmpAndPuuid(getLogin(),new Long(root));
			// 非一级菜单
			for(MenuModel menu:menuList) {
				json.append("{\"text\":\"");
				json.append("<a class='hei' target='main' href='"+menu.getUrl()+"'>");
				json.append(menu.getName());
				json.append("</a>");
				json.append("\", \"classes\":\"file\"},");
			}
		}
		json.deleteCharAt(json.length()-1);
		json.append("]");
		PrintWriter pw = res.getWriter();
		pw.write(json.toString());
		pw.flush();
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
