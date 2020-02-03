package cn.itcast.erp.auth.res.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.res.vo.ResQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;
import cn.itcast.erp.auth.res.dao.dao.ResDao;

public class ResImpl extends BaseDaoImpl<ResModel> implements ResDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		// TODO: 自定义查询条件
	}
}
