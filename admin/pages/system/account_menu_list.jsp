<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="funcs" uri="funcs" %>
<script type="text/javascript">
$(document).ready(function(){
	accountMenu = new dTree('accountMenu', 'select');
	accountMenu.clearCookie();
  // id, pid, name, url, title, target, icon, iconOpen, open, isChecked, rel
  <c:forEach var="menu" items="${requestScope.menus}">
  accountMenu.add('${menu.id}', '${menu.parentId}', '${menu.name}', '',  '${menu.name}', "", '', '', '', '${funcs:isContains( menu.id, requestScope.accountMenuIds)}', "");
  </c:forEach>
  $('#accountMenuList').html(accountMenu.toString());
})

function saveUserTreeNode(form){
    var tmpForm=$(form);
    var checkedBox=$(":checkbox:checked", tmpForm);
    if(checkedBox.size()==0){
        return false;
    }
    alertMsg.confirm("确定要提交吗？", {
        okCall: function(){
          validateCallback(form, dialogAjaxDone);;
        }
    });
    return false;
}
</script>
<form onsubmit="return saveUserTreeNode(this)" action="system/account_menu/save.html" method="post">
  <input type="hidden" name="accountId" value="${param.accountId}">
  <div class="pageContent">
    <div id="accountMenuList" layoutH="50">
    </div>
    <div class="formBar">
      <ul>
        <li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
        <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
      </ul>
    </div>
  </div>
</form>