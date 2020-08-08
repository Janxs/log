package com.log.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class LogAop {

    @Autowired
    private LogFactory logFactory;

    @Pointcut("@annotation(com.log.aop.LogAnn)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object resultData = null;
        // 获取此次的请求URL
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnn annotation = method.getAnnotation(LogAnn.class);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestURI = request.getRequestURI();
        // 根据URL去到具体的日志实现类保存修改前的日志
        LogService logService = logFactory.getLogService(requestURI);
        if (logService != null) {
            Object o = logService.saveBeforeChange(getRequestParam(request));
        }
        try {
            resultData = joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable e) {
            // 记录异常信息
        }
        // 完事之后，更新日志记录，保存这次操作的结果,这里可以根据设置的值去区分

        if (annotation.saveResponse()) {

//o.setResponse(resultData);
        }

//        update(o);

        return resultData;


    }


    public static Map getRequestParam(HttpServletRequest req) {
        Map<String, String[]> requestMap = req.getParameterMap();
        Map<String, String> paramsMap = new HashMap<>();
        if (requestMap == null || requestMap.size() == 0) {
            return paramsMap;
        }
        requestMap.forEach((key, values) -> {
            String strs = "";
            for (String value : values) {
                strs = strs + value;
            }
            paramsMap.put(key, strs);
        });
        return paramsMap;
    }


}
