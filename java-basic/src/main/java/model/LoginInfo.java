package model;

import lombok.Builder;
import lombok.Data;

/**
 * 登录信息
 * @author BB
 * @create 2021/2/5 7:35 上午
 */
@Builder
@Data
public class LoginInfo {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 登录密码
     */
    private String loginPass;
}
