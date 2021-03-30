package com.annis;

import java.net.URI;

public class UriTest {
    public static void main(String[] args) {
        String uriStr = "cqcbank://mobile.bank/scanCode/mobileLoginAuth?code={code}";
//        String uriStr = "https://www.baidu.com/path/path?name=lee";
        URI uri = URI.create(uriStr);
        System.out.println("scheme:"+uri.getScheme());
        System.out.println("host:"+uri.getHost());
        System.out.println("path:"+uri.getPath());
        System.out.println("query:"+uri.getQuery());
    }
}
