package cn.itcast.erp.utils.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import cn.itcast.erp.auth.emp.vo.EmpModel;

public class LoginInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 6199634433012228331L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
//		判断用户是否已经登录
//		1.获取用户信息
		EmpModel empModel = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		
//		2.如果用户为登录操作，放行
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String method = invocation.getProxy().getMethod();
		String allName = actionName + "." + method;	// action的全路径名，唯一
		if("cn.itcast.erp.auth.emp.web.EmpAction.login".equals(allName)) {
			invocation.invoke();
		}
		
//		3.如果用户未登录，则跳转登录页面
		if(empModel==null) {
			return "noLogin";
		}
		return invocation.invoke();
	}

}
