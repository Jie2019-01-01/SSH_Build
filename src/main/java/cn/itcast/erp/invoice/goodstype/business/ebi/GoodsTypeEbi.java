package cn.itcast.erp.invoice.goodstype.business.ebi;

import org.springframework.transaction.annotation.Transactional;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeQueryModel;
import cn.itcast.erp.utils.base.BaseEbi;

@Transactional
public interface GoodsTypeEbi extends BaseEbi<GoodsTypeModel, GoodsTypeQueryModel>{

}
