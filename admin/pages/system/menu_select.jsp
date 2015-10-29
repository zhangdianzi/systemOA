<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<script type="text/javascript">
$(document).ready(function(){
  menuSelect = new dTree('menuSelect', 'edit');
  <c:forEach var="menu" items="${requestScope.menus}" varStatus="rowstat">
   menuSelect.add('${menu.id}','${menu.parentId}','${menu.name}',"javaScript:selectSystemMenuItem(${menu.id},'${menu.parentIdPath}','${menu.name}');$.pdialog.closeCurrent();", '${menu.name}', "",'','','',false,"menu_dialog_${menu.id}");
  </c:forEach>
  $("#menuSelectList").append($(menuSelect.toString()));
})
</script>
<div class="pageContent">
<div id="menuSelectList" layoutH="20"></div>
</div>