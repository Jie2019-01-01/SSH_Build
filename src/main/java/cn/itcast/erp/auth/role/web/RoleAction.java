package cn.itcast.erp.auth.role.web;

import java.util.List;
import cn.itcast.erp.auth.role.business.ebi.RoleEbi;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.auth.role.vo.RoleQueryModel;
import cn.itcast.erp.utils.base.BaseAction;

public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	private RoleEbi roleEbi;
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
		if(rm.getUuid()!=null) {
			rm = roleEbi.get(rm.getUuid());
		}
		return INPUT;
	}

	// 添加
	public String save() {
		if(rm.getUuid()==null) {
			roleEbi.save(rm);
		}else {
			roleEbi.update(rm);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		roleEbi.delete(rm);
		return TO_LIST;
	}
}
