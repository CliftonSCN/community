package top.clifton.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.clifton.community.dto.AccessTokenDTO;
import top.clifton.community.dto.GithubUser;
import top.clifton.community.mapper.UserMapper;
import top.clifton.community.pojo.User;
import top.clifton.community.provider.GithubProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author Clifton
 * @create 2020/2/1 - 12:03
 */
@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @Autowired
    private UserMapper userMapper;

    /**
     * 请求github登录授权，回调接收code，state
     * @param code
     * @param state
     * @param response
     * @return
     */
    @GetMapping("/callback")
    public String callBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response,
                           HttpServletRequest request){
        //封装请求参数
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        //请求github获取accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        //根据accessToken获取用户信息
        GithubUser userInfo = githubProvider.getUserInfo(accessToken);
        //成功获取用户信息
        if (userInfo != null) {
            //用户是否使用github登录过网站
            User oldUser = userMapper.findByAccountId(userInfo.getId());

            HttpSession session = request.getSession();
            //用户未使用github登录过网站
            if (oldUser == null) {
                User user = new User();
                user.setAccountId(String.valueOf(userInfo.getId()));
                user.setName(userInfo.getName());
                user.setToken(UUID.randomUUID().toString());
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                user.setAvatarUrl(userInfo.getAvatar_url());
                oldUser.setToken(user.getToken());
                //持久化用户信息
                userMapper.insertUser(user);
                session.setAttribute("user",user);
            }else {
                session.setAttribute("user",oldUser);
            }
            response.addCookie(new Cookie("token", oldUser.getToken()));
        }
        return "redirect:/";
    }

}
