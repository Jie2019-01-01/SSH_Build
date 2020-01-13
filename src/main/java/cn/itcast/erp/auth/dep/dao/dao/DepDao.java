package cn.itcast.erp.auth.dep.dao.dao;

import java.util.List;

import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.dep.vo.DepQueryModel;

public interface DepDao {

	public void save(DepModel dm);

	public List<DepModel> getAll();

	public DepModel get(Long uuid);

	public void update(DepModel dm);

	public void delete(DepModel dm);

	public List<DepModel> getAll(DepQueryModel dqm);

	public List<DepModel> getAll(DepQueryModel dqm, Integer pageNum, Integer pageCount);

}
