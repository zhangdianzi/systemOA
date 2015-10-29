<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="funcs" uri="funcs" %>


        
<div class="ajaxcontent lightbox">
  			 <p>
                <label>${funcs:getSessionLoginUser(pageContext.session).realName},您好！</label>
            </p>
			 <p>
                <label>您的基本薪资：</label>
                <label><font style="color:#ff0000">${funcs:getSessionLoginUser(pageContext.session).baseSalary}</font></label>
            </p>
			 <p>
                <label>您的当前薪资：</label>
                <label><font style="color:#ff0000">${funcs:getSessionLoginUser(pageContext.session).curSalary}</font></label>
            </p>
			 <p>
                <label>当前薪资=基本薪资+奖励金额-扣款金额</label>
            </p>
</div>