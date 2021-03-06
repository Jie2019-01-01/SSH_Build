package cn.itcast.erp.invoice.goods.vo;

import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.utils.format.FormatUtil;

public class GoodsModel {

	private Long uuid;
	private String name;
	private String origin;
	private String producer;
	private String unit;
	
	private Double inPrice;
	private Double outPrice;
	private String inPriceView;
	private String outPriceView;
	
	public String getInPriceView() {
		return inPriceView;
	}

	public String getOutPriceView() {
		return outPriceView;
	}

	//对商品类别多对一
	private GoodsTypeModel gtm;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getInPrice() {
		return inPrice;
	}

	public void setInPrice(Double inPrice) {
		this.inPrice = inPrice;
		this.inPriceView = FormatUtil.formatMonmey(inPrice);
	}

	public Double getOutPrice() {
		return outPrice;
	}

	public void setOutPrice(Double outPrice) {
		this.outPrice = outPrice;
		this.outPriceView = FormatUtil.formatMonmey(outPrice);
	}

	public GoodsTypeModel getGtm() {
		return gtm;
	}

	public void setGtm(GoodsTypeModel gtm) {
		this.gtm = gtm;
	}
}
