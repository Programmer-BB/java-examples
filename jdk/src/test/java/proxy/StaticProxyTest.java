package proxy;

import lombok.extern.slf4j.Slf4j;
import model.LoginInfo;
import model.ServerInfo;
import org.junit.Before;
import org.junit.Test;
import service.LoginService;
import service.impl.LinuxLoginServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * 静态代理测试类
 */
@Slf4j
public class StaticProxyTest {

    private LoginService loginService;
    private ServerInfo serverInfo;
    private LoginInfo loginInfo;

    @Before
    public void before() {
        loginService = (LoginService) Proxy.newProxyInstance(LoginService.class.getClassLoader(), new Class[]{LoginService.class}, newLoginServiceInterfaceInstance(LoginService.class));
        serverInfo = ServerInfo.builder().url("http://localhost:22/login").build();
        loginInfo = LoginInfo.builder().loginName("admin").loginPass("admin").build();
    }

    private InvocationHandler newLoginServiceInterfaceInstance(Class<LoginService> clazz) {
        return (proxy, method, args) -> {
            Object result = null;
            if (!Objects.isNull(args) && args.length == 2) {
                ServerInfo param1 = (ServerInfo) args[0];
                LoginInfo param2 = (LoginInfo) args[1];
                if ("test".equals(param2.getLoginName()) && "test".equals(param2.getLoginPass())) {
                    log.info("用户:{}, 登录:{}, 成功！", loginInfo.getLoginName(), serverInfo.getUrl());
                    result = "登录成功";
                } else {
                    result = "用户名或密码错误";
                }
                return result;
            } else {
                throw new IllegalArgumentException();
            }
        };
    }


    private InvocationHandler newLoginServiceClassInstance(Object obj) {
        return (proxy, method, args) -> {
            log.info("调用之前...");
            Object result = method.invoke(obj, args);
            log.info("调用之后...");
            return result;
        };
    }

    @Test
    public void testLogin() {
        log.info("结果: {}", loginService.login(serverInfo, loginInfo));
    }

    @Test
    public void testClassProxy() {
        LoginService loginService1 = new LinuxLoginServiceImpl();
        LoginService loginServiceProxy = (LoginService) Proxy.newProxyInstance(loginService1.getClass().getClassLoader(), loginService1.getClass().getInterfaces(), newLoginServiceClassInstance(loginService1));
        log.info("结果: {}", loginServiceProxy.login(serverInfo, loginInfo));
    }

}
