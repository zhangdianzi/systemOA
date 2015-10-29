package com.manager.admin.common.to;

import java.io.Serializable;

public class CommonTo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer data_int;
    private String data_String;
    private Integer num;
	public Integer getData_int() {
		return data_int;
	}
	public void setData_int(Integer data_int) {
		this.data_int = data_int;
	}
	public String getData_String() {
		return data_String;
	}
	public void setData_String(String data_String) {
		this.data_String = data_String;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}