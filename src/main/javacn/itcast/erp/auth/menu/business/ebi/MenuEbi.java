package cn.itcast.erp.auth.menu.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.menu.vo.MenuModel;
import cn.itcast.erp.util.base.BaseEbi;

@Transactional
public interface MenuEbi extends BaseEbi<MenuModel>{

}
