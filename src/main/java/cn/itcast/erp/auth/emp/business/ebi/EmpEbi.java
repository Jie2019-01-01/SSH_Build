package cn.itcast.erp.auth.emp.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

// Enterprice Business interface
@Transactional
public interface EmpEbi extends BaseEbi<EmpModel, EmpQueryModel>{

	public EmpModel login(String userName, String pwd);

}
