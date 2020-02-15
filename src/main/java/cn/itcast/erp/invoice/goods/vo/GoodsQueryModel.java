package cn.itcast.erp.invoice.goods.vo;

import cn.itcast.erp.utils.base.BaseQueryModel;

public class GoodsQueryModel extends GoodsModel implements BaseQueryModel{
	
	private Double maxInPrice; // 进货上限
	private Double maxOutPrice; // 销售上限
	public Double getMaxInPrice() {
		return maxInPrice;
	}
	public void setMaxInPrice(Double maxInPrice) {
		this.maxInPrice = maxInPrice;
	}
	public Double getMaxOutPrice() {
		return maxOutPrice;
	}
	public void setMaxOutPrice(Double maxOutPrice) {
		this.maxOutPrice = maxOutPrice;
	}
}
