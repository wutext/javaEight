package com.learn.chapter.ten.optionalBasic;

import com.fasterxml.jackson.datatype.jdk8.OptionalIntDeserializer;
import com.fasterxml.jackson.datatype.jdk8.OptionalSerializer;
import com.learn.chapter.ten.util.FormatTramslate;
import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.Properties;

public class TestBasic {

    static int getProperties(Properties p, String name) {

         return Optional.ofNullable(p.getProperty(name))
                .flatMap(FormatTramslate::stringToInt)
                .filter(a -> a>0)
                .orElse(0);
    }

    public static void isNull() {
        String s = null;
        Optional<String> p = Optional.ofNullable(s);
    }

    public static void main(String[] args) {
        Properties p = new Properties();
        p.setProperty("a", "5");
        p.setProperty("b", "true");
        p.setProperty("c", "-3");
        p.setProperty("d", "ddd");
        int num = getProperties(p, "a");
        System.out.println(num);
        //isNull();
    }
}
