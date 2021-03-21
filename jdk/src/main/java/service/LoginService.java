package service;

import model.LoginInfo;
import model.ServerInfo;

/**
 * @author BB
 * @create 2021/2/5
 */
public interface LoginService {

    /**
     * 登录
     * @param serverInfo
     * @param loginInfo
     * @return
     */
    String login(ServerInfo serverInfo, LoginInfo loginInfo);
}
