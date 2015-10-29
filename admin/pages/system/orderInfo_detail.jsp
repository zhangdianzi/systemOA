<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>




<div class="pageContent">
    <form method="post" action="system/orderInfo/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone)">
       <input name="order_id" type="hidden" value="${orderInfo.id}"/>
        <div class="pageFormContent" layoutH="56">
<table class="table" width="100%">
<tr>
<td width="20%"><label style="width:60px;">姓名：</label><font style="color:#0000ff">${orderInfo.data_name}</font></td>
<td width="25%"><label style="width:60px;">身份证：</label><font style="color:#0000ff">${orderInfo.data_sfz}</font></td>
<td><label style="width:60px;">工作单位：</label><font style="color:#0000ff">${orderInfo.data_gzdw}</font></td>
</tr>
<tr>
<td ><label style="width:60px;">办公电话：</label><font style="color:#0000ff">${orderInfo.data_bgdh}</font></td>
<td><label style="width:60px;">联系方式：</label><font style="color:#0000ff">${orderInfo.data_lxfs}</font></td>
<td ><label style="width:60px;">家庭地址：</label><font style="color:#0000ff">${orderInfo.data_jtdz}</font></td>
</tr>
<tr>
<td ><label style="width:60px;">配偶姓名：</label><font style="color:#0000ff">${orderInfo.partner_name}</font></td>
<td ><label style="width:60px;">身份证：</label><font style="color:#0000ff">${orderInfo.partner_sfz}</font></td>
<td><label style="width:60px;">工作单位：</label><font style="color:#0000ff">${orderInfo.partner_gzdw}</font></td>
</tr>
<tr>
<td ></td>
<td><label style="width:60px;">联系方式：</label><font style="color:#0000ff">${orderInfo.partner_lxfs}</font></td>
<td ><label style="width:60px;">家庭地址：</label><font style="color:#0000ff">${orderInfo.partner_jtdz}</font></td>
</tr>
<tr>
<td><label style="width:60px;">购房金额：</label><font style="color:#0000ff">${orderInfo.data_gfje}</font></td>
<td><label style="width:60px;">申请金额：</label><font style="color:#0000ff">${orderInfo.data_sqje}</font></td>
<td ><label style="width:60px;">放款金额：</label><font style="color:#0000ff">${orderInfo.data_fkje}</font></td>
</tr>
<tr>
<td><label style="width:60px;">业务内容：</label><font style="color:#0000ff">${orderInfo.data_ywnr}</font></td>
<td><label style="width:60px;">购房信息：</label><font style="color:#0000ff">${orderInfo.data_gfxx}</font></td>
</tr>
<tr >
<td colspan="3" >
			<label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_zxbg==1}">checked</c:if> name="data_zxbg" value="1" />征信报告</label>
            <label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_zxbg==1}">checked</c:if> name="data_hkb" value="1" />户口簿</label>
            <label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_zczm==1}">checked</c:if> name="data_zczm" value="1" />资产证明</label>
            <label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_yhls==1}">checked</c:if> name="data_yhls" value="1" />银行流水</label>
            <label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_gfht==1}">checked</c:if> name="data_gfht" value="1" />购房合同</label>
            <label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_djsj==1}">checked</c:if> name="data_djsj" value="1" />订单收据</label>
            <label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_fcz==1}">checked</c:if> name="data_fcz" value="1" />卖房房产证</label>
            <label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_srzm==1}">checked</c:if> name="data_srzm" value="1" />收入证明</label>
            <label style="width:80px;"><input type="checkbox" disabled <c:if test="${orderInfo.data_jhz==1}">checked</c:if> name="data_jhz" value="1" />结婚证/离婚证</label>
            
</td >
</tr>
 <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
<tr >
<td colspan="3">

