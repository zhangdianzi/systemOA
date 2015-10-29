<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="funcs" uri="funcs"%>

	<script type="text/javascript">
	    $(document).ready(function(){
		});
		function query(p){
			alert('aa');
			var roleID = document.getElementById("roleID").value;
			$.ajax({
				url: "system/account/list.html?pageNum="+p+"&&roleID="+roleID,
				context: document.body,
				success: function(data){
				$('#navTab').html(data);
				}});
		}
		function searchUserName(){
			var u = document.getElementById("search").value;
			$.ajax({
				url: "system/account/list.html?userName="+u,
				context: document.body,
				success: function(data){
				$('#navTab').html(data);
				}});
		}
	    </script>


 <!-- content start -->
  <div class="admin-content">

    <div class="am-cf am-padding">
      <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">系统管理</strong> / <small>帐号管理</small></div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12 am-u-md-6">
        <div class="am-btn-toolbar">
          <div class="am-btn-group am-btn-group-xs">
            <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
            <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
            <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 审核</button>
            <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
          </div>
        </div>
      </div>
      <div class="am-u-sm-12 am-u-md-3">
        <div class="am-form-group">
          <select name="roleID" id="roleID" onchange= "javascript:query(1)">
							<option value="">角色选择</option>
								<option value="1" ${roleID==1?'selected':''}>经理</option>
								<option value="2" ${roleID==2?'selected':''}>业务</option>
								<option value="3" ${roleID==3?'selected':''}>风控</option>
								<option value="4" ${roleID==4?'selected':''}>跟单</option>
								<option value="5" ${roleID==5?'selected':''}>区域经理</option>
			</select>
        </div>
      </div>
      <div class="am-u-sm-12 am-u-md-3">
        <div class="am-input-group am-input-group-sm">
          <input id="search" type="text" class="am-form-field">
          <span class="am-input-group-btn">
            <button class="am-btn am-btn-default" type="button" onClick="javascript:searchUserName()">搜索</button>
          </span>
        </div>
      </div>
    </div>

    <div class="am-g">
      <div class="am-u-sm-12">
        <form class="am-form">
          <table class="am-table am-table-striped am-table-hover table-main">
            <thead>
						<tr>
                            <th><input type="checkbox" /></th>
							<th>ID</th>
							<th>用户名</th>
							<th>密码</th>
							<th>真实名</th>
							<th>角色</th>
							<th>基本工资</th>
							<th>当前工资</th>
							<th>所属区域</th>
							<th>创建时间</th>
							<th>状态</th>
							<th>菜单权限</th>
						</tr>
					</thead>
          <tbody>
           <c:forEach items="${requestScope.list}" var="accountList" varStatus="count">
          <tr>
              <td><input type="checkbox" /></td>
          <td>${accountList.id}</td>
          <td>${accountList.userName}</td>
          <td>${fn:substring(accountList.password,0,1)}******</td>
          <td>${accountList.realName}</td>
          <td>
          <c:if test="${accountList.roleID==4}"><font style="color:#00ff00">跟单</font></c:if>
          <c:if test="${accountList.roleID==3}"><font style="color:#0000ff">风控</font></c:if>
          <c:if test="${accountList.roleID==2}"><font style="color:#000000">业务</font></c:if>
          <c:if test="${accountList.roleID==1}"><font style="color:#ff0000">经理</font></c:if>
          <c:if test="${accountList.roleID==5}"><font style="color:#aa0000">区域经理</font></c:if>
          </td>
          <td>${accountList.baseSalary}</td>
          <td>${accountList.curSalary}</td>
          <td>${accountList.groupName}</td>
          <td>${funcs:formatDateTime(accountList.createTime, 'yyyy-MM-dd')}</td>
          <td>${accountList.state == 1 ? "有效" : "无效"} </td>
<!--           <td> -->
<%--           <a href="system/account_menu/list.html?accountId=${accountList.id}" target="dialog" mask="true" minable="false" width="300" height="600"><span style="color:blue">查看</span></a> --%>
<!--           </td> -->
<td>
<button class="am-btn am-btn-default am-btn-xs am-text-secondary"  href="system/account_menu/list.html?accountId=${accountList.id}" target="dialog" mask="true" minable="false" width="300" height="600"><span class="am-icon-pencil-square-o"></span> 编辑</button>
            </td>
          </tr>
          </c:forEach>
          </tbody>
        </table>
        </form>
      </div>

    </div>
  </div>

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