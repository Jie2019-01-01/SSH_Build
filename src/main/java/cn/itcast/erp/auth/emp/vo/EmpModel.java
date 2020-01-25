package cn.itcast.erp.auth.emp.vo;

public class EmpModel {

	public static final String EMP_LOGIN_USER_OBJECT_NAME = "loginEm";
	
	private Long uuid;
	private String userName;
	private String name;
	private String pwd;
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
}
