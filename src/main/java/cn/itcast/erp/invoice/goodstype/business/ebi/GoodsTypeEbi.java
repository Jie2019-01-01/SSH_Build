package cn.itcast.erp.invoice.goodstype.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel, GoodsTypeQueryModel>{

	/**
	 * 获取供应商下的包含商品的类别信息
	 * @param uuid 供应商uuid
	 * @return 商品类别信息
	 */
	public List<GoodsTypeModel> getBySm(Long uuid);
	
	/**
	 * 获取供应商下的全部商品类别信息
	 * @param uuid 供应商uuid
	 * @return 商品类别信息
	 */
	public List<GoodsTypeModel> getAllBySm(Long uuid);
}
