package com.log.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 日志方法工厂
 */
@Component
public class LogFactory {

    @Autowired
    private Map<String, LogService> logServiceMap;

    public LogService getLogService(String url) {
        if (!UrlEnum.exitPaymentType(url)) {
            return null;
        }

        if (logServiceMap == null || logServiceMap.size() == 0) {
            return null;
        }
        for (LogService logService : logServiceMap.values()) {
            if (logService.acceptLog(url)) {
                return logService;
            }
        }
        return null;
    }
}
