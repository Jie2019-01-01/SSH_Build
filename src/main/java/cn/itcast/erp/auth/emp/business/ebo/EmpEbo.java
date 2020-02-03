package cn.itcast.erp.auth.emp.business.ebo;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import cn.itcast.erp.auth.emp.business.ebi.EmpEbi;
import cn.itcast.erp.auth.emp.dao.dao.EmpDao;
import cn.itcast.erp.auth.emp.vo.EmpModel;
import cn.itcast.erp.auth.emp.vo.EmpQueryModel;
import cn.itcast.erp.utils.exception.AppException;
import cn.itcast.erp.utils.format.MD5Utils;
import cn.itcast.erp.utils.ip.IpUtils;

public class EmpEbo implements EmpEbi{

	private EmpDao empDao;

	public void setEmpDao(EmpDao empDao) {this.empDao = empDao;}

	/**
	 * 根据用户名和密码登录
	 * @param userName	用户名
	 * @param pwd 密码
	 * @return 登录成功的用户信息，失败则返回null
	 */
	public EmpModel login(String userName, String pwd) {
		// 密码加密
		pwd = MD5Utils.md5(pwd);
		// 调用数据层
		EmpModel loginEm = empDao.getByUserNameAndPwd(userName, pwd);
		if(loginEm!=null) {
			loginEm.setLastLoginTime(System.currentTimeMillis());
			int times = loginEm.getLoginTimes()+1;
			loginEm.setLoginTimes(times);
			String ip = IpUtils.getIpAddr(ServletActionContext.getRequest());
			loginEm.setLastLoginIp(ip);
			// 不需要写update方法，直接快照更新
		}
		return loginEm;
	}
	
	public void save(EmpModel em) {
		if(em.getUserName()==null || em.getUserName().trim().length()==0) {
			throw new AppException("INFO_EMP_USERNAME_IS_EMPTY");
		}
		em.setPwd(MD5Utils.md5(em.getPwd()));
		//设置默认值
		em.setLastLoginTime(System.currentTimeMillis());
		em.setLastLoginIp("-");
		em.setLoginTimes(0);
		empDao.save(em);
	}

	public void update(EmpModel em) {
		// 快照思想更新
		// 1、根据id查询出数据
		// 2、由em接收前台传过来的数据
		// 3、允许更新的数据重新设置到temp中
		// 4、不用使用update方法，快照直接即可更新
		EmpModel temp = empDao.get(em.getUuid());
		temp.setName(em.getName());
		temp.setEmail(em.getEmail());
		temp.setTele(em.getTele());
		temp.setGender(em.getGender());
		temp.setAddress(em.getAddress());
		temp.setDm(em.getDm());
	}

	public void delete(EmpModel em) {
		empDao.delete(em);
	}

	public List<EmpModel> getAll() {
		return empDao.getAll();
	}

	public EmpModel get(Long uuid) {
		return empDao.get(uuid);
	}

	public List<EmpModel> getAll(EmpQueryModel dqm, Integer pageNum, Integer pageCount) {
		return empDao.getAll(dqm,pageNum,pageCount);
	}

	public Integer getCount(EmpQueryModel dqm) {
		return empDao.getCount(dqm);
	}

	public boolean changePwd(String userName, String pwd, String newPwd) {
		// 密码加密
		pwd = MD5Utils.md5(pwd);
		newPwd = MD5Utils.md5(newPwd);
		return empDao.changePwdByUserNameAndPwd(userName, pwd, newPwd);
	}

}
