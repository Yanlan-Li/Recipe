package com.example.recipe.util;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpUtils {
    public static final String TAG = "MyTag" + "HttpUtils";
    private static String resultStr;


    //向指定URL发送指定消息，并返回从服务器端获取到的String类型的数据
    public static String send(String strUrl, String content, String method, String contentType) {
        String result;
        try {
            URL url = new URL(strUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置get或者post
            connection.setRequestMethod(method);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-Type", contentType);
            connection.addRequestProperty("Connection", "Keep-Alive");
            OutputStream os = connection.getOutputStream();
            Log.e(TAG, "send: " + content);
            os.write(content.getBytes());
            os.flush();
            os.close();
            if (connection.getResponseCode() == 200) {
                result = DataTransfer.streamToString(connection.getInputStream());
                Log.d(TAG, "send: " + result);
                return result;
            } else {
                result = "error";
                Log.d(TAG, "send: " + connection.getResponseCode());
                return result;
            }
        } catch (SocketTimeoutException e) {
            result = "NetWorkBroken";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "other Error";
            return result;
        }
    }

    //只处理GET请求
    public static String send(String strUrl){
        String result;
        try {
            URL url = new URL(strUrl);
            Log.e(TAG, "send: 发送的URL为:"+strUrl );
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.addRequestProperty("Connection", "Keep-Alive");
            if (connection.getResponseCode() == 200) {
                result = DataTransfer.streamToString(connection.getInputStream());
                Log.d(TAG, "send: " + result);
                return result;
            } else {
                result = "error";
                Log.d(TAG, "send: " + connection.getResponseCode());
                return result;
            }
        } catch (SocketTimeoutException e) {
            result = "Socket error";
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            result = "IO error";
            return result;
        }
    }

/*
    //登录操作
    public static State login(String strUrl){
        State result=new State();
        Gson gson=new Gson();
        resultStr=send(strUrl," ","POST","application/json");
        if (resultStr.equals("NetWorkBroken")){
            result.setCode(404);
            result.setMessage("出现网络问题，请检查是否联网！");
        }else if (resultStr.equals("other Error")){
            result.setCode(404);
            result.setMessage("出现其他故障！");
        }else{
            result=gson.fromJson(resultStr,State.class);
        }
        return result;
    }
*/

    //根据type获取菜谱列表
    public static List<RecipeData> getRecipeByKey(String key){
        List<RecipeData> recipeList=new ArrayList<>();
        Gson gson=new Gson();
        String strUrl="http://47.106.76.106:8080/recipeSys/recipe/search?key="+key;
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                resultStr=send(strUrl);
                Log.e(TAG, "run: 获取到的resultStr为"+resultStr );
            }
        });
        thread.start();
        try{
            thread.join();
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(resultStr.equals("error")||resultStr.equals("[]")) return null;
        else{
            recipeList=gson.fromJson(resultStr,new TypeToken<List<RecipeData>>(){}.getType());
            return recipeList;
        }
    }
    //随机获取n个菜谱
    public static List<RecipeData> getRandomRecipe(int n){
        List<RecipeData> recipeList=new ArrayList<>();
        Gson gson=new Gson();
        String strUrl="http://47.106.76.106:8080/recipeSys/randomRecipe?randomRecipeNumber="+n;
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                resultStr=send(strUrl);
                Log.e(TAG, "run: 获取到的resultStr为"+resultStr );
            }
        });
        thread.start();
        try{
            thread.join();
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        if(resultStr.equals("error")||resultStr.equals("[]")) return null;
        else{
            recipeList=gson.fromJson(resultStr,new TypeToken<List<RecipeData>>(){}.getType());
            return recipeList;
        }
    }


}
