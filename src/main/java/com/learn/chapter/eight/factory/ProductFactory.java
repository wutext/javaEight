package com.learn.chapter.eight.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class ProductFactory {

   final static Map<String, Supplier<Product>> map = new HashMap();

    static {
        map.put("lona", Lona::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    public static Product getFactory(String name) {
        Supplier<Product> p = map.get(name);
        if(p!=null) return p.get();
        throw new IllegalArgumentException("the bean is error");
    }
}
