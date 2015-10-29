<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>

<div class="pageContent">
    <form method="post" action="system/account/update.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <p> 
                <label>用 户 名：</label>
                <input name="userName" type="text" size="30" value="${requestScope.account.userName}" readonly />
            </p>
            <p>
                <label>密    码：</label>
                <input name="password" type="password" size="30" minlength="6" maxlength="20" alt="字母、数字、下划线 6-20位"/>
            </p>
            <p>
                <label>真实姓名：</label>
                <input name="realName" type="text" size="30" value="${requestScope.account.realName}" class=""/>
            </p>
            <p>
                <label>用户角色：</label>
                <select name="roleID" class="required combox">
                        <option value=2 ${requestScope.account.roleID == 2 ? "selected" : ""}>业务</option>
                        <option value=3 ${requestScope.account.roleID == 3 ? "selected" : ""}>风控</option>
                        <option value=4 ${requestScope.account.roleID == 4 ? "selected" : ""}>跟单</option>
                        <option value=5 ${requestScope.account.roleID == 5 ? "selected" : ""}>区域经理</option>
                        <option value=1 ${requestScope.account.roleID == 1 ? "selected" : ""}>经理</option>
                </select>
            </p>
            <p> 
                <label>邮    箱：</label>
                <input name="email" type="text" size="30" value="${requestScope.account.email}" class="email"/>
            </p>
            <p>
                <label>联系电话：</label>
                <input name="phone" type="text" size="30" value="${requestScope.account.phone}" class=""/>
            </p>
            <p>
                <label>联系地址：</label>
                <input name="address" type="text" size="30" value="${requestScope.account.address}" class=""/>
            </p>
             <p>
                <label>基本工资：</label>
                <input type="text" name="baseSalary" value="${requestScope.account.baseSalary}" size="30" class="number"  />
            </p>
              <p>
                <label>所属分区：</label>
<!--                 <select name="groupName" style="width:100px;height:22px;"> -->
<!-- 					<option value="">--全部--</option> -->
<%-- 					<c:forEach items="${groupNameList}" var="c" varStatus="var"> --%>
<%-- 					<option value="${c.groupName}"${c.groupName==account.groupName?'selected':''}>${c.groupName}</option> --%>
<%-- 					</c:forEach> --%>
<!-- 					</select> -->
 <input name="groupName" size="30" value="${account.groupName}" type="text" postField="keyword" suggestFields="groupName" 
                    suggestUrl="system/account/lookGroupName.html" lookupGroup=""/>
            </p>
            <p>
                <label>账户状态：</label>
                <select name="state" class="required combox">
                        <option value="0" ${requestScope.account.state == 0 ? "selected" : ""}>已失效</option>
                        <option value="1" ${requestScope.account.state == 1 ? "selected" : ""}>已启用</option>
                </select>
            </p>
        </div>
        <input name="account_id" type="hidden" value="${requestScope.account.id}"/>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
                </li>
            </ul>
        </div>
    </form>
</div>