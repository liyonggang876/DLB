package com.dlb.testng.httpclient;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.SystemDefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {
    private String url ;
    private org.apache.http.client.CookieStore cookieStore;
    private ResourceBundle resourceBundle;

    @BeforeTest
    public void beforsTest(){
        resourceBundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = resourceBundle.getString("test.url");
    }

    @Test
    public void testGetCookies() throws IOException {
        String uri = resourceBundle.getString("getCookies.uri");
        String testurl = this.url + uri;

        HttpGet httpGet = new HttpGet(testurl);

        DefaultHttpClient httpClient = new SystemDefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(httpGet);
        String res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println("res=" + res);

        this.cookieStore = httpClient.getCookieStore();
        List<Cookie> cookieList = cookieStore.getCookies();
        for (Cookie cookie : cookieList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("name=" + name + ";" + "value=" + value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testCookiesForPost() throws IOException {
        String uri = resourceBundle.getString("test.post.with.cookies");
        String testurl = this.url + uri;
        HttpPost httpPost = new HttpPost(testurl);
        DefaultHttpClient httpClient = new DefaultHttpClient();

        //添加参数
        JSONObject param = new JSONObject();
        param.put("name","huhansan");
        param.put("age","18");

        //设置头信息
        httpPost.setHeader("content-type","application/json");

        //将参数添加到方法中
        StringEntity stringEntity = new StringEntity(param.toString(),"utf-8");
        httpPost.setEntity(stringEntity);
        String res;
        //设置cookies
        httpClient.setCookieStore(this.cookieStore);
        //执行post方法
        HttpResponse httpResponse = httpClient.execute(httpPost);
        res = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println("res=" + res);

        JSONObject jsonObject = new JSONObject(res);
        String name = (String) jsonObject.get("huhansan");
        System.out.println("name1= " + name);



    }
}
