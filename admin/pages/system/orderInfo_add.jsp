<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>

<div class="pageContent">
    <form method="post" action="system/orderInfo/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
       <input name="order_id" type="hidden" value="${orderInfo.id}"/>
        <div class="pageFormContent" layoutH="56">
         <h2 class="contentTitle">基本资料：</h2>
            <p>
                <label>姓名：</label>
                <input name="data_name" ${orderInfo.data_name == null?"":"readonly"} value="${orderInfo.data_name}" type="text" size="30" class="required"/>
            </p>
            <p>
                <label>身份证：</label>
                <input name="data_sfz" value="${orderInfo.data_sfz}" type="text" size="30" class="required alphanumeric" minlength="15" maxlength="18" />
            </p>
            <p>
                <label>联系方式：</label>
                <input name="data_lxfs" value="${orderInfo.data_lxfs}" type="text" size="30" class=""/>
            </p>
            <p>
                <label>工作单位：</label>
                <input name="data_gzdw" value="${orderInfo.data_gzdw}" type="text" size="30" class=""/>
            </p>
            <p>
                <label>办公电话：</label>
                <input name="data_bgdh" value="${orderInfo.data_bgdh}" type="text" size="30" class=""/>
            </p>
            <p>
                <label>家庭地址：</label>
                <input name="data_jtdz"value="${orderInfo.data_jtdz}" type="text" size="30"  />
            </p>

            <div class="divider"></div>
             <h2 class="contentTitle">配偶资料：</h2>
            <p>
                <label>姓名：</label>
                <input name="partner_name"value="${orderInfo.partner_name}" type="text" size="30"  />
            </p>
            <p>
                <label>身份证：</label>
                <input name="partner_sfz"value="${orderInfo.partner_sfz}" type="text" size="30"  />
            </p>
            <p>
                <label>工作单位：</label>
                <input name="partner_gzdw"value="${orderInfo.partner_gzdw}" type="text" size="30"  />
            </p>
            <p>
                <label>联系方式：</label>
                <input name="partner_lxfs"value="${orderInfo.partner_lxfs}" type="text" size="30"  />
            </p>
            <p>
                <label>家庭地址：</label>
                <input name="partner_jtdz"value="${orderInfo.partner_jtdz}" type="text" size="30"  />
            </p>
            <div class="divider"></div>
             <h2 class="contentTitle">业务信息：</h2>
               <p>
                <label>业务内容：</label>
                <select name="data_ywnr"	>
							<option value="1"${orderInfo.data_ywnr==1?'selected':''}>融资</option>
							<option value="2"${orderInfo.data_ywnr==2?'selected':''}>0首付购房</option>
							<option value="3"${orderInfo.data_ywnr==3?'selected':''}>0首付购车</option>
				</select>
            </p>
               <p>
                <label>购房金额：</label>
                <input name="data_gfje" value="${orderInfo.data_gfje}" type="text" size="30" class=""/>
            </p>
               <p>
                <label>购房信息：</label>
                <input name="data_gfxx" value="${orderInfo.data_gfxx}" type="text" size="30" class=""/>
            </p>
            <p>
                <label>申请金额：</label>
                <input name="data_sqje" value="${orderInfo.data_sqje}" type="text" size="30" class=""/>
            </p>
            <p>
                <label>放款金额：</label>
                <input name="data_fkje" readonly value="${orderInfo.data_fkje}" type="text" size="30" class=""/>
            </p>
            <div class="divider"></div>
            <h2 class="contentTitle">所需资料：</h2>
            <label><input type="checkbox" <c:if test="${orderInfo.data_zxbg==1}">checked</c:if> name="data_zxbg" value="1" />征信报告</label>
            <label><input type="checkbox"  <c:if test="${orderInfo.data_jhz==1}">checked</c:if> name="data_jhz" value="1" />结婚证/离婚证</label>
            <label><input type="checkbox" <c:if test="${orderInfo.data_zxbg==1}">checked</c:if> name="data_hkb" value="1" />户口簿</label>
            <label><input type="checkbox" <c:if test="${orderInfo.data_zczm==1}">checked</c:if> name="data_zczm" value="1" />资产证明</label>
            <label><input type="checkbox" <c:if test="${orderInfo.data_yhls==1}">checked</c:if> name="data_yhls" value="1" />银行流水</label>
            <label><input type="checkbox" <c:if test="${orderInfo.data_gfht==1}">checked</c:if> name="data_gfht" value="1" />购房合同</label>
            <label><input type="checkbox" <c:if test="${orderInfo.data_djsj==1}">checked</c:if> name="data_djsj" value="1" />订单收据</label>
            <label><input type="checkbox" <c:if test="${orderInfo.data_fcz==1}">checked</c:if> name="data_fcz" value="1" />卖房房产证</label>
            <label><input type="checkbox" <c:if test="${orderInfo.data_srzm==1}">checked</c:if> name="data_srzm" value="1" />收入证明</label>
            <div class="divider"></div>
             <h2 class="contentTitle">备注说明：</h2>
              <dl class="nowrap">
			<dd><textarea name="salesman_note" cols="150"  rows="3">${orderInfo.submit_note1}</textarea></dd>
			</dl>  
        </div>
        <div class="formBar">
            <ul>
                <li><div class="button"><div class="buttonContent"><button type="submit">录入</button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form>
</div>