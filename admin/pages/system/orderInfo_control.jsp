<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>

<div class="pageContent">
    <form method="post" action="system/orderInfo/control.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <input name="order_id" type="hidden" value="${requestScope.orderInfo.id}"/>
        <div class="pageFormContent" layoutH="56">
        <h2 class="contentTitle">基本资料：</h2>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_zxbg==1}">checked</c:if> name="data_zxbg" value="1" />征信报告</label>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_jhz==1}">checked</c:if> name="data_jhz" value="1" />结婚证/离婚证</label>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_zxbg==1}">checked</c:if> name="data_hkb" value="1" />户口簿</label>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_zczm==1}">checked</c:if> name="data_zczm" value="1" />资产证明</label>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_yhls==1}">checked</c:if> name="data_yhls" value="1" />银行流水</label>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_gfht==1}">checked</c:if> name="data_gfht" value="1" />购房合同</label>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_djsj==1}">checked</c:if> name="data_djsj" value="1" />订单收据</label>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_fcz==1}">checked</c:if> name="data_fcz" value="1" />卖房房产证</label>
            <label><input type="checkbox" disabled <c:if test="${orderInfo.data_srzm==1}">checked</c:if> name="data_srzm" value="1" />收入证明</label>
          <p>
          <div class="divider"></div>
          <label>录入备注：</label>
          <textarea name="submit_note" readonly cols="55" rows="3" style="color:#0000ff">${submit_note}</textarea>
          <label>审核备注：</label>
          <textarea name="controlman_note"  cols="55" rows="3" style="color:#ff0000"></textarea>
         <p>
          <label>是否通过：</label>
      	 	<input type="radio" name="merge" value="1" />通过
    		<input type="radio" name="merge" value="0"  />不通过
    		</p>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form>
</div>