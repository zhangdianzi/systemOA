<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="funcs" uri="funcs"%>
<script type="text/javascript">
$("#export_excel").click(function(){
    $("#export_form").submit();
    return false;
});
$("#waittodo_text").click(function(){
    $("#waittodo_form").submit();
    return false;
});
$("#waittodo_text2").click(function(){
    $("#waittodo_form2").submit();
    return false;
});
</script>
<form id="pagerForm" action="system/orderInfo/list.html" method="post">
  <input type="hidden" name="pageNum" value="${pageNo }" />
  <input type="hidden" name="data_name" value="${data_name }">
  <input type="hidden" name=data_state value="${data_state }">
  <input type="hidden" name="data_sfz" value="${data_sfz }">
  <input type="hidden" name="startTime" value="${funcs:formatDateTime(startTime, 'yyyy-MM-dd')}">
  <input type="hidden" name="endTime" value="${funcs:formatDateTime(endTime, 'yyyy-MM-dd')}">
  <input type="hidden" name="waittodoFlag" value="${waittodoFlag }">
</form>
<form id="export_form" action="system/orderInfo/exportExcel.html" method="post">
  <input type="hidden" name="waittodoFlag" value="${waittodoFlag }">
  <input type="hidden" name="pageNum" value="${pageNo }" />
  <input type="hidden" name="data_name" value="${data_name }">
  <input type="hidden" name=data_state value="${data_state }">
  <input type="hidden" name="data_sfz" value="${data_sfz }">
  <input type="hidden" name="startTime" value="${funcs:formatDateTime(startTime, 'yyyy-MM-dd')}">
  <input type="hidden" name="endTime" value="${funcs:formatDateTime(endTime, 'yyyy-MM-dd')}">
</form>
<form id="waittodo_form" onsubmit="return navTabSearch(this);" action="system/orderInfo/list.html" method="post" class="pageForm required-validate">
  <input type="hidden" name="waittodoFlag" value="1" />
    <input type="hidden" name="data_name" value="${data_name }">
  <input type="hidden" name=data_state value="${data_state }">
  <input type="hidden" name="data_sfz" value="${data_sfz }">
  <input type="hidden" name="startTime" value="${funcs:formatDateTime(startTime, 'yyyy-MM-dd')}">
  <input type="hidden" name="endTime" value="${funcs:formatDateTime(endTime, 'yyyy-MM-dd')}">
</form>
<form id="waittodo_form2" onsubmit="return navTabSearch(this);" action="system/orderInfo/list.html" method="post" class="pageForm required-validate">
  <input type="hidden" name="waittodoFlag" value="0" />
    <input type="hidden" name="data_name" value="${data_name }">
  <input type="hidden" name=data_state value="${data_state }">
  <input type="hidden" name="data_sfz" value="${data_sfz }">
  <input type="hidden" name="startTime" value="${funcs:formatDateTime(startTime, 'yyyy-MM-dd')}">
  <input type="hidden" name="endTime" value="${funcs:formatDateTime(endTime, 'yyyy-MM-dd')}">
