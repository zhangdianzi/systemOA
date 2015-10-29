package com.manager.admin.common.to;

import java.io.Serializable;
import java.util.Date;

public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 状态：10收集材料(录入)11收集材料(审核) 12收集材料(通过) 20银行业务审批 (提交)  21银行业务审批 (审核) 22银行业务审批 (通过)30银行尽职审批(提交) 31银行尽职审批(审核) 32银行尽职审批(通过) 40银行风控审批(提交) 41银行风控审批(审核) 42银行风控审批(通过) 50银行放款审批(提交) 51银行放款审批(审核) 52银行放款审批(通过) 1完成  2失败
     */
    private Integer data_state;

    /**
     * 姓名
     */
    private String data_name;

    /**
     * 身份证
     */
    private String data_sfz;

    /**
     * 工作单位
     */
    private String data_gzdw;

    /**
     * 家庭地址
     */
    private String data_jtdz;

    /**
     * 联系方式
     */
    private String data_lxfs;

    /**
     * 购房金额
     */
    private Integer data_gfje;
    
    private String data_gfxx;

    /**
     * 申请金额
     */
    private Integer data_sqje;

    /**
     * 实际房款金额
     */
    private Integer data_fkje;
    private Integer data_ywnr;

    /**
     * 办公电话
     */
    private String data_bgdh;

    private String partner_name;

    private String partner_sfz;

    private String partner_gzdw;

    private String partner_lxfs;

    private String partner_jtdz;

    private Integer data_srzm;

    /**
     * 征信报告
     */
    private Integer data_zxbg;

    /**
     * 结婚证、离婚证
     */
    private Integer data_jhz;

    /**
     * 资产证明
     */
    private Integer data_zczm;

    /**
     * 户口簿
     */
    private Integer data_hkb;

    /**
     * 银行流水
     */
    private Integer data_yhls;

    /**
     * 购房合同
     */
    private Integer data_gfht;

    /**
     * 定金收据
     */
    private Integer data_djsj;

    /**
     * 房产证
     */
    private Integer data_fcz;

    /**
     * 业务员
     */
    private Integer salesman;

    private Integer controlman;

    private String controlman_note1;

    private String controlman_note2;

    private String controlman_note3;

    private String controlman_note4;

    private String controlman_note5;

    /**
     * 经理
     */
    private Integer manager;

    private String manager_note1;

    private String manager_note2;

    private String manager_note3;

    private String manager_note4;

    private String manager_note5;

    /**
     * 跟单员
     */
    private Integer documentary;

    private String submit_note1;

    private String submit_note2;

    private String submit_note3;

    private String submit_note4;

    private String submit_note5;

    private String fail_note;

    /**
     * 录入时间
     */
    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 状态：10收集材料(录入)11收集材料(审核) 12收集材料(通过) 20银行业务审批 (提交)  21银行业务审批 (审核) 22银行业务审批 (通过)30银行尽职审批(提交) 31银行尽职审批(审核) 32银行尽职审批(通过) 40银行风控审批(提交) 41银行风控审批(审核) 42银行风控审批(通过) 50银行放款审批(提交) 51银行放款审批(审核) 52银行放款审批(通过) 1完成  2失败
     */
    public Integer getData_state() {
        return data_state;
    }

    /**
     * @param dataState 
	 *            状态：10收集材料(录入)11收集材料(审核) 12收集材料(通过) 20银行业务审批 (提交)  21银行业务审批 (审核) 22银行业务审批 (通过)30银行尽职审批(提交) 31银行尽职审批(审核) 32银行尽职审批(通过) 40银行风控审批(提交) 41银行风控审批(审核) 42银行风控审批(通过) 50银行放款审批(提交) 51银行放款审批(审核) 52银行放款审批(通过) 1完成  2失败
     */
    public void setData_state(Integer data_state) {
        this.data_state = data_state;
    }

    /**
     * @return 姓名
     */
    public String getData_name() {
        return data_name;
    }

    /**
     * @param dataName 
	 *            姓名
     */
    public void setData_name(String data_name) {
        this.data_name = data_name;
    }

    /**
     * @return 身份证
     */
    public String getData_sfz() {
        return data_sfz;
    }

    /**
     * @param dataSfz 
	 *            身份证
     */
    public void setData_sfz(String data_sfz) {
        this.data_sfz = data_sfz;
    }

    /**
     * @return 工作单位
     */
    public String getData_gzdw() {
        return data_gzdw;
    }

    /**
     * @param dataGzdw 
	 *            工作单位
     */
    public void setData_gzdw(String data_gzdw) {
        this.data_gzdw = data_gzdw;
    }

    /**
     * @return 家庭地址
     */
    public String getData_jtdz() {
        return data_jtdz;
    }

    /**
     * @param dataJtdz 
	 *            家庭地址
     */
    public void setData_jtdz(String data_jtdz) {
        this.data_jtdz = data_jtdz;
    }

    /**
     * @return 联系方式
     */
    public String getData_lxfs() {
        return data_lxfs;
    }

    /**
     * @param dataLxfs 
	 *            联系方式
     */
    public void setData_lxfs(String data_lxfs) {
        this.data_lxfs = data_lxfs;
    }

    /**
     * @return 购房金额
     */
    public Integer getData_gfje() {
        return data_gfje;
    }

    /**
     * @param dataGfje 
	 *            购房金额
     */
    public void setData_gfje(Integer data_gfje) {
        this.data_gfje = data_gfje;
    }

    /**
     * @return 申请金额
     */
    public Integer getData_sqje() {
        return data_sqje;
    }

    /**
     * @param dataSqje 
	 *            申请金额
     */
    public void setData_sqje(Integer data_sqje) {
        this.data_sqje = data_sqje;
    }

    /**
     * @return 实际房款金额
     */
    public Integer getData_fkje() {
        return data_fkje;
    }

    /**
     * @param dataFkje 
	 *            实际房款金额
     */
    public void setData_fkje(Integer data_fkje) {
        this.data_fkje = data_fkje;
    }

    /**
     * @return 办公电话
     */
    public String getData_bgdh() {
        return data_bgdh;
    }

    /**
     * @param dataBgdh 
	 *            办公电话
     */
    public void setData_bgdh(String data_bgdh) {
        this.data_bgdh = data_bgdh;
    }

    public String getPartner_name() {
        return partner_name;
    }

    public void setPartner_name(String partner_name) {
        this.partner_name = partner_name;
    }

    public String getPartner_sfz() {
        return partner_sfz;
    }

    public void setPartner_sfz(String partner_sfz) {
        this.partner_sfz = partner_sfz;
    }

    public String getPartner_gzdw() {
        return partner_gzdw;
    }

    public void setPartner_gzdw(String partner_gzdw) {
        this.partner_gzdw = partner_gzdw;
    }

    public String getPartner_lxfs() {
        return partner_lxfs;
    }

    public void setPartner_lxfs(String partner_lxfs) {
        this.partner_lxfs = partner_lxfs;
    }

    public String getPartner_jtdz() {
        return partner_jtdz;
    }

    public void setPartner_jtdz(String partner_jtdz) {
        this.partner_jtdz = partner_jtdz;
    }

    public Integer getData_srzm() {
        return data_srzm;
    }

    public void setData_srzm(Integer data_srzm) {
        this.data_srzm = data_srzm;
    }

    /**
     * @return 征信报告
     */
    public Integer getData_zxbg() {
        return data_zxbg;
    }

    /**
     * @param dataZxbg 
	 *            征信报告
     */
    public void setData_zxbg(Integer data_zxbg) {
        this.data_zxbg = data_zxbg;
    }

    /**
     * @return 结婚证、离婚证
     */
    public Integer getData_jhz() {
        return data_jhz;
    }

    /**
     * @param dataJhz 
	 *            结婚证、离婚证
     */
    public void setData_jhz(Integer data_jhz) {
        this.data_jhz = data_jhz;
    }

    /**
     * @return 资产证明
     */
    public Integer getData_zczm() {
        return data_zczm;
    }

    /**
     * @param dataZczm 
	 *            资产证明
     */
    public void setData_zczm(Integer data_zczm) {
        this.data_zczm = data_zczm;
    }

    /**
     * @return 户口簿
     */
    public Integer getData_hkb() {
        return data_hkb;
    }

    /**
     * @param dataHkb 
	 *            户口簿
     */
    public void setData_hkb(Integer data_hkb) {
        this.data_hkb = data_hkb;
    }

    /**
     * @return 银行流水
     */
    public Integer getData_yhls() {
        return data_yhls;
    }

    /**
     * @param dataYhls 
	 *            银行流水
     */
    public void setData_yhls(Integer data_yhls) {
        this.data_yhls = data_yhls;
    }

    /**
     * @return 购房合同
     */
    public Integer getData_gfht() {
        return data_gfht;
    }

    /**
     * @param dataGfht 
	 *            购房合同
     */
    public void setData_gfht(Integer data_gfht) {
        this.data_gfht = data_gfht;
    }

    /**
     * @return 定金收据
     */
    public Integer getData_djsj() {
        return data_djsj;
    }

    /**
     * @param dataDjsj 
	 *            定金收据
     */
    public void setData_djsj(Integer data_djsj) {
        this.data_djsj = data_djsj;
    }

    /**
     * @return 房产证
     */
    public Integer getData_fcz() {
        return data_fcz;
    }

    /**
     * @param dataFcz 
	 *            房产证
     */
    public void setData_fcz(Integer data_fcz) {
        this.data_fcz = data_fcz;
    }

    /**
     * @return 业务员
     */
    public Integer getSalesman() {
        return salesman;
    }

    /**
     * @param salesman 
	 *            业务员
     */
    public void setSalesman(Integer salesman) {
        this.salesman = salesman;
    }

    public Integer getControlman() {
        return controlman;
    }

    public void setControlman(Integer controlman) {
        this.controlman = controlman;
    }

    public String getControlman_note1() {
        return controlman_note1;
    }

    public void setControlman_note1(String controlman_note1) {
        this.controlman_note1 = controlman_note1;
    }

    public String getControlman_note2() {
        return controlman_note2;
    }

    public void setControlman_note2(String controlman_note2) {
        this.controlman_note2 = controlman_note2;
    }

    public String getControlman_note3() {
        return controlman_note3;
    }

    public void setControlman_note3(String controlman_note3) {
        this.controlman_note3 = controlman_note3;
    }

    public String getControlman_note4() {
        return controlman_note4;
    }

    public void setControlman_note4(String controlman_note4) {
        this.controlman_note4 = controlman_note4;
    }

    public String getControlman_note5() {
        return controlman_note5;
    }

    public void setControlman_note5(String controlman_note5) {
        this.controlman_note5 = controlman_note5;
    }

    /**
     * @return 经理
     */
    public Integer getManager() {
        return manager;
    }

    /**
     * @param manager 
	 *            经理
     */
    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public String getManager_note1() {
        return manager_note1;
    }

    public void setManager_note1(String manager_note1) {
        this.manager_note1 = manager_note1;
    }

    public String getManager_note2() {
        return manager_note2;
    }

    public void setManager_note2(String manager_note2) {
        this.manager_note2 = manager_note2;
    }

    public String getManager_note3() {
        return manager_note3;
    }

    public void setManager_note3(String manager_note3) {
        this.manager_note3 = manager_note3;
    }

    public String getManager_note4() {
        return manager_note4;
    }

    public void setManager_note4(String manager_note4) {
        this.manager_note4 = manager_note4;
    }

    public String getManager_note5() {
        return manager_note5;
    }

    public void setManager_note5(String manager_note5) {
        this.manager_note5 = manager_note5;
    }

    /**
     * @return 跟单员
     */
    public Integer getDocumentary() {
        return documentary;
    }

    /**
     * @param documentary 
	 *            跟单员
     */
    public void setDocumentary(Integer documentary) {
        this.documentary = documentary;
    }

    public String getSubmit_note1() {
        return submit_note1;
    }

    public void setSubmit_note1(String submit_note1) {
        this.submit_note1 = submit_note1;
    }

    public String getSubmit_note2() {
        return submit_note2;
    }

    public void setSubmit_note2(String submit_note2) {
        this.submit_note2 = submit_note2;
    }

    public String getSubmit_note3() {
        return submit_note3;
    }

    public void setSubmit_note3(String submit_note3) {
        this.submit_note3 = submit_note3;
    }

    public String getSubmit_note4() {
        return submit_note4;
    }

    public void setSubmit_note4(String submit_note4) {
        this.submit_note4 = submit_note4;
    }

    public String getSubmit_note5() {
        return submit_note5;
    }

    public void setSubmit_note5(String submit_note5) {
        this.submit_note5 = submit_note5;
    }

    public String getFail_note() {
        return fail_note;
    }

    public void setFail_note(String fail_note) {
        this.fail_note = fail_note;
    }

    /**
     * @return 录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createtime 
	 *            录入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public Integer getData_ywnr() {
		return data_ywnr;
	}

	public void setData_ywnr(Integer data_ywnr) {
		this.data_ywnr = data_ywnr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getData_gfxx() {
		return data_gfxx;
	}

	public void setData_gfxx(String data_gfxx) {
		this.data_gfxx = data_gfxx;
	}
    
}