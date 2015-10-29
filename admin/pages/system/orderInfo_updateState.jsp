<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>

<div class="pageContent">
    <form method="post" action="system/orderInfo/updateState.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <input name="order_id" type="hidden" value="${requestScope.orderInfo.id}"/>
        <div class="pageFormContent" layoutH="56">
          <label>订单状态：</label>
          <select name="data_state"
						style="width: 133px; height: 22px;">
							<c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1 || funcs:getSessionLoginUser(pageContext.session).roleID==2 ||funcs:getSessionLoginUser(pageContext.session).roleID==3||funcs:getSessionLoginUser(pageContext.session).roleID==5}">
							<option value="9"${data_state==9?'selected':''}>收集材料(录入)</option>
							<option value="10"${data_state==10?'selected':''}>收集材料(提交)</option>
							<option value="11"${data_state==11?'selected':''}>收集材料(审核)</option>
							<option value="12"${data_state==12?'selected':''}>收集材料(通过)</option>
							</c:if>
							<option value="20"${data_state==20?'selected':''}>业务审批(提交) </option>
							<option value="21"${data_state==21?'selected':''}>业务审批(审核) </option>
							<option value="22"${data_state==22?'selected':''}>业务审批(通过) </option>
							<option value="30"${data_state==30?'selected':''}>尽职审批(提交)</option>
							<option value="31"${data_state==31?'selected':''}>尽职审批(审核)</option>
							<option value="32"${data_state==32?'selected':''}>尽职审批(通过)</option>
							<option value="40"${data_state==40?'selected':''}>风控审批(提交)</option>
							<option value="41"${data_state==41?'selected':''}>风控审批(审核)</option>
							<option value="42"${data_state==42?'selected':''}>风控审批(通过)</option>
							<option value="50"${data_state==50?'selected':''}>放款审批(提交)</option>
							<option value="51"${data_state==51?'selected':''}>放款审批(审核)</option>
							<option value="52"${data_state==52?'selected':''}>放款审批(通过)</option>
							<option value="1"${data_state==1?'selected':''}>申请成功</option>
							<option value="2"${data_state==2?'selected':''}>申请失败</option>
				</select>
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