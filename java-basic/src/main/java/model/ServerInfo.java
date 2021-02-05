package model;

import lombok.Builder;
import lombok.Data;

/**
 * 服务器信息
 * @author BB
 * @create 2021/2/5 7:34 上午
 */
@Builder
@Data
public class ServerInfo {

    /**
     * 访问地址
     */
    private String url;

}
