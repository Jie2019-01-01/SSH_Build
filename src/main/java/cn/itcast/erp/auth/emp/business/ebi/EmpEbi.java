package cn.itcast.erp.auth.emp.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.emp.vo.EmpModel;

// Enterprice Business interface
@Transactional
public interface EmpEbi {

	public EmpModel login(String userName, String pwd);

}
