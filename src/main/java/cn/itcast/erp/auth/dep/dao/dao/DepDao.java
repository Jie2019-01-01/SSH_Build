package cn.itcast.erp.auth.dep.dao.dao;

import java.util.List;

import cn.itcast.erp.auth.dep.vo.DepModel;

public interface DepDao {

	public void save(DepModel dm);

	public List<DepModel> getAll();

}
