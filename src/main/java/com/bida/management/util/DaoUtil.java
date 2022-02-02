package com.bida.management.util;

import java.util.Objects;

public class DaoUtil {

    public static String like(String param) {
        return "%" + (Objects.isNull(param) ? "" : param.trim()) + "%";
    }
}
