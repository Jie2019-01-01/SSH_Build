package cn.itcast.erp.utils.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	
	private Class<T> entityClass;
	// 获取T的泛型
	public BaseDaoImpl() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		entityClass = (Class)params[0];
	}
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}
	
	public T get(Long uuid) {
		return this.getHibernateTemplate().get(entityClass, uuid);
	}

	public List<T> getAll() {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public List<T> getAll(BaseQueryModel bqm, Integer pageNum, Integer pageCount) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		doQbc(bqm, dc);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc, (pageNum*1-1)*pageCount, pageCount);
	}

	public Integer getCount(BaseQueryModel bqm) {
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		dc.setProjection(Projections.rowCount());
		doQbc(bqm, dc);
		Long size = (Long)this.getHibernateTemplate().findByCriteria(dc).get(0);
		return size.intValue();
	}

	public abstract void doQbc(BaseQueryModel bqm, DetachedCriteria dc);
}
