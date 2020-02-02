package cn.itcast.erp.auth.emp.vo;

import cn.itcast.erp.utils.base.BaseQueryModel;
import cn.itcast.erp.utils.format.FormatUtil;

public class EmpQueryModel extends EmpModel implements BaseQueryModel {

	private Long birthDay2;
	private String birthDay2View;

	public Long getBirthDay2() {
		return birthDay2;
	}

	public void setBirthDay2(Long birthDay2) {
		this.birthDay2 = birthDay2;
		this.birthDay2View = FormatUtil.formatTime(birthDay2);
	}

	public String getBirthDay2View() {
		return birthDay2View;
	}
}