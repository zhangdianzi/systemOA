<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>


<div class="pageContent">
    <form method="post" action="system/account/modifyPassword.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);" >
      <div class="pageFormContent" layoutH="50">
        <dl class="unit">
            <dt>新密码：</dt>
            <dd><input name="newPwd" id="newPassword" type="password"   class="required" minlength="6" maxlength="20"/></dd>
        </dl>
        <dl class="unit">
            <dt>确认密码：</dt>
            <dd><input name="confirmPwd" type="password" equalTo="#newPassword" class="required" minlength="6" maxlength="20"/></dd>
        </dl>
        
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