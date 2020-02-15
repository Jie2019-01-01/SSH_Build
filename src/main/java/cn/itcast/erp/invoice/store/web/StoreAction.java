package cn.itcast.erp.invoice.store.web;

import java.util.List;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.store.business.ebi.StoreEbi;
import cn.itcast.erp.invoice.store.vo.StoreModel;
import cn.itcast.erp.invoice.store.vo.StoreQueryModel;
import cn.itcast.erp.utils.base.BaseAction;

public class StoreAction extends BaseAction{
	
	public StoreModel sm = new StoreModel();
	public StoreQueryModel sqm = new StoreQueryModel();

	private StoreEbi storeEbi;
	private EmpEbi empEbi;
	public void setEmpEbi(EmpEbi empEbi) {
		this.empEbi = empEbi;
	}
	public void setStoreEbi(StoreEbi storeEbi) {
		this.storeEbi = storeEbi;
	}

	//列表
	public String list(){
		setRecords(storeEbi.getCount(sqm));
		List<StoreModel> storeList = storeEbi.getAll(sqm,pageNum,pageCount);
		put("storeList", storeList);
		return LIST;
	}

	//到添加
	public String input(){
		if(sm.getUuid()!=null){
			sm = storeEbi.get(sm.getUuid());
		}
		List<EmpModel> empList = empEbi.getByDep(getLogin().getDm().getUuid());
		put("empList", empList);
		return INPUT;
	}

	//添加
	public String save(){
		if(sm.getUuid() == null){
			storeEbi.save(sm);
		}else{
			storeEbi.update(sm);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		storeEbi.delete(sm);
		return TO_LIST;
	}

}
