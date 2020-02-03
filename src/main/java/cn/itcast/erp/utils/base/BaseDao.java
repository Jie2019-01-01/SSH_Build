package cn.itcast.erp.utils.base;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface BaseDao<T> {
	
	// 增
	public void save(T t);

	// 查全部
	public List<T> getAll();

	// 根据id查
	public T get(Long uuid);

	// 改
	public void update(T t);
	
	// 删
	public void delete(T t);

	// 分页查
	public List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount);

	// 统计
	public Integer getCount(BaseQueryModel bqm);
}
