package cn.itcast.erp.auth.res.dao.dao;

import java.util.List;

import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.utils.base.BaseDao;

public interface ResDao extends BaseDao<ResModel>{

	public List<ResModel> getResByEmpId(Long uuid);}	
