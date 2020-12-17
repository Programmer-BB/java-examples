package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @author BB
 */
public class DynamicProxy {

    public static void main(String[] args) {

        // 为接口生成代理实现类
        proxyByInterface();

        // 为类生成代理类
        proxyByClass();
    }

    /**
     * 为接口生成代理对象
     */
    public static void proxyByInterface() {
        InvocationHandler handler = (proxy, method, args) -> {
            if ("printMsg".equals(method.getName())) {
                System.out.println("你好, " + args[0]);
            } else if ("sayHello".equals(method.getName())) {
                return "hi, " + args[0];
            }
            return null;
        };

        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, handler);
        hello.printMsg("张三");
        String msg = hello.sayHello("李四");
        System.out.println(msg);
    }

    /**
     * 为实现类生成代理对象
     */
    public static void proxyByClass() {
        HelloWorld hw = new HelloWorld();

        InvocationHandler handler = (proxy, method, args) -> {
            System.out.println("代理开始");
            Object obj = method.invoke(hw, args);
            System.out.println("代理结束");
            return obj;
        };

        Hello hwProxy = (Hello) Proxy.newProxyInstance(hw.getClass().getClassLoader(), hw.getClass().getInterfaces(), handler);
        hwProxy.printMsg("Java");
        String msg = hwProxy.sayHello("Java");
        System.out.println("代理返回的消息:" + msg);
    }
}


/**
 * 接口
 */
interface Hello {

    void printMsg(String msg);

    String sayHello(String name);
}

/**
 * 实现了接口的类
 */
class HelloWorld implements Hello {

    @Override
    public void printMsg(String msg) {
        System.out.println("helloWorld, " + msg + " - 未使用代理");
    }

    @Override
    public String sayHello(String name) {
        return "Hi, helloWorld, " + name + " - 未使用代理";
    }
}