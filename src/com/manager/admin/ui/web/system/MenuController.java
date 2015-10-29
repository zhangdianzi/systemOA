package com.manager.admin.ui.web.system;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manager.admin.common.Constants;
import com.manager.admin.common.exception.AdminException;
import com.manager.admin.common.service.MenuService;
import com.manager.admin.common.to.Menu;
import com.manager.admin.common.utils.DwzJsonUtil;
import com.manager.admin.common.utils.RequestUtil;


@Controller
@RequestMapping("/system/menu")
public class MenuController implements Constants {

    @Resource
    private MenuService menuService;

    @RequestMapping("/list.html")
    public String list(HttpServletRequest request) throws AdminException {
        List<Menu> menus=menuService.getMenuList();
        request.setAttribute("menus", menus);
        return "/pages/system/menu_list";
    }

    @RequestMapping("/goedit.html")
    public String goEditor(HttpServletRequest request, HttpServletResponse response) throws AdminException {
        Integer id=RequestUtil.getInteger(request, "id",0);
        if(null != id) {
            Menu menu=menuService.getById(id);
            request.setAttribute("menu", menu);
        }
        return "/pages/system/menu_edit";
    }

    @RequestMapping("/goselect.html")
    public String goSelect(HttpServletRequest request, HttpServletResponse response) throws AdminException {
        List<Menu> menus=menuService.getMenuList();
        request.setAttribute("menus", menus);
        return "/pages/system/menu_select";
    }

    @RequestMapping("/save.html")
    public ModelAndView save(HttpServletRequest request) throws AdminException {
        Integer id=RequestUtil.getInteger(request, "id",0);
        String name=RequestUtil.getString(request, "name");
        Integer parentId=RequestUtil.getInteger(request, "parentId",0);
        String lcode=RequestUtil.getString(request, "parentIdPath");
        Integer orderNo=RequestUtil.getInteger(request, "orderNo",0);
        String url=RequestUtil.getString(request, "url");
        String remark=RequestUtil.getString(request, "remark");
        Integer type = RequestUtil.getInteger(request, "type",0);
        
        Menu record=new Menu();
        record.setId(id);
        record.setName(name);
        record.setType(type);
        record.setParentId(parentId);
        record.setParentIdPath(lcode);
        record.setOrderNo(orderNo);
        record.setRemark(remark);
        record.setUrl(url);
        record.setCreateTime(new Date());
        menuService.saveOrUpdate(record);
        return new ModelAndView(JSON_VIEW, JSON_ROOT, DwzJsonUtil.getOkStatusMsg("更新成功"));
    }

    @RequestMapping("/del.html")
    public String deleteTreeNode(HttpServletRequest request) throws AdminException {
        List<Integer> menuIds=RequestUtil.getIntegers(request, "treecheckbox");
        if(null == menuIds || menuIds.isEmpty()) {
            throw new AdminException("请选择要删除的节点！");
        }
        for(Integer menuId: menuIds) {
            menuService.deleteById(menuId);
        }
        return list(request);
    }
}
