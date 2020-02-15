package cn.itcast.erp.invoice.order.web;

import java.util.List;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.order.business.ebi.OrderEbi;
import cn.itcast.erp.invoice.order.vo.OrderModel;
import cn.itcast.erp.invoice.order.vo.OrderQueryModel;
import cn.itcast.erp.invoice.supplier.business.ebi.SupplierEbi;
import cn.itcast.erp.invoice.supplier.vo.SupplierModel;
import cn.itcast.erp.utils.base.BaseAction;

public class OrderAction extends BaseAction{

	private static final long serialVersionUID = 756695487440L;
	
	public OrderModel om = new OrderModel();
	public OrderQueryModel oqm = new OrderQueryModel();

	private OrderEbi orderEbi;
	private SupplierEbi supplierEbi;
	private GoodsTypeEbi goodsTypeEbi;
	private GoodsEbi goodsEbi;
	private EmpEbi empEbi;
	public void setEmpEbi(EmpEbi empEbi) {this.empEbi = empEbi;}
	public void setSupplierEbi(SupplierEbi supplierEbi) {this.supplierEbi = supplierEbi;}
	public void setGoodsTypeEbi(GoodsTypeEbi goodsTypeEbi) {this.goodsTypeEbi = goodsTypeEbi;}
	public void setGoodsEbi(GoodsEbi goodsEbi) {this.goodsEbi = goodsEbi;}
	public void setOrderEbi(OrderEbi orderEbi) {this.orderEbi = orderEbi;}
	

