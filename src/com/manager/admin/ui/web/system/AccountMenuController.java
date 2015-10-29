package com.manager.admin.ui.web.system;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.manager.admin.common.Constants;
import com.manager.admin.common.exception.AdminException;
import com.manager.admin.common.service.AccountMenuService;
import com.manager.admin.common.service.AccountService;
import com.manager.admin.common.service.MenuService;
import com.manager.admin.common.to.Account;
import com.manager.admin.common.to.Menu;
import com.manager.admin.common.utils.DwzJsonUtil;
import com.manager.admin.common.utils.RequestUtil;

@Controller
@RequestMapping("/system/account_menu")
public class AccountMenuController implements Constants
{

    @Autowired
	private MenuService menuService;

    @Autowired
	private AccountService accountService;
	
    @Autowired
	private AccountMenuService accountMenuService;

	@RequestMapping("/list.html")
	public String listSystemMenus(HttpServletRequest request, HttpServletResponse response) throws AdminException
	{
		Integer accountId = RequestUtil.getInteger(request, "accountId",0);
		Account account=accountService.selectByPrimaryKey(accountId);
		if (null == account)
		{
			throw new AdminException("用户不存在！");
		}
		List<Menu> menus = menuService.getMenuList();
		List<Menu> accountMenus = accountMenuService.getMenuListByAccountId(account.getId());
		List<Integer> accountMenuIds = new ArrayList<Integer>();
		for (Menu menu : accountMenus)
		{
			accountMenuIds.add(menu.getId());
		}
		request.setAttribute("menus", menus);
		request.setAttribute("accountMenuIds", accountMenuIds);
		return "/pages/system/account_menu_list";
	}

	@RequestMapping("/save.html")
	public ModelAndView save(HttpServletRequest request) throws AdminException
	{
		Integer accountId = RequestUtil.getInteger(request, "accountId",0);
		List<Integer> menuIds = RequestUtil.getIntegers(request, "treecheckbox");
		if (null == accountId || accountId <= 0)
		{
			throw new AdminException("非法操作！");
		}
		if (null == menuIds || menuIds.isEmpty())
        {
            throw new AdminException("请选择要保存的节点！");
        }
		Account account = accountService.selectByPrimaryKey(accountId);
		if (null == account)
		{
			throw new AdminException("用户不存在！");
		}
		accountMenuService.saveAccountMenus(account, menuIds); // 更新权限菜单
		return new ModelAndView(JSON_VIEW, JSON_ROOT, DwzJsonUtil.getOkStatusMsg(null));
	}
}
