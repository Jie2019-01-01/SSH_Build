package cn.itcast.erp.invoice.goods.business.ebi;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface GoodsEbi extends BaseEbi<GoodsModel, GoodsQueryModel>{

}
