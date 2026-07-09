package com.ust.sdet.selenium.support;
import com.ust.sdet.selenium.support.TestEnvironment;
public  class Config {
    private Config()
    {

    }
    public static String baseUrl()
    {
//        return System.getProperty("baseUrl","http://localhost:5173").replaceAll("/$","");
        return TestEnvironment.optional("BASE_URL", "http://localhost:5173")
                .replaceAll("/$", "");
    }
    public static String catalogUrl()
    {
        return  baseUrl()+"/catalog";
    }
    public static  String loginUrl()
    {
        return baseUrl()+"/login";
    }
    public static boolean headless()
    {
        return Boolean.parseBoolean(TestEnvironment.optional("headless","false"));
    }

}
