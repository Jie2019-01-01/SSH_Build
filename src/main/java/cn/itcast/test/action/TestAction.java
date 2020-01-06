package cn.itcast.test.action;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import cn.itcast.test.service.impl.TestServiceImpl;

@Controller
public class TestAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Resource
	private TestServiceImpl testService;

	@Override
	public String execute() throws Exception {
		testService.test();
		System.out.println("514654684");
		return SUCCESS;
	}
}
