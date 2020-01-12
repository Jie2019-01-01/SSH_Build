package cn.itcast.erp.auth.dep.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.auth.dep.vo.DepModel;

@Transactional
public interface DepEbi {

	public void save(DepModel dm);

}
