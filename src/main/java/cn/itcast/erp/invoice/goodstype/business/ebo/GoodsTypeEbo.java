package cn.itcast.erp.invoice.goodstype.business.ebo;

import java.util.List;
import cn.itcast.erp.invoice.goodstype.business.ebi.GoodsTypeEbi;
import cn.itcast.erp.invoice.goodstype.dao.dao.GoodsTypeDao;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeModel;
import cn.itcast.erp.invoice.goodstype.vo.GoodsTypeQueryModel;

public class GoodsTypeEbo implements GoodsTypeEbi{
	
	private GoodsTypeDao goodsTypeDao;
	public void setGoodsTypeDao(GoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
	}

	public void save(GoodsTypeModel gm) {
		goodsTypeDao.save(gm);
	}

	public void update(GoodsTypeModel gm) {
		goodsTypeDao.update(gm);
	}

	public void delete(GoodsTypeModel gm) {
		goodsTypeDao.delete(gm);
	}

	public GoodsTypeModel get(Long uuid) {
		return goodsTypeDao.get(uuid);
	}

	public List<GoodsTypeModel> getAll() {
		return goodsTypeDao.getAll();
	}

	public List<GoodsTypeModel> getAll(GoodsTypeQueryModel gqm, Integer pageNum,Integer pageCount) {
		return goodsTypeDao.getAll(gqm,pageNum,pageCount);
	}

	public Integer getCount(GoodsTypeQueryModel gqm) {
		return goodsTypeDao.getCount(gqm);
	}

	public List<GoodsTypeModel> getAllBySm(Long uuid) {
		return goodsTypeDao.getAllBySm(uuid);
	}
}
