package cn.itcast.erp.auth.dep.business.ebi;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.auth.dep.vo.DepQueryModel;

@Transactional
public interface DepEbi {

	public void save(DepModel dm);

	public List<DepModel> getAll();

	public DepModel get(Long uuid);

	public void update(DepModel dm);

	public void delete(DepModel dm);

	public List<DepModel> getAll(DepQueryModel dqm);

	public List<DepModel> getAll(DepQueryModel dqm, Integer pageNum, Integer pageCount);

	public Integer getCount(DepQueryModel dqm);

}
