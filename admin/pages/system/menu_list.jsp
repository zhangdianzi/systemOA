<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<script type="text/javascript">
$(document).ready(function(){
  sysMenuList = new dTree('sysMenuList', 'list');
  <c:forEach var="menu" items="${requestScope.menus}" varStatus="rowstat">
    sysMenuList.add(${menu.id},${menu.parentId},'${menu.name}(${menu.type})','system/menu/goedit.html?id=${menu.id}', '${menu.name}', "dialog",'','',true,false,"menu_dialog_${menu.id}",{close:"closedialog"});
  </c:forEach>
  $("#menuList").append($(sysMenuList.toString()));
})

function deleteTreeNode(form){
  var tmpForm=$(form);
  var checkedBox=$(":checkbox:checked", tmpForm);
  if(checkedBox.size()==0){
      return false;
  }
  alertMsg.confirm("确定要删除吗？", {
      okCall: function(){
        navTabSearch(form);
      }
  });
  return false;
}
</script>
<form id="pagerForm" action="system/menu/list.html" method="get">
  <input type="hidden" name="pageNum" value="1" />
</form>
<form id="menuSearchForm" onsubmit="return deleteTreeNode(this);" action="system/menu/del.html" method="get" >
  <div class="panel collapse" minH="30">
    <h1>功能菜单管理</h1>
    <div>
      <ul class="rightTools">
        <li><a class="button" target="dialog" rel="menuEditor" href="system/menu/goedit.html" mask="true" minable="false" close="closedialog" width="600" height="230"><span>添加</span></a></li>
        <li><div class="buttonActive"><div class="buttonContent"><button type="submit">删除</button></div></div></li>
      </ul>
    </div>
  </div>
<div class="pageContent">
<div id="menuList" layoutH="100">
</div>
</div>
</form>