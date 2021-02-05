package service.impl;

import lombok.extern.slf4j.Slf4j;
import model.LoginInfo;
import model.ServerInfo;
import org.springframework.stereotype.Service;
import service.LoginService;

/**
 * linux系统登录实现
 *
 * @author BB
 * @create 2021/2/5
 */
@Slf4j
@Service
public class LinuxLoginServiceImpl implements LoginService {

    @Override
    public String login(ServerInfo serverInfo, LoginInfo loginInfo) {
        String loginResult = "";
        if ("admin".equals(loginInfo.getLoginName()) && "admin".equals(loginInfo.getLoginPass())) {
            log.info("用户:{}, 登录:{}, 成功！", loginInfo.getLoginName(), serverInfo.getUrl());
            loginResult = "登录成功";
        } else {
            loginResult = "用户名或密码错误";
        }
        return loginResult;
    }
}
