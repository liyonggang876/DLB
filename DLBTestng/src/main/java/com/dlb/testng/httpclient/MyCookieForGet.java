package com.dlb.testng.httpclient;

import org.apache.http.HttpResponse;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookieForGet {
    private String url;
    private ResourceBundle bundle;
    private org.apache.http.client.CookieStore store;
    @BeforeTest
    public void BeforeTest(){
        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String testUrl = this.url + bundle.getString("getCookies.uri");
        String res ;

        HttpGet httpget = new HttpGet(testUrl);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpget);
        res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println("res1=" + res);

        //获取cookies
        this.store = httpClient.getCookieStore();
        List<Cookie> cookieList = store.getCookies();
        for(Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name=" + name + ";" + "value=" + value);
        }


    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetWithCookies() throws IOException {
        String uri = bundle.getString("test.get.with.cookies");
        String testurl = this.url + uri;
        HttpGet httpget = new HttpGet(testurl);
        DefaultHttpClient httpclient = new DefaultHttpClient();
        httpclient.setCookieStore(this.store);
        HttpResponse httpResponse = httpclient.execute(httpget);


        int stautsCode = httpResponse.getStatusLine().getStatusCode();
        if (stautsCode == 200){
            String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
            System.out.println("res2=" + res);
        }

    }

}
