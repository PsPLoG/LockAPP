package com.example.kmh.lockapp.http;

import android.util.Log;


import com.example.kmh.lockapp.adapter.AccountHistoryItem;
import com.example.kmh.lockapp.adapter.Ad_For_Fill_PointData;
import com.example.kmh.lockapp.adapter.PointStoreProductData;
import com.example.kmh.lockapp.data.CardDataItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.TlsVersion;

public class HttpClient {
    public static ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256)
            .build();
    private static OkHttpClient client = new OkHttpClient.Builder()
            .connectionSpecs(Collections.singletonList(spec))
            .build();
    public static String serverIp = "https://dailycalli.tk";
    public static JSONObject jArr = null;
    public static String email;
    public static String user_id;
    public static String user_nickname;
    public static String token;
    public static ArrayList<CardDataItem> mlist;


    public OkHttpClient getInstance() {
        return client;
    }

    public static String signup(String id, String password) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("id",id);
        json.put("password",password);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        okhttp3.RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrZXlfc2xpZGUiLCJVU0VSTkFNRSI6ImtleSIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.kxum_G7eqbL2BXmTr73tiLWzSxS4J6nISwBIArNPxd4")
                .url("https://key.apps.dev.clayon.io/formlogin")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String ret = response.body().string();
            jArr = new JSONObject(ret);
            if(jArr.getString("token")!=null)
                return "true";
            Log.d("Login response", ret);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "fail";
    }

    public static String signin(String name,String id, String password,String email, String phone) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("name",name);
        json.put("id",id);
        json.put("password",password);
        json.put("email",email);
        json.put("phone",phone);
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        okhttp3.RequestBody body = RequestBody.create(JSON, json.toString());
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrZXlfc2xpZGUiLCJVU0VSTkFNRSI6ImtleSIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.kxum_G7eqbL2BXmTr73tiLWzSxS4J6nISwBIArNPxd4")
                .url("https://key.apps.dev.clayon.io/signup")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            String ret = response.body().string();
            Log.d("Login response", ret);

        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    public static String getAdData() {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrZXlfc2xpZGUiLCJVU0VSTkFNRSI6ImtleSIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.kxum_G7eqbL2BXmTr73tiLWzSxS4J6nISwBIArNPxd4")
                .url("https://key.apps.dev.clayon.io/key/advert/lock")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            Log.d("getCalliList response", res);
            jArr = new JSONObject(res);
            if(jArr.getString("message").equals("SUCCESS"))
                return jArr.getJSONObject("data").getJSONObject("advert").getString("image");
            else return "fail";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
//        this.cardId = cardId;
//        this.cardName = cardName;
//        this.cardLimit = cardLimit;
//        this.cardType = cardType;
//        this.balance = balance;
//        this.imageUrl = imageUrl;
    public static ArrayList<CardDataItem> getCardData() {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrZXlfc2xpZGUiLCJVU0VSTkFNRSI6ImtleSIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.kxum_G7eqbL2BXmTr73tiLWzSxS4J6nISwBIArNPxd4")
                .url("https://key.apps.dev.clayon.io/key/card/inapp?size=3")
                .get()
                .build();
        ArrayList list = new ArrayList<CardDataItem>();
        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            Log.d("getCardData response", res);

            jArr = new JSONObject(res);
            if(jArr.getString("message").equals("SUCCESS")){
                JSONArray arr  = jArr.getJSONObject("data").getJSONArray("cards");
                for(int i=0;i<arr.length();i++) {
                    Log.d("http", "getCardData: "+arr.length());
                    JSONObject json = arr.getJSONObject(i);
                    list.add(new CardDataItem(
                            json.getInt("cardIdx"),
                            json.getString("cardname"),
                            json.getInt("expenditure"),
                            json.getBoolean("credit"),
                            json.getInt("expenditure"),
                            json.getString("cardimage")));
                }
                mlist=list;
                return list;
            }

            else return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public static ArrayList<PointStoreProductData> getPointProduct() {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrZXlfc2xpZGUiLCJVU0VSTkFNRSI6ImtleSIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.kxum_G7eqbL2BXmTr73tiLWzSxS4J6nISwBIArNPxd4")
                .url("https://key.apps.dev.clayon.io/key/point/products?size=3")
                .get()
                .build();
        ArrayList list = new ArrayList<PointStoreProductData>();
        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            Log.d("getpointData response", res);

            jArr = new JSONObject(res);
            if(jArr.getString("message").equals("SUCCESS")){
                JSONArray arr  = jArr.getJSONObject("data").getJSONArray("pointProduct");
                for(int i=0;i<arr.length();i++) {
                    JSONObject json = arr.getJSONObject(i);
                    list.add(new PointStoreProductData(json.getString("pointProductIdx"),json.getString("price"),
                            json.getString("image")));
                }
                return list;
            }

            else return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }
    public static ArrayList<Ad_For_Fill_PointData> getPointAd() {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrZXlfc2xpZGUiLCJVU0VSTkFNRSI6ImtleSIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.kxum_G7eqbL2BXmTr73tiLWzSxS4J6nISwBIArNPxd4")
                .url("https://key.apps.dev.clayon.io/key/advert/inapp/list?size=3")
                .get()
                .build();
        ArrayList list = new ArrayList<Ad_For_Fill_PointData>();
        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            Log.d("getpointData response", res);

            jArr = new JSONObject(res);
            if(jArr.getString("message").equals("SUCCESS")){
                JSONArray arr  = jArr.getJSONObject("data").getJSONArray("adverts");
                for(int i=0;i<arr.length();i++) {
                    JSONObject json = arr.getJSONObject(i);
                    list.add(new Ad_For_Fill_PointData(json.getString("inappAdvertIdx"),json.getString("point"),
                            json.getString("image")));
                }
                return list;
            }

            else return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }

    public static int getPoint() {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrZXlfc2xpZGUiLCJVU0VSTkFNRSI6ImtleSIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.kxum_G7eqbL2BXmTr73tiLWzSxS4J6nISwBIArNPxd4")
                .url("https://key.apps.dev.clayon.io/key/point")
                .get()
                .build();
        ArrayList list = new ArrayList<Ad_For_Fill_PointData>();
        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            Log.d("getpointData response", res);

            jArr = new JSONObject(res);
            if(jArr.getString("message").equals("SUCCESS")){
                return jArr.getJSONObject("data").getJSONObject("point").getInt("balance");
            }

            else return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static ArrayList<AccountHistoryItem> getCardHistory(int cardId) {
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJrZXlfc2xpZGUiLCJVU0VSTkFNRSI6ImtleSIsIlVTRVJfUk9MRSI6IlJPTEVfVVNFUiJ9.kxum_G7eqbL2BXmTr73tiLWzSxS4J6nISwBIArNPxd4")
                .url("https://key.apps.dev.clayon.io/key/card/histories/"+cardId+"?size=7")
                .get()
                .build();
        ArrayList list = new ArrayList<AccountHistoryItem>();
        try (Response response = client.newCall(request).execute()) {
            String res = response.body().string();
            Log.d("gethisData response", res);

            jArr = new JSONObject(res);
            if(jArr.getString("message").equals("SUCCESS")){
                JSONArray arr  = jArr.getJSONObject("data").getJSONArray("history");
                for(int i=0;i<arr.length();i++) {
                    JSONObject json = arr.getJSONObject(i);
                    list.add(new AccountHistoryItem(
                            json.getString("month")+"월 "+json.getString("day")+"일",
                            json.getString("traderName"),
                            json.getInt("difference"),
                            json.getInt("balance")
                            ));
                }
                return list;
            }

            else return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }
    }
}
