package cn.itcast.erp.auth.res.web;

import java.util.List;
import cn.itcast.erp.auth.res.business.ebi.ResEbi;
import cn.itcast.erp.auth.res.vo.ResModel;
import cn.itcast.erp.auth.res.vo.ResQueryModel;
import cn.itcast.erp.utils.base.BaseAction;

public class ResAction extends BaseAction{

	private static final long serialVersionUID = 1L;

	private ResEbi resEbi;
	public void setResEbi(ResEbi resEbi) {this.resEbi = resEbi;}

	public ResModel rm = new ResModel();
	public ResQueryModel rqm = new ResQueryModel();

	// 跳转到列表页
	public String list() {
		setRecords(resEbi.getCount(rqm));
		List<ResModel> resList = resEbi.getAll(rqm, pageNum, pageCount);
		put("resList", resList);
		return LIST;
	}

	// 跳转到input.jsp
	public String input() {
		if(rm.getUuid()!=null) {
			rm = resEbi.get(rm.getUuid());
		}
		return INPUT;
	}

	// 添加
	public String save() {
		if(rm.getUuid()==null) {
			resEbi.save(rm);
		}else {
			resEbi.update(rm);
		}
		return TO_LIST;
	}

	// 删除
	public String delete() {
		resEbi.delete(rm);
		return TO_LIST;
	}
}
