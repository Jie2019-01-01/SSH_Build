package cn.itcast.erp.utils.interceptor;

import java.util.List;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.utils.exception.AppException;

/**
 * 权限校验拦截器
 * @author liuweijie
 */
public class AuthInterceptor extends AbstractInterceptor {

	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {
		this.resEbi = resEbi;
	}


	public String intercept(ActionInvocation invocation) throws Exception {
			//1.get当前要执行的操作
		String actionName = invocation.getProxy().getAction().getClass().getName();
		String methodName = invocation.getProxy().getMethod();
		String allName = actionName +"."+ methodName;
		
			//2.get当前登录人
		EmpModel loginEm = (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
		
			//3判断只要不是资源操作，全部放行
			//3.1如果资源拼接的字符串中没有allName,则放行
		String allRes = (String) ServletActionContext.getServletContext().getAttribute("allRes");
		if(!allRes.contains(allName))
			return invocation.invoke();
		
			//4.获取员工包含的权限(资源-角色-员工)
		List<ResModel> rmList = resEbi.getResByEmpId(loginEm.getUuid());
			//4.1当前登录人是否有权限执行当前操作
		for(ResModel rm: rmList) {
			if(rm.getUrl().equals(allName)) {
				return invocation.invoke();
			}
		}
		throw new AppException("对不起，您没有该操作的权限，请不要执行非法操作！");
	}
}
