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
                    //QQ返回的用户昵称
                    String nickname = userInfoBean.getNickname();
                    //头像
                    String icon = userInfoBean.getAvatar().getAvatarURL50();
                    //封装用户信息
                    packageUserInfo(openID, nickname, icon, session);
                }
            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return "redirect:" + session.getAttribute("url");
    }


    /**
     * 把用户信息封装到数据库中
     *
     * @param thirdId
     * @param nickname
     * @param icon
     * @param session
     */
    private void packageUserInfo(String thirdId, String nickname, String icon, HttpSession session) {
        User user = new User();
        user.setThirdId(thirdId);
        user.setName(nickname);
        user.setIcon(icon);
        User daoUser = userService.getUserByThirdId(thirdId);
        //说明是第一次登陆
        if (daoUser == null) {
            this.userService.addUser(user);
        } else if (user.getThirdId().equals("8E1544B0D015EC98612B39DD5D5B90B0")) {
            user = daoUser;
        } else {
            user.setId(daoUser.getId());
            this.userService.updateUserByThirdId(user);
        }

        Link link = linkService.getLinkByUserId(user.getId());
        //判断是否申请过友链
        if (link != null) {
            session.setAttribute("isApply", "1");
        }
        //保存到session中
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(60 * 60 * 24 * 7);
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
            session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            return R.ERROR();
        }
        return R.OK();
    }

}
