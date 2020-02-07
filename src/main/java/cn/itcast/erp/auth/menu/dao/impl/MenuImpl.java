package cn.itcast.erp.auth.menu.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.menu.dao.dao.MenuDao;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.utils.base.BaseDaoImpl;
import cn.itcast.erp.utils.base.BaseQueryModel;

public class MenuImpl extends BaseDaoImpl<MenuModel> implements MenuDao{

	public void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		dc.add(Restrictions.not(Restrictions.eq("uuid", MenuModel.SYSTEM_MENU_UUID)));
		MenuQueryModel mqm = (MenuQueryModel) bqm;
		if(mqm.getName()!=null && mqm.getName().trim().length()>0) {
			dc.add(Restrictions.like("name", "%"+mqm.getName().trim()+"%"));
		}
		if(mqm.getPatent()!=null && mqm.getPatent().getUuid()!=null && mqm.getPatent().getUuid()!=-1) {
			dc.add(Restrictions.eq("patent.uuid", mqm.getPatent().getUuid()));
		}
	}

	public List<MenuModel> getPuuidIsOneOrZero() {
		String hql = "from MenuModel where puuid=:puuid or uuid=:uuid";
		return (List<MenuModel>) this.getHibernateTemplate().findByNamedParam(hql, 
				new String[]{"puuid","uuid"}, new Long[]{MenuModel.SYSTEM_MENU_UUID,MenuModel.SYSTEM_MENU_UUID});
	}

	public List<MenuModel> getAllOneLevelByEmp(Long uuid) {
		String hql = "select distinct menu from EmpModel emp join emp.roles r join r.menus menu "
				+ "where emp.uuid=:uuid and puuid=:puuid order by menu.uuid";
		String[] key = new String[]{"uuid","puuid"};
		Long[] value = new Long[] {uuid, MenuModel.SYSTEM_MENU_UUID};
		return (List<MenuModel>) this.getHibernateTemplate().findByNamedParam(hql, key, value);
	}

	public List<MenuModel> getByEmpAndPuuid(Long uuid, Long puuid) {
		String hql = "select distinct menu from EmpModel emp join emp.roles r join r.menus menu "
				+ "where emp.uuid=:uuid and puuid=:puuid order by menu.uuid";
		String[] key = new String[]{"uuid","puuid"};
		Long[] value = new Long[] {uuid, puuid};
		return (List<MenuModel>) this.getHibernateTemplate().findByNamedParam(hql, key, value);
	}
}
