<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib prefix="funcs" uri="funcs"%>

	<script type="text/javascript">
	    $(document).ready(function(){
		});
		function query(p){
			$.ajax({
				url: "system/account/loginLog.html?pageNum="+p+"",
				context: document.body,
				success: function(data){
				$('#navTab').html(data);
				}});
		}
	    </script>

<form id="pagerForm" action="system/account/loginLog.html" method="post">
  <input type="hidden" name="pageNum" value="${pageNo}" />
</form>

  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统管理</strong> / <small>访问日志</small></div>
    </div>

   <table class="am-table am-table-striped am-table-hover table-main">
    <thead>
      <tr>
     	<th width="10%">时间</th>
        <th width="10%">账号 </th>
        <th width="10%">行为 </th>
        <th width="10%">备注</th>
        <th width="10%">IP</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${requestScope.list}" var="loginLog" varStatus="count">
        <tr >
          <td>${funcs:formatDateTime(loginLog.createTime, 'yyyy-MM-dd HH:mm:ss')}</td>
          <td>${loginLog.account}</td>
          <td>${loginLog.action}</td>
          <td>${loginLog.context}</td>
          <td>${loginLog.ip}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
  
   <div class="am-cf">	共 ${totalCnt} 条记录
  		<div class="am-fr">
	    <ul class="am-pagination">
	      <li class="${pageNo==1?'am-disabled':''}"><a href="javascript:query(1)">首页</a></li>
	      <li class="${pageNo==1?'am-disabled':''}"><a href="javascript:query(${pageNo-1})">上一页</a></li>
	      <c:if test="${pageNo>4 && pageNo+1>lastPage}"><li><a href="javascript:query(${pageNo-4})">${pageNo-4}</a></li> </c:if>
	      <c:if test="${pageNo>3 && pageNo+2>lastPage}"><li><a href="javascript:query(${pageNo-3})">${pageNo-3}</a></li> </c:if>
	      <c:if test="${pageNo>2}"><li><a href="javascript:query(${pageNo-2})">${pageNo-2}</a></li></c:if>
	      <c:if test="${pageNo>1}"><li><a href="javascript:query(${pageNo-1})">${pageNo-1}</a></li> </c:if>
	      <li class="am-active"><a href="#">${pageNo}</a></li>
	      <c:if test="${pageNo+1<=lastPage}"><li><a href="javascript:query(${pageNo+1})">${pageNo+1}</a></li></c:if>
	      <c:if test="${pageNo+2<=lastPage}"><li><a href="javascript:query(${pageNo+2})">${pageNo+2}</a></li></c:if>
	      <c:if test="${pageNo<=1 && pageNo+3<lastPage}"><li><a href="javascript:query(${pageNo+3})">${pageNo+3}</a></li> </c:if>
	      <c:if test="${pageNo<=2 && pageNo+4<lastPage}"><li><a href="javascript:query(${pageNo+4})">${pageNo+4}</a></li> </c:if>
	      <li class="${pageNo==lastPage?'am-disabled':''}"><a href="javascript:query(${pageNo+1})">下一页</a></li>
	      <li class="${pageNo==lastPage?'am-disabled':''}"><a href="javascript:query(${lastPage})">尾页</a></li>
	    </ul>
  		</div>
	</div>



</div>

