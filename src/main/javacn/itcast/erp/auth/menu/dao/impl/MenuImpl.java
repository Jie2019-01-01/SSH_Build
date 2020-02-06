package cn.itcast.erp.auth.menu.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import cn.itcast.erp.auth.menu.dao.dao.MenuDao;
import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.auth.menu.vo.MenuQueryModel;
import cn.itcast.erp.util.base.BaseImpl;
import cn.itcast.erp.util.base.BaseQueryModel;

public class MenuImpl extends BaseImpl<MenuModel> implements MenuDao{

	public void doQbc(DetachedCriteria dc,BaseQueryModel qm){
		MenuQueryModel mqm = (MenuQueryModel)qm;
		// TODO 添加自定义查询条件
	}

}
