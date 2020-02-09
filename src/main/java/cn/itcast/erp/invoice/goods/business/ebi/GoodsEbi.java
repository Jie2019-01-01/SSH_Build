package cn.itcast.erp.invoice.goods.business.ebi;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel, GoodsQueryModel>{

	/**
	 * 获取商品分类对应的商品信息
	 * @param gtmUuid 商品分类id
	 * @return 商品信息集合
	 */
	public List<GoodsModel> getByGtm(Long gtmUuid);

}
