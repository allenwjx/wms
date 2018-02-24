package com.zeh.wms.web.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zeh.jungle.utils.page.SingleResult;
import com.zeh.wms.web.controller.BaseController;
import com.zeh.wms.web.form.LoginForm;

/**
 * @author allen
 * @create $ ID: LoginController, 18/2/24 20:10 allen Exp $
 * @since 1.0.0
 */
@Controller
public class LoginController extends BaseController {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private LogoutHandler         logoutHandler;

    /**
     * 后台用户登录
     *
     * @param form the form
     * @return single result
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public SingleResult login(@RequestBody LoginForm form, HttpServletRequest request) {
        String username = form.getUsername();
        String password = form.getPassword();
        if (StringUtils.isBlank(username)) {
            return createErrorResult("请输入用户名");
        }
        if (StringUtils.isBlank(password)) {
            return createErrorResult("请输入密码");
        }
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return createSuccessResult();
        } catch (UsernameNotFoundException e) {
            return createErrorResult("用户不存在");
        } catch (BadCredentialsException e) {
            return createErrorResult("用户名密码不正确");
        } catch (AuthenticationException e) {
            return createErrorResult(e);
        }
    }

    /**
     * 后台用户登出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logoutHandler.logout(request, response, auth);
        }
        return "redirect:/page/login/index";
    }
}