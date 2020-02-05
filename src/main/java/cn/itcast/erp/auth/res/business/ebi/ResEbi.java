package cn.itcast.erp.auth.res.business.ebi;

import java.util.List;

import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.res.vo.ResQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

public interface ResEbi extends BaseEbi<ResModel, ResQueryModel>{

	public List<ResModel> getResByEmpId(Long uuid);
}
