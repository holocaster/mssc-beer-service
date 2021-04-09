package br.com.prcompany.msscbeerservice.utils;

import java.util.Objects;

public abstract class StringUtils {

    public static boolean isEmpty (String msg) {
       return Objects.isNull(msg) || "".equals(msg);
    }
}
