package com.manager.admin.common.exception;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.manager.admin.common.utils.DwzJsonUtil;
import com.manager.admin.common.utils.RequestUtil;

public class AdminExceptionHandler implements HandlerExceptionResolver {

    private static Logger logger=Logger.getLogger(AdminExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String msg=null;
        msg=ex.getMessage();
        if(msg == null){
        	return null;
        }
        String tmp="AdminException:";
        int ind=msg.indexOf(tmp);
        if(ind > 0) {
            msg=msg.substring(ind + tmp.length());
        }
        logger.error(msg, ex);
        handlerError(request, response, msg);
        return null;
    }

    public static void handlerError(HttpServletRequest request, HttpServletResponse response, String msg) {
        if(response.isCommitted()) {
            return;
        }
        response.setCharacterEncoding("UTF-8");
        StringBuilder buf=new StringBuilder();
        if(RequestUtil.isAjax(request)) {
            response.setStatus(200);
            response.setContentType("application/json; charset=UTF-8");
            String jsonStr=null;
            try {
                jsonStr=DwzJsonUtil.getJSON(DwzJsonUtil.getErrorStatusMsg(msg));
            } catch(JsonGenerationException e) {
                e.printStackTrace();
            } catch(JsonMappingException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
            buf.append(jsonStr);
        } else {
            response.setStatus(200);
            response.setContentType("text/html; charset=UTF-8");
            buf
                .append("<html><head><title>错误-后台</title><meta http-equiv='Content-Type' content='text/html; charset=UTF-8'></head>");
            buf.append("<body>出错了！<br/><span style='color:red'>").append(msg).append("</span><br/><br/>");
            buf.append("<input type='button' onclick='window.history.back()' value='返回'/></body></html>");
        }
        try {
            OutputStream out=response.getOutputStream();
            out.write(buf.toString().getBytes("UTF-8"));
            out.flush();
            out.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
