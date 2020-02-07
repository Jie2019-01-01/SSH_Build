package cn.itcast.erp.auth.res.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.erp.auth.res.dao.dao.ResDao;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class ResImpl extends BaseDaoImpl<ResModel> implements ResDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		// TODO: 自定义查询条件
	}

	public List<ResModel> getResByEmpId(Long uuid) {
		String hql = "select distinct res from EmpModel em join em.roles re join re.reses res where em.uuid=:uuid";
		List<ResModel> resList = (List<ResModel>) this.getHibernateTemplate().findByNamedParam(hql, "uuid", uuid);
		return resList;
	}
}
