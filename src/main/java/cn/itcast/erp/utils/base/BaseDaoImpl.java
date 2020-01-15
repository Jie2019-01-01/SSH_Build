package cn.itcast.erp.utils.base;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import cn.itcast.erp.auth.dep.vo.DepQueryModel;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{
	// TODO: 哈哈
	private Class<T> entityClass = null;
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	public List<T> getAll() {
		// 获取类名
//		entityClass.getSimpleName();
//		String hql = "from T";
//		return (List<T>) this.getHibernateTemplate().find(hql);
		
		DetachedCriteria dc = DetachedCriteria.forClass(entityClass);
		return (List<T>) this.getHibernateTemplate().findByCriteria(dc);
	}

	public T get(Long uuid) {
		return this.getHibernateTemplate().get(entityClass, uuid);
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
		
		doQbc(bqm, dc);
		
		Long size = (Long)this.getHibernateTemplate().findByCriteria(dc).get(0);
		return size.intValue();
	}

	private void doQbc(BaseQueryModel bqm, DetachedCriteria dc) {
		DepQueryModel dqm = (DepQueryModel)bqm;
		if(dqm.getName()!=null && dqm.getName().trim().length()>0) {
			dc.add(Restrictions.like("name", "%"+dqm.getName().trim()+"%"));
		}
		if(dqm.getTele()!=null && dqm.getTele().trim().length()>0) {
			dc.add(Restrictions.like("tele", "%"+dqm.getTele().trim()+"%"));
		}
	}
}
