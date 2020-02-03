package cn.itcast.erp.auth.role.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.auth.role.vo.RoleModel;
import cn.itcast.erp.auth.role.vo.RoleQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;
import cn.itcast.erp.auth.role.dao.dao.RoleDao;

public class RoleImpl extends BaseDaoImpl<RoleModel> implements RoleDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		// TODO: 自定义查询条件
	}
}
