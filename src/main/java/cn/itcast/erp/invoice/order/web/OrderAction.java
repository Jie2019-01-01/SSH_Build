package cn.itcast.erp.invoice.order.web;

import java.util.List;
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
		return "buyList";
	}
	
	public String buyInput() {
		// 加载所有供应商信息
		List<SupplierModel> supplierList = supplierEbi.getAll();
		// 加载第一个供应商的商品分类信息
		List<GoodsTypeModel> goodsTypeList = goodsTypeEbi.getAllBySm(supplierList.get(0).getUuid());
		// 加载第一个商品分类的商品信息
		List<GoodsModel> goodsList = goodsEbi.getByGtm(goodsTypeList.get(0).getUuid());
		
		put("supplierList",supplierList);
		put("goodsTypeList",goodsTypeList);
		put("goodsList",goodsList);
		return "buyInput";
	}
	
	//-------------ajax----------------------------
	public Long supplierUuid;
	public Long goodsTypeUuid;
	public Long goodsUuid;
	public String ajaxGetGtmAndGm() {
		// 通过供应商uuid查询商品类别信息
		gtmList = goodsTypeEbi.getAllBySm(supplierUuid);
		// 通过商品类别uuid查询商品信息
		if(gtmList.size()>0) {
			gmList = goodsEbi.getByGtm(gtmList.get(0).getUuid());
			if(gmList.size()>0) {
				inPriceView = gmList.size()==0? "": gmList.get(0).getInPriceView();
			}
		}
		return "ajaxGetGtmAndGm";
	}
	
	public String ajaxGetGm(){
		// 通过商品类别uuid查询商品信息
		gmList = goodsEbi.getByGtm(goodsTypeUuid);
		if(gmList.size()>0) {
			inPriceView = gmList.size()==0? "": gmList.get(0).getInPriceView();
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





