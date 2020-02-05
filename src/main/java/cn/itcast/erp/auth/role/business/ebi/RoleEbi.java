package cn.itcast.erp.auth.role.business.ebi;

import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.auth.role.vo.RoleQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

public interface RoleEbi extends BaseEbi<RoleModel, RoleQueryModel>{

	public void save(RoleModel rm, Long[] resesUuids);

	public void update(RoleModel rm, Long[] resesUuids);}
