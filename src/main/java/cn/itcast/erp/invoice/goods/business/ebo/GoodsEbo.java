package cn.itcast.erp.invoice.goods.business.ebo;

import java.util.List;
import cn.itcast.erp.invoice.goods.business.ebi.GoodsEbi;
import cn.itcast.erp.invoice.goods.dao.dao.GoodsDao;
import cn.itcast.erp.invoice.goods.vo.GoodsModel;
import cn.itcast.erp.invoice.goods.vo.GoodsQueryModel;
import cn.itcast.erp.utils.exception.AppException;

public class GoodsEbo implements GoodsEbi{
	private GoodsDao goodsDao;
	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void save(GoodsModel gm) {
		if(gm!=null && gm.getGtm()!=null && gm.getGtm().getUuid()!=null) {
			goodsDao.save(gm);
		}else {
			throw new AppException("对不起，请指定供应商和商品类别!");
		}
	}

	public void update(GoodsModel gm) {
		if(gm!=null && gm.getGtm()!=null && gm.getGtm().getUuid()!=-1) {
			goodsDao.update(gm);
		}else {
			throw new AppException("对不起，请指定供应商和商品类别!");
		}
	}

	public void delete(GoodsModel gm) {
		goodsDao.delete(gm);
	}

	public GoodsModel get(Long uuid) {
		return goodsDao.get(uuid);
	}

	public List<GoodsModel> getAll() {
		return goodsDao.getAll();
	}

	public List<GoodsModel> getAll(GoodsQueryModel gqm, Integer pageNum,Integer pageCount) {
		return goodsDao.getAll(gqm,pageNum,pageCount);
	}

	public Integer getCount(GoodsQueryModel gqm) {
		return goodsDao.getCount(gqm);
	}

	public List<GoodsModel> getByGtm(Long gtmUuid) {
		return goodsDao.getByGtm(gtmUuid);
	}

}
