package cn.itcast.erp.utils.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import cn.itcast.erp.utils.exception.AppException;

public class ExceptionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (AppException e) {
			// 记录日志
			// 发送消息到程序员邮箱
			// 报警（根据需求）
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError(e.getMessage());
			return "error";
		}catch(Exception e) {
			ActionSupport as = (ActionSupport) invocation.getAction();
			as.addActionError("对不起，服务器已关闭，请联系管理员！");
			return "error";
		}
	}

}
