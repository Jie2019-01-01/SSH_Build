package cn.itcast.test.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.test.service.TestService;

@Controller
@Scope("prototype")
public class TestAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	@Autowired
	private TestService testService;

	@Override
	public String execute() throws Exception {
		testService.save(null);
		System.out.println("514654684");
		return SUCCESS;
	}
}
