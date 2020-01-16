package cn.itcast.erp.auth.dep.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.dep.vo.DepQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface DepEbi extends BaseEbi<DepModel, DepQueryModel>{}
