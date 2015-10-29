<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.manager.admin.common.type.RoleType"%>
<%@ taglib prefix="funcs" uri="funcs" %>
<div class="pageContent">
    <form method="post" action="system/role/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <p>
                <label>角色名称：</label>
                <input name="name" type="text" size="30" class="required"/>
            </p>
            <p>
                <label>作用人员：</label>
                <select name="type" class="required combox">
                <c:set var="types" value="<%=RoleType.values()%>"/>
                <c:forEach items="${types}" var="t">
                  <option value="${t.id}" ${param.type == t.id ? "selected" : ""}>${t.name}</option>
                </c:forEach>
              </select>
            </p>
            <p>
                <label>备注：</label>
                <input name="remark" type="text" size="30" class=""/>
            </p>
        </div>
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