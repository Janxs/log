package com.log.aop;

import org.springframework.util.StringUtils;

public enum  UrlEnum {

    EDIT("/testPost", "编辑");

    private final String value;
    private final String nickname;

    UrlEnum(String value, String nickname) {
        this.value = value;
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getValue() {
        return value;
    }

    public static boolean exitPaymentType(String type) {
        if (StringUtils.isEmpty(type)) {
            return false;
        }
        for (UrlEnum ele : UrlEnum.values()) {
            if (ele.value.equals(type)) {
                return true;
            }
        }
        return false;
    }
}
