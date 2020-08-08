package com.log.aop;

import java.util.Map;

public interface LogService {

    boolean acceptLog(String url);

    /**
     * 保存改变之前的日志
     * @param req 请求参数
     * @return 返回日记记录
     */
    Object saveBeforeChange(Map<String, String> req);

}
