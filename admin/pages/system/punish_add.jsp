<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>

<div class="pageContent">
    <form method="post" action="system/punish/add.html" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
        <div class="pageFormContent" layoutH="56">
            <p>
                <label>被通知者：</label>
               <select name="account_id"
						style="width: 133px; height: 22px;">
							<option value=-1>全体同事</option>
							<c:forEach items="${requestScope.accountList}" var="r">
								<option value="${r.id}" >
								${r.realName}
								</option>
							</c:forEach>
							</select>
            </p>
            <p>
                <label>公告类型：</label>
                   <select name="kinds" >
                   		 <option value=3>通知公告</option>
                         <option value=1>奖励公告</option>
                         <option value=2>处罚公告</option>
                </select>
            </p>
            <p>
                <label>金额：</label>
                <input name="money" type="text" size="30" class=""/>
            </p>
            <p>
                <label>说明：</label>
                <input name="reason" type="text" size="30" class=""/>
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