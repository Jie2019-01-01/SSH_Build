package cn.itcast.erp.auth.menu.vo;

import java.util.Set;

import cn.itcast.erp.auth.role.vo.RoleModel;

public class MenuModel {

	public static final Long SYSTEM_MENU_UUID = 1L; 
	
	private Long uuid;
	private String name;
	private String url;
	
	// 对菜单多对一
	private MenuModel patent;
	
	// 对菜单一对多
	private Set<MenuModel> childrens;
	
	// 对角色多对多
	private Set<RoleModel> roles;
	
	public Set<RoleModel> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleModel> roles) {
		this.roles = roles;
	}

	public Set<MenuModel> getChildrens() {
		return childrens;
	}
	
	public void setChildrens(Set<MenuModel> childrens) {
		this.childrens = childrens;
	}

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuModel getPatent() {
		return patent;
	}

	public void setPatent(MenuModel patent) {
		this.patent = patent;
	}
}
