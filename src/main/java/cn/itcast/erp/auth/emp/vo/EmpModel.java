package cn.itcast.erp.auth.emp.vo;

import java.util.HashMap;
import java.util.Map;
import cn.itcast.erp.auth.dep.vo.DepModel;
import cn.itcast.erp.utils.format.FormatUtil;

public class EmpModel {

	public static final String EMP_LOGIN_USER_OBJECT_NAME = "loginEm";

	// 数据结构思想
	// 性别key值
	public static final Integer EMP_GENDER_OF_MAN = 1;
	public static final Integer EMP_GENDER_OF_WOMAN = 0;
	// 性别视图
	public static final String EMP_GENDER_OF_MAN_VIEW = "男";
	public static final String EMP_GENDER_OF_WOMAN_VIEW = "女";
	// 性别映射
	public static final Map<Integer, String> genderMap = new HashMap<Integer, String>();
	// 初始化赋值
	static {
		genderMap.put(EMP_GENDER_OF_MAN, EMP_GENDER_OF_MAN_VIEW);
		genderMap.put(EMP_GENDER_OF_WOMAN, EMP_GENDER_OF_WOMAN_VIEW);
	}
	
	private Long uuid;
	private String userName; // 用户名
	private String name; // 真实姓名
	private String pwd; // 密码
	private String email; // 邮箱
	private String tele; // 电话
	private String address; // 地址
	private Integer gender; // 性别
	private String genderView;
	private DepModel dm; // 所属部门
	private Long birthDay; // 出生日期
	private String birthDayView; // 出生日期视图
	private Long lastLoginTime; // 最后登录时间
	private String lastLoginTimeView; // 登录时间视图
	private String lastLoginIp; // 登录ip
	private String loginTimes; // 登录次数
	
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
		this.genderView = genderMap.get(gender);
	}
	public String getGenderView() {
		return genderView;
	}
	public void setBirthDayView(String birthDayView) {
		this.birthDayView = birthDayView;
		this.birthDay = FormatUtil.formatDate(birthDayView);
	}
	public String getBirthDayView() {
		return birthDayView;
	}
	public String getLoginTimes() {
		return loginTimes;
	}
	public void setLoginTimes(String loginTimes) {
		this.loginTimes = loginTimes;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public String getLastLoginTimeView() {
		return lastLoginTimeView;
	}
	public Long getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Long lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
		this.lastLoginTimeView = FormatUtil.formatDate(lastLoginTime);
	}
	public Long getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Long birthDay) {
		this.birthDay = birthDay;
		this.birthDayView = FormatUtil.formatDate(birthDay);
	}
	public DepModel getDm() {
		return dm;
	}
	public void setDm(DepModel dm) {
		this.dm = dm;
	}
}
