<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="funcs" uri="funcs"%>
<form id="pagerForm" action="system/punish/list.html" method="post">
  <input type="hidden" name="pageNum" value="${pageNo }" />
  <input type="hidden" name="kinds" value="${kinds}">
</form>
<div class="pageHeader">
  <form onsubmit="return navTabSearch(this);" action="system/punish/list.html" method="post" class="pageForm required-validate">
    <div class="searchBar">
			<table class="searchContent">
				<tr>
				<td>公告类型：</td>
                <td>  <select name="kinds"
						style="width: 103px; height: 22px;">
							<option value="">全部类型</option>
							 <option value=3 ${3==kinds?'selected':''}>通知公告</option>
                       		 <option value=1 ${1==kinds?'selected':''}>奖励公告</option>
                       		 <option value=2 ${2==kinds?'selected':''}>处罚公告</option>
				</select>
				</tr>
		    		 
			</table>
			<div class="subBar">
				<ul>
				<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
				</div></li>
				</ul>
			</div>
        </div>
  </form>
</div>

<div class="pageContent">
<div class="panelBar">
            <ul class="toolBar">
            <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
                <li><a class="add" href="system/punish/toAdd.html" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>添加</span></a></li>
                <li><a class="delete" href="system/punish/del.html?id={sid_user}" target="ajaxTodo" title="删除本条记录吗?"><span>删除</span></a></li>
                <li><a class="edit" href="system/punish/toDetail.html?punish_id={sid_user}" target="dialog" width="500" height="370" mask="true" close="closedialog"><span>修改</span></a></li>         
           </c:if>
            </ul>
        </div>

   <table class="table" width="100%" layoutH="140">
    <thead>
      <tr>
        <th width="5%">公告类型</th>
        <th width="5%">姓名</th>
        <th width="5%">金额</th>
        <th width="10%">说明  </th>
        <th width="5%">订单详情  </th>
        <th width="5%">时间  </th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${requestScope.list}" var="punishList" varStatus="count">
        <tr target="sid_user" rel="${punishList.id}">
          <td>
          <c:if test="${punishList.kinds==1}"><font style="color:#ff0000">奖励公告</font></c:if>
          <c:if test="${punishList.kinds==2}"><font style="color:#00ff00">处罚公告</font></c:if>
          <c:if test="${punishList.kinds==3}"><font style="color:#0000ff">通知公告</font></c:if>
          </td>
          <td>${funcs:selecUserByAccountId(punishList.account_id).realName}</td>
          <td>${punishList.money}</td>
          <td>${punishList.reason}</td>
          <td>
          <c:if test="${punishList.order_id!=null}">
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
          <a href="system/orderInfo/toDetail.html?order_id=${punishList.order_id}" target="dialog" mask="true" minable="false" width="950" height="640"><span style="color:blue">查看</span></a>
         </c:if>
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==2}">
          <a href="system/orderInfo/toDetail.html?order_id=${punishList.order_id}" target="dialog" mask="true" minable="false" width="950" height="320"><span style="color:blue">查看</span></a>
         </c:if>
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==3}">
          <a href="system/orderInfo/toDetail.html?order_id=${punishList.order_id}" target="dialog" mask="true" minable="false" width="950" height="640"><span style="color:blue">查看</span></a>
         </c:if>
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==4}">
          <a href="system/orderInfo/toDetail.html?order_id=${punishList.order_id}" target="dialog" mask="true" minable="false" width="950" height="540"><span style="color:blue">查看</span></a>
         </c:if>
          </c:if>
          </td>
          <td>${funcs:formatDateTime(punishList.createTime, 'yyyy-MM-dd')}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
 <div class="panelBar">
    <div class="pages">
      <span>共${requestScope.totalCnt}条</span>
    </div>
	<div class="pagination" targetType="navTab" totalCount="${requestScope.totalCnt}" numPerPage="${pageSize}" currentPage="${pageNo}"></div>
  </div>
</div>

