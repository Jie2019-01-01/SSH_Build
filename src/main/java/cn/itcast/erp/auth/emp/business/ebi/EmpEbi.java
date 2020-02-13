package cn.itcast.erp.auth.emp.business.ebi;

import java.util.List;

import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

public interface EmpEbi extends BaseEbi<EmpModel, EmpQueryModel>{
	
	public EmpModel login(String userName, String pwd);

	public boolean changePwd(String userName, String pwd, String newPwd);

	public void save(EmpModel em, Long[] roleUuids);

	public void update(EmpModel em, Long[] roleUuids);

	public List<EmpModel> getByDep(Long depUuid);
}