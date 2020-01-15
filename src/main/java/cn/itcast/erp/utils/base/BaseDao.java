package cn.itcast.erp.utils.base;

import java.util.List;

public interface BaseDao<T> {
	
	public void save(T t);

	public List<T> getAll();

	public T get(Long uuid);

	public void update(T t);

	public void delete(T t);

	public List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount);

	public Integer getCount(BaseQueryModel bqm);
}
