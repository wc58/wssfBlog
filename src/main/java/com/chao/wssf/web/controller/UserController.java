package com.chao.wssf.web.controller;

import com.chao.wssf.entity.Link;
import com.chao.wssf.entity.User;
import com.chao.wssf.service.ILinkService;
import com.chao.wssf.service.IUserService;
import com.chao.wssf.util.R;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.Avatar;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private ILinkService linkService;
    @Autowired
    private IUserService userService;

    private String loginPage = "";

    /**
     * 请求登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/loginByQQ")
    public String loginByQQ(String url, HttpServletRequest request, HttpSession session) {
        loginPage = url;
        System.out.println(url);
        System.out.println(session);
        session.setAttribute("url", url);
        String authorizeURL = null;
        try {
            authorizeURL = new Oauth().getAuthorizeURL(request);
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        authorizeURL = new StringBuffer("redirect:").append(authorizeURL).toString();
        return authorizeURL;
    }

    /**
     * 回调
     *
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("/qqLoginCallback")
    public String connection(HttpServletRequest request, HttpSession session) {
        System.out.println(session);
        System.out.println(session.getAttribute("url"));
        if (session.getAttribute("user") != null) {
            return "redirect:" + session.getAttribute("url");
        }
        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken = null;
            String openID = null;
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();
                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));
                OpenID openIDObj = new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
                if (userInfoBean.getRet() == 0) {
                    //用户信息
                    User user = new User();
                    user.setName(userInfoBean.getNickname());
                    Avatar avatar = userInfoBean.getAvatar();
                    String avatarURL50 = avatar.getAvatarURL50();
                    user.setIcon(avatarURL50);
                    user.setThirdId(openID);
                    User thirdIdUser = userService.getUserByThirdId(openID);
                    //第二次登录则不需要保存数据库
                    if (thirdIdUser == null) {
                        userService.addUser(user);
                    } else {
                        user = thirdIdUser;
                    }
                    Link link = linkService.getLinkByUserId(user.getId());
                    if (link != null) {
                        session.setAttribute("isApply", "1");
                    }
                    session.setAttribute("user", user);
                    session.setMaxInactiveInterval(60 * 60 * 24 * 7);
                }
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "redirect:" + session.getAttribute("url");
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping("logout")
    @ResponseBody
    public R logout(HttpSession session) {
        try {
            session.removeAttribute("user");
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

}