录单员：
<c:if test="${funcs:selecUserByAccountId(orderInfo.salesman).realName != null}">
<font style="color:#0000ff">
${funcs:selecUserByAccountId(orderInfo.salesman).realName}
</font>
</c:if>
<c:if test="${funcs:selecUserByAccountId(orderInfo.salesman).realName == null}">
<font style="color:#ff0000">
--
</font>
</c:if>

&nbsp;&nbsp;风控：
<c:if test="${funcs:selecUserByAccountId(orderInfo.controlman).realName != null}">
<font style="color:#0000ff">
${funcs:selecUserByAccountId(orderInfo.controlman).realName}
</font>
</c:if>
<c:if test="${funcs:selecUserByAccountId(orderInfo.controlman).realName == null}">
<font style="color:#ff0000">
--
</font>
</c:if>
&nbsp;&nbsp;跟单员：
<c:if test="${funcs:selecUserByAccountId(orderInfo.documentary).realName != null}">
<font style="color:#0000ff">
${funcs:selecUserByAccountId(orderInfo.documentary).realName}
</font>
</c:if>
<c:if test="${funcs:selecUserByAccountId(orderInfo.documentary).realName == null}">
<font style="color:#ff0000">
--
</font>
</c:if>
&nbsp;&nbsp;经理：
<c:if test="${funcs:selecUserByAccountId(orderInfo.manager).realName != null}">
<font style="color:#0000ff">
${funcs:selecUserByAccountId(orderInfo.manager).realName}
</font>
</c:if>
<c:if test="${funcs:selecUserByAccountId(orderInfo.manager).realName == null}">
<font style="color:#ff0000">
--
</font>
</c:if>
</td>

</tr>                                                                                             
<tr >
</tr>
</c:if>
 <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1 || funcs:getSessionLoginUser(pageContext.session).roleID==2 || funcs:getSessionLoginUser(pageContext.session).roleID==3}">
<tr>
<td colspan="3"><label style="width:110px;">收集材料(提交批注)：</label><font style="color:#0000ff">${orderInfo.submit_note1}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">收集材料(审核批注)：</label><font style="color:#0000ff">${orderInfo.controlman_note1}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">收集材料(通过批注)：</label><font style="color:#0000ff">${orderInfo.manager_note1}</font></td>
</tr>
</c:if>
 <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1 || funcs:getSessionLoginUser(pageContext.session).roleID==4 || funcs:getSessionLoginUser(pageContext.session).roleID==3}">
<tr>
<td colspan="3"><label style="width:110px;">业务审批(提交批注)：</label><font style="color:#0000ff">${orderInfo.submit_note2}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">业务审批(审核批注)：</label><font style="color:#0000ff">${orderInfo.controlman_note2}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">业务审批(通过批注)：</label><font style="color:#0000ff">${orderInfo.manager_note2}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">尽职审批(提交批注)：</label><font style="color:#0000ff">${orderInfo.submit_note3}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">尽职审批(审核批注)：</label><font style="color:#0000ff">${orderInfo.controlman_note3}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">尽职审批(通过批注)：</label><font style="color:#0000ff">${orderInfo.manager_note3}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">风控审批(提交批注)：</label><font style="color:#0000ff">${orderInfo.submit_note4}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">风控审批(审核批注)：</label><font style="color:#0000ff">${orderInfo.controlman_note4}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">风控审批(通过批注)：</label><font style="color:#0000ff">${orderInfo.manager_note4}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">放款审批(提交批注)：</label><font style="color:#0000ff">${orderInfo.submit_note5}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">放款审批(审核批注)：</label><font style="color:#0000ff">${orderInfo.controlman_note5}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">放款审批(通过批注)：</label><font style="color:#0000ff">${orderInfo.manager_note5}</font></td>
</tr>
<tr>
<td colspan="3"><label style="width:110px;">失败原因：</label><font style="color:#0000ff">${orderInfo.fail_note}</font></td>
</tr>
</c:if>
</table>
        </div>
    </form>
</div>