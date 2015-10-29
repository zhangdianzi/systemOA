<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.manager.admin.common.type.RoleType"%>
<%@ taglib prefix="funcs" uri="funcs" %>
<form id="pagerForm" method="post" action="system/role/list.html">
    <input type="hidden" name="pageNum" value="${requestScope.result.currentPage}" />
    <input type="hidden" name="numPerPage" value="${requestScope.reslut.pageSize}" />
    <input type="hidden" name="type" value="${requestScope.type}" />
</form>

<div class="pageHeader">
    <form onsubmit="return navTabSearch(this);" action="system/role/list.html" method="post" class="pageForm required-validate">
      <div class="searchBar">
        <table class="searchContent">
          <tr>
            <td>角色作用人员:</td>
            <td>
              <select name="type" class="required combox">
                <c:set var="types" value="<%=RoleType.values()%>"/>
                <c:forEach items="${types}" var="t">
                  <option value="${t.id}" ${requestScope.type == t.id ? "selected" : ""}>${t.name}</option>
                </c:forEach>
              </select>
            </td>
          </tr>
        </table>
        <div class="subBar">
          <ul>
            <li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
          </ul>
        </div>
      </div>
    </form>
</div>

<div class="pageContent">
  <div class="panelBar">
    <ul class="toolBar">
      <li><a class="add" href="system/role/goadd.html?type=${requestScope.type}" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>添加</span></a></li>
      <li><a class="delete" href="system/role/del.html?id={sid_role}" target="ajaxTodo" title="确定删除该角色吗?"><span>删除</span></a></li>
      <li><a class="edit" href="system/role/detail.html?id={sid_role}" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>修改</span></a></li>         
    </ul>
  </div>
  <table class="table" width="100%" layoutH="138">
    <thead>
      <tr >
        <th width="80">角色ID</th>
        <th width="120">角色名称</th>
        <th width="120">角色作用人员</th>
        <th width="120">角色备注</th>
        <th width="100">数据状态</th>
        <c:if test="${requestScope.type != 3}"><th width="200">默认权限菜单</th></c:if>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${requestScope.result.resultList}" var="r" varStatus="var">
        <tr target="sid_role" rel="${r.id}">
          <td>${r.id}</td>
          <td>${r.name}</td>
          <td>${r.typeName}</td>
          <td>${r.remark}</td>
          <td>${r.state == 1 ? "有效" : "失效"}</td>
          <c:if test="${requestScope.type != 3}">
          <td>
            <a href="system/role_menu/list.html?cooperationType=1&roleId=${r.id}&mtype=${requestScope.type}" target="dialog" mask="true" target="dialog" minable="false" width="300" height="600"><span style="color:blue">查看权限</span></a>
          </td>
          </c:if>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>