</form>
<div class="pageHeader">
  <form onsubmit="return navTabSearch(this);" action="system/orderInfo/list.html" method="post" class="pageForm required-validate">
   <input name="waittodoFlag" type="hidden" value="${waittodoFlag}"/>
    <div class="searchBar">
			<table class="searchContent">
				<tr>
				<td>起始日期:</td>
					<td><input type="text" name="startTime"
						value="${funcs:formatDateTime(startTime, 'yyyy-MM-dd')}"
						class="date" /></td>
					<td>结束日期:</td>
					<td><input type="text" name="endTime"
						value="${funcs:formatDateTime(endTime, 'yyyy-MM-dd')}"
						class="date"  /></td>
				<td>客户名:</td>
					<td><input type="text" name="data_name" value="${data_name}" /></td>
				<td>身份证号:</td>
					<td><input type="text" name="data_sfz" value="${data_sfz}" /></td>
				<td>状态：</td>
                <td>  <select name="data_state"
						style="width: 133px; height: 22px;">
							<option value="" >全部</option>
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
				</td>
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
            <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1 || funcs:getSessionLoginUser(pageContext.session).roleID==5}">
            <li> <span style="margin-left:-20px;"><a class="icon" id="export_excel" title="请检索后再导出!"><span>导出EXCEL</span></a></span>  </li>
             </c:if>
            <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
                <li><a class="edit" href="system/orderInfo/toAdd.html?order_id={sid_user}" target="navTab" ><span>修改资料</span></a></li>         
                <li><a class="edit" href="system/orderInfo/toUpdateState.html?order_id={sid_user}" target="dialog" width="330" height="150" mask="true" close="closedialog" ><span>修改状态</span></a></li>         
             <li><a class="delete" href="system/orderInfo/del.html?order_id={sid_user}" target="ajaxTodo" title="确定删除该订单吗?"><span>删除</span></a></li>
             </c:if>
             <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID !=5}">
             <c:if test="${waitToDo>0}">
                  <li>
                  <c:if test="${waittodoFlag!=1}">
                  <a  id="waittodo_text"><span style="color:#ff0000">您当前有${waitToDo}条数据待处理</span></a>
                 </c:if>
                  <c:if test="${waittodoFlag==1}">
                  <a  id="waittodo_text2"><span style="color:#0000ff">正在处理${waitToDo}条数据</span></a>
                 </c:if>
                  </li>
              </c:if>
             <c:if test="${waitToDo==0}">
                  <li><span style="color:#0000ff"> 所有事物处理完毕。</span></li>
              </c:if>
              </c:if>
            </ul>
        </div>

   <table class="table" width="100%" layoutH="140">
    <thead>
      <tr>
     	<th width="1%">编号</th>
        <th width="2%">客户名 </th>
        <th width="3%">业务内容 </th>
        <th width="3%">身份证 </th>
        <th width="2%">详情</th>
        <th width="2%">购房金额 </th>
        <th width="2%">申请金额 </th>
        <th width="2%">放款金额 </th>
        <th width="2%">资料 </th>
        <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1 || funcs:getSessionLoginUser(pageContext.session).roleID==5 || funcs:getSessionLoginUser(pageContext.session).roleID==3}">
        <th width="2%">录单员 </th>
        </c:if>
        <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
        <th width="2%">风控 </th>
        <th width="2%">经理 </th>
        <th width="2%">跟单员 </th>
        </c:if>
        <th width="4%">状态 /操作</th>
        <th width="3%">录入日期</th>
			</tr>
    </thead>
    <tbody>
      <c:forEach items="${requestScope.list}" var="orderInfoList" varStatus="count">
        <tr target="sid_user" rel="${orderInfoList.id}">
          <td>${orderInfoList.id}</td>
          <td>${orderInfoList.data_name} </td>
           <td>
				<c:if test="${orderInfoList.data_ywnr==1}">融资</c:if>
				<c:if test="${orderInfoList.data_ywnr==2}">0首付购房</c:if>
				<c:if test="${orderInfoList.data_ywnr==3}">0首付购车</c:if>
          </td>
          <td>${orderInfoList.data_sfz}</td>
          <td>
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
          <a href="system/orderInfo/toDetail.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="950" height="640"><span style="color:blue">查看</span></a>
         </c:if>
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==2}">
          <a href="system/orderInfo/toDetail.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="950" height="320"><span style="color:blue">查看</span></a>
         </c:if>
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==3}">
          <a href="system/orderInfo/toDetail.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="950" height="640"><span style="color:blue">查看</span></a>
         </c:if>
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==4}">
          <a href="system/orderInfo/toDetail.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="950" height="540"><span style="color:blue">查看</span></a>
         </c:if>
          </td>
          <td>${orderInfoList.data_gfje}</td>
          <td>${orderInfoList.data_sqje}</td>
          <td>${orderInfoList.data_fkje}</td>
          <td>
          <c:if test="${orderInfoList.data_zxbg+orderInfoList.data_jhz+orderInfoList.data_zczm+orderInfoList.data_hkb+orderInfoList.data_yhls+orderInfoList.data_gfht+orderInfoList.data_djsj+orderInfoList.data_fcz+orderInfoList.data_srzm==9}"><font >完整</font></c:if>
          <c:if test="${orderInfoList.data_zxbg+orderInfoList.data_jhz+orderInfoList.data_zczm+orderInfoList.data_hkb+orderInfoList.data_yhls+orderInfoList.data_gfht+orderInfoList.data_djsj+orderInfoList.data_fcz+orderInfoList.data_srzm!=9}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=2}">
	          <font style="color:#969696">不完整</font>
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==2 && orderInfoList.data_state==9}">
	           <a href="system/orderInfo/toAdd.html?order_id=${orderInfoList.id}" target="navTab" ><span style="color:#0000ff">不完整</span></a>
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==2 && orderInfoList.data_state!=9}">
	          <font style="color:#969696">不完整</font>
	          </c:if>
          </c:if>
          </td>
           <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1 || funcs:getSessionLoginUser(pageContext.session).roleID==5 || funcs:getSessionLoginUser(pageContext.session).roleID==3}">
          <td>
          ${funcs:selecUserByAccountId(orderInfoList.salesman).realName}
          </td>
          </c:if>
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
          <td>
          ${funcs:selecUserByAccountId(orderInfoList.controlman).realName}
          </td>
          <td>
          ${funcs:selecUserByAccountId(orderInfoList.manager).realName}
          </td>
          <td>
          ${funcs:selecUserByAccountId(orderInfoList.documentary).realName}
          </td>
          </c:if>
          
          <td>
          <c:if test="${orderInfoList.data_state==9}">
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=2}">
          <font >收集材料(录入)</font>
          </c:if>
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==2}">
          <a href="system/orderInfo/genjin.html?order_id=${orderInfoList.id}" target="ajaxTodo" ><span style="color:#0000ff">跟进</span></a>/
          <a href="system/orderInfo/tijiao.html?order_id=${orderInfoList.id}" target="ajaxTodo" title="提交之后将无法修改！"><span style="color:#0000ff">提交</span></a>
