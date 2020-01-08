package cn.itcast.erp.auth.emp.vo;

public class EmpModel {

	// 注册功能
	private Long uuid;
	// 用户名
	private String userName;
	// 密码
	private String pwd;
	// 真实姓名
	private String name;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
