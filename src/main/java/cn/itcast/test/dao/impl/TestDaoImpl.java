package cn.itcast.test.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.itcast.test.dao.TestDao;
import cn.itcast.test.entity.Person;

@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(Person person) {
		Session session = sessionFactory.openSession();
		session.save(person);
	}

}