<%-- 	          <a href="system/orderInfo/toControl.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="480"><span style="color:#ff0000">收集材料(提交)</span></a> --%>
          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==10}">
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=3}">
          <font >收集材料(提交)</font>
          </c:if>
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==3}">
	          <a href="system/orderInfo/toControl.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="480"><span style="color:#ff0000">收集材料(提交)</span></a>
          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==11}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=1}">
	        	收集材料(审核)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
	          <a href="system/orderInfo/toIssue.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="580"><span style="color:#ff0000">收集材料(审核)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==12}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=4}">
	        	收集材料(通过)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==4}">
	          <a href="system/orderInfo/toGendan.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="400"><span style="color:#ff0000">收集材料(通过)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==20}">
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=3}">
          <font >业务审批(提交)</font>
          </c:if>
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==3}">
	          <a href="system/orderInfo/toControl.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="480"><span style="color:#ff0000">业务审批(提交)</span></a>
          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==21}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=1}">
	        	业务审批(审核)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
	          <a href="system/orderInfo/toIssue.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="580"><span style="color:#ff0000">业务审批(审核)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==22}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=4}">
	        	业务审批(通过)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==4}">
	          <a href="system/orderInfo/toGendan.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="400"><span style="color:#ff0000">业务审批(通过)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==30}">
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=3}">
          <font >尽职审批(提交)</font>
          </c:if>
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==3}">
	          <a href="system/orderInfo/toControl.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="480"><span style="color:#ff0000">尽职审批(提交)</span></a>
          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==31}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=1}">
	        	尽职审批(审核)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
	          <a href="system/orderInfo/toIssue.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="580"><span style="color:#ff0000">尽职审批(审核)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==32}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=4}">
	        	尽职审批(通过)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==4}">
	          <a href="system/orderInfo/toGendan.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="400"><span style="color:#ff0000">尽职审批(通过)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==40}">
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=3}">
          <font >风控审批(提交)</font>
          </c:if>
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==3}">
	          <a href="system/orderInfo/toControl.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="480"><span style="color:#ff0000">风控审批(提交)</span></a>
          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==41}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=1}">
	        	风控审批(审核)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
	          <a href="system/orderInfo/toIssue.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="580"><span style="color:#ff0000">风控审批(审核)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==42}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=4}">
	        	风控审批(通过)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==4}">
	          <a href="system/orderInfo/toGendan.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="400"><span style="color:#ff0000">风控审批(通过)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==50}">
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=3}">
          <font >放款审批(提交)</font>
          </c:if>
          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==3}">
	          <a href="system/orderInfo/toControl.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="480"><span style="color:#ff0000">放款审批(提交)</span></a>
          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==51}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=1}">
	        	放款审批(审核)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1}">
	          <a href="system/orderInfo/toIssue.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="580"><span style="color:#ff0000">放款审批(审核)</span></a>
	          </c:if>
          </c:if>
          <c:if test="${orderInfoList.data_state==52}">
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID!=4}">
	        	放款审批(通过)
	          </c:if>
	          <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==4}">
	          <a href="system/orderInfo/toGendan.html?order_id=${orderInfoList.id}" target="dialog" mask="true" minable="false" width="400" height="400"><span style="color:#ff0000">放款审批(通过)</span></a>
	          </c:if>
          </c:if>
          
          
          
          
          
          
          
          
          
          
          <c:if test="${orderInfoList.data_state==1}"><font style="color:#FF00CC">申请成功</font></c:if>
         <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==3 || funcs:getSessionLoginUser(pageContext.session).roleID==4 || funcs:getSessionLoginUser(pageContext.session).roleID==5}">
          <c:if test="${orderInfoList.data_state==2}"><font style="color:#969696">申请失败</font></c:if>
         </c:if>
         <c:if test="${funcs:getSessionLoginUser(pageContext.session).roleID==1 || funcs:getSessionLoginUser(pageContext.session).roleID==2}">
          <c:if test="${orderInfoList.data_state==2}"><a class="delete" href="system/orderInfo/redo.html?order_id=${orderInfoList.id}" target="ajaxTodo" title="将此记录重新审核吗？"><span style="color:#969696">申请失败</span></a></c:if>
         </c:if>
          </td>
          <td>${funcs:formatDateTime(orderInfoList.createTime, 'yyyy-MM-dd')}</td>
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

