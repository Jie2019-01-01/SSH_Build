package cn.itcast.erp.utils.base;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import cn.itcast.erp.auth.emp.vo.EmpModel;

public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	protected static final String LIST = "list";
	protected static final String TO_LIST = "toList";
	protected static final String INPUT = "input";
	
	public Integer records;
	public Integer pageNum = 1;
	public Integer pageCount = 2;
	public Integer lastPage;
	
	// 存放数据到request
	protected void put(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}
	// 存放数据到session
	protected void putSession(String key, Object value) {
		ActionContext.getContext().getSession().put(key, value);
	}
	
	// 获取登录人信息
	protected EmpModel getLogin() {
		return (EmpModel) ActionContext.getContext().getSession().get(EmpModel.EMP_LOGIN_USER_OBJECT_NAME);
	}
	
	// 定义分页，初始化相关操作
	protected void setRecords(Integer records) {
		this.records = records;
		this.lastPage = (this.records + pageCount - 1) / pageCount;
		// 页码值初始化错误
//		if(pageNum<1) pageNum = 1;
//		if(pageNum>pageCount) pageNum = pageCount;
	}
	
	public String getActionName() {
		// 以部门模块为例
		String action = this.getClass().getSimpleName(); // DepAction
		String temp = action.substring(0, action.length()-6); // Dep
		String actionName = temp.substring(0,1).toLowerCase() + temp.substring(1); // dep
		return actionName;
	}
}
