package com.log.aop;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EditServiceImpl implements LogService{
    @Override
    public boolean acceptLog(String url) {
        return UrlEnum.EDIT.getValue().equals(url);
    }

    @Override
    public Object saveBeforeChange(Map<String, String> req) {
        // 这里根据参数req 先去查找这次要改变的数据，然后保存一条新的日志记录
//        Log log = save();
//        return log;
        System.out.println("这里查到啦，也记录了");
        return null;
    }
}
