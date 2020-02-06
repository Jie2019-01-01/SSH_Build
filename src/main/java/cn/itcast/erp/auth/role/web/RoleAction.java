package cn.itcast.erp.auth.role.web;

import java.util.List;
import cn.itcast.erp.auth.menu.business.ebi.MenuEbi;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.auth.role.vo.RoleQueryModel;
import cn.itcast.erp.utils.base.BaseAction;

public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	private RoleEbi roleEbi;
	private ResEbi resEbi;
	private MenuEbi menuEbi;
	public void setMenuEbi(MenuEbi menuEbi) {this.menuEbi = menuEbi;}
	public void setResEbi(ResEbi resEbi) {this.resEbi = resEbi;}
	public void setRoleEbi(RoleEbi roleEbi) {this.roleEbi = roleEbi;}

	public RoleModel rm = new RoleModel();
	public RoleQueryModel rqm = new RoleQueryModel();

	// 跳转到列表页
	public String list() {
		setRecords(roleEbi.getCount(rqm));
		List<RoleModel> roleList = roleEbi.getAll(rqm, pageNum, pageCount);
		put("roleList", roleList);
		return LIST;
	}

	// 跳转到input.jsp
	public String input() {
		put("resList", resEbi.getAll());
		put("menuList", menuEbi.getAll());
		if(rm.getUuid()!=null) {
			// 资源回显
			rm = roleEbi.get(rm.getUuid());
			int i = 0;
			resesUuids = new Long[rm.getReses().size()];
			for(ResModel temp: rm.getReses()) {
				resesUuids[i++] = temp.getUuid();
			}
			// 菜单回显
			menuUuids = new Long[rm.getMenus().size()];
			i = 0;
			for(MenuModel temp: rm.getMenus()) {
				menuUuids[i++] = temp.getUuid();
			}
		}
		return INPUT;
	}

	public Long[] resesUuids;
	public Long[] menuUuids;
	// 添加
	public String save() {
		if(rm.getUuid()==null) {
			roleEbi.save(rm, resesUuids, menuUuids);
		}else {
			roleEbi.update(rm, resesUuids, menuUuids);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		roleEbi.delete(rm);
		return TO_LIST;
	}
}
