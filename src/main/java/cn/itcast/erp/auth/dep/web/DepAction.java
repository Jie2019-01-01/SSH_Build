package cn.itcast.erp.auth.dep.web;

import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import cn.itcast.erp.auth.dep.business.ebi.DepEbi;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.dep.vo.DepQueryModel;

public class DepAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private DepEbi depEbi;
	public void setDepEbi(DepEbi depEbi) {
		this.depEbi = depEbi;
	}
	
	public DepModel dm = new DepModel();
	public DepQueryModel dqm = new DepQueryModel();
	
	public Integer pageNum = 1;	// 当前页码
	public Integer pageCount = 2; // 每页显示数量
	public Integer lastPage; // 尾页
	public Integer records;
	
	// 跳转部门管理首页
	public String list() {
		// 获取数据总量,为了确保与下面条件查询相符，这里也一定要加上条件
		records = depEbi.getCount(dqm);
		lastPage = (records+pageCount-1)/pageCount;
		// 调用业务层，获取部门数据，在list页面中显示
		List<DepModel> depList = depEbi.getAll(dqm, pageNum, pageCount);
		ActionContext.getContext().put("depList", depList);
		return "list";
	}
	
	// 跳转添加页面
	public String input() {
		// 通过uuid的值来确定是修改还是添加操作
		if(dm.getUuid()!=null) {
			// 修改操作
			dm = depEbi.get(dm.getUuid());
		}
		return "input";
	}
	
	// 信息输入之后的保存操作，然后跳转到list
	public String save() {
		// 判断是新增还是修改，根据dm.getUuid()的值
		if(dm.getUuid()==null) {
			// 新增
			depEbi.save(dm);
		}else {
			// 修改
			depEbi.update(dm);
		}
		// 重新加载框架时会出现保存操作，所以这里设置重定向
		return "toList";
	}
	
	// 删除
	public String delete() {
		depEbi.delete(dm);
		return "toList";
	}
}
