package cn.itcast.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.test.action.TestAction;
import cn.itcast.test.service.TestService;

public class TestMerge {

	private ClassPathXmlApplicationContext context;
	
	@Before
	public void aa() {
		context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
	}
	
	@Test
	public void testServieDao() {
		TestAction ta = (TestAction) context.getBean("testAction");
		System.out.println(ta);
//		ts.save(new Person("人员1"));
	}
}