	//列表
	public String list(){
		setRecords(orderEbi.getCount(oqm));
		List<OrderModel> orderList = orderEbi.getAll(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return LIST;
	}

	//到添加
	public String input(){
		if(om.getUuid()!=null){
			om = orderEbi.get(om.getUuid());
		}
		return INPUT;
	}

	//添加
	public String save(){
		if(om.getUuid() == null){
			orderEbi.save(om);
		}else{
			orderEbi.update(om);
		}
		return TO_LIST;
	}

	//删除
	public String delete(){
		orderEbi.delete(om);
		return TO_LIST;
	}

	//----------采购------------------------
	public String buyList() {
		setRecords(orderEbi.getCount(oqm));
		List<OrderModel> orderBuyList = orderEbi.getBuyAll(oqm,pageNum,pageCount);
		put("orderBuyList", orderBuyList);
		return "buyList";
	}
	
	public String buyInput() {
		// 查询包含商品类别、并且商品类别又包含商品的供应商信息
		List<SupplierModel> supplierList = supplierEbi.getHasTypeAndGoods();
		// 加载第一个供应商的、同时包含商品的、商品分类信息
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getBySm(supplierList.get(0).getUuid());
		// 加载第一个商品分类的商品信息
		List<GoodsModel> goodsList = goodsEbi.getByGtm(goodsTypeList.get(0).getUuid());
		
		put("supplierList",supplierList);
		put("goodsTypeList",goodsTypeList);
		put("goodsList",goodsList);
		return "buyInput";
	}
	
	public Long[] goodsUuids;
	public Integer[] nums;
	public Double[] prices;
	public String buySave() {
		orderEbi.saveBuyOrder(om, goodsUuids, nums, prices, getLogin());
		return "toBuyList";
	}
	
	public String buyDetail() {
		om = orderEbi.get(om.getUuid());
		return "buyDetail";
	}
	
	//----------采购审批---------------------------------
	// 显示审核列表
	public String buyCheckList() {
		Integer records = orderEbi.getCountBuyCheck(oqm);
		setRecords(records);
		List<OrderModel> orderList = orderEbi.getAllBuyCheck(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "buyCheckList";
	}
	
	// 进入审核详情页
	public String buyCheckDetail() {
		om = orderEbi.get(om.getUuid());
		return "buyCheckDetail";
	}
	
	// 审核通过
	public String buyCheckPass() {
		orderEbi.buyCheckPass(om.getUuid(), getLogin());
		return "toBuyCheckList";
	}
	// 审核驳回
	public String buyCheckNoPass() {
		orderEbi.buyCheckNoPass(om.getUuid(), getLogin());
		return "toBuyCheckList";
	}
	
	// --------------任务运输---------------------
	public String taskList() {
		Integer records = orderEbi.getCountTask(oqm);
		setRecords(records);
		List<OrderModel> orderList = orderEbi.getAllTask(oqm,pageNum,pageCount);
		put("orderList", orderList);
		return "taskList";
	}
	
	public String taskDetail() {
		om = orderEbi.get(om.getUuid());
		// 获取运输部门员工,能访问这个方法的必是运输部门的人
		List<EmpModel> empList = empEbi.getByDep(getLogin().getDm().getUuid());
		put("empList", empList);
		return "taskDetail";
	}
	
	public String assign() {
		orderEbi.assignTask(om.getUuid(), om.getCompleter());
		return "toTaskList";
	}
	
	// --------------任务查询---------------------
	public String queryTaskList() {
		// 只查询当前登录人的订单任务
		Integer records = orderEbi.getCountQuery(oqm, getLogin());
		setRecords(records);
		List<OrderModel> orderList = orderEbi.getAllQuery(oqm, pageNum, pageCount, getLogin());
		put("orderList", orderList);
		return "queryTaskList";
	}
	
	public String queryDetail() {
		om = orderEbi.get(om.getUuid());
		return "queryDetail";
	}
	
	public String finished() {
		// 接收om.uuid
		orderEbi.statement(om);
		return "toQueryList";
	}
	
	
	
	
	
	
	//-------------ajax----------------------------
	public Long supplierUuid;
	public Long goodsTypeUuid;
	public Long goodsUuid;
	public String ajaxGetGtmAndGm() {
		// 通过供应商uuid查询商品类别信息
		gtmList = goodsTypeEbi.getBySm(supplierUuid);
		// 通过商品类别uuid查询商品信息
		if(gtmList.size()>0) {
			gmList = goodsEbi.getByGtm(gtmList.get(0).getUuid());
			if(gmList.size()>0) {
				inPriceView = gmList.size()==0? "": gmList.get(0).getInPriceView();
			}
		}
		return "ajaxGetGtmAndGm";
	}
	
	public String used;
	public String ajaxGetGtmAndGm2() {
		// 通过供应商uuid查询商品类别信息
		gtmList = goodsTypeEbi.getBySm(supplierUuid);
		// 通过商品类别uuid查询商品信息
		if(gtmList.size()>0) {
			// 取出每一个商品类别，判断对应的分类是否全部添加了，是则从集合中移除该分类，不返回前端
			Gtms:
			for(int i=gtmList.size()-1; i>=0; i--) {
				List<GoodsModel> gmTemps = goodsEbi.getByGtm(gtmList.get(i).getUuid());
				for(GoodsModel gmTemp: gmTemps) {
					//方法一：包含已使用的id，则判断下一个下一个商品id，所有商品id都包含，则删除该类别
					//方法二：不包含已使用的id，则该类别保留，循环判断下一个类别
					if(!used.contains("@"+gmTemp.getUuid()+"@")) {
						continue Gtms;
					}
				}
				//如果这里执行，表示没有走上面循环中的if,则应该删除该类别
				gtmList.remove(i);
			}
			
			gmList = goodsEbi.getByGtm(gtmList.get(0).getUuid());
			if(gmList.size()>0) {
				// 在集合中去除已添加的商品信息
				for(int i=gmList.size()-1; i>=0; i--) {
					if(used!=null && used.contains("@"+gmList.get(i).getUuid()+"@")) {
						gmList.remove(i);
					}
				}
				inPriceView = gmList.size()==0? "": gmList.get(0).getInPriceView();
			}
		}
		return "ajaxGetGtmAndGm";
	}
	
	public String ajaxGetGm(){
		// 通过商品类别uuid查询商品信息
		gmList = goodsEbi.getByGtm(goodsTypeUuid);
		if(gmList.size()>0) {
			// 过滤已添加过的商品
			for(int i=gmList.size()-1; i>=0; i--) {
				//通过used和每个商品的uuid来判断哪些需要过滤，哪些需要返回
				//方法一：used不包含gmUuid，则跳过；若包含，则删除
				//方法二：used包含gmUuid，则删除；若不包含，则跳过
				if(used.contains("@"+gmList.get(i).getUuid()+"@"))
					gmList.remove(i);
			}
			// 为商品进价赋值
			inPriceView = gmList.get(0).getInPriceView();
		}
		return "ajaxGetGm";
	}
	
	public String ajaxGetInPrice() {
		// 查询商品信息
		GoodsModel gm = goodsEbi.get(goodsUuid);
		inPriceView = gm.getInPriceView();
		return "ajaxGetInPrice";
	}
	
	private List<GoodsTypeModel> gtmList;
	private List<GoodsModel> gmList;
	private String inPriceView;
	public String getInPriceView() {return inPriceView;}
	public List<GoodsTypeModel> getGtmList() {return gtmList;}
	public List<GoodsModel> getGmList() {return gmList;}
	
}





