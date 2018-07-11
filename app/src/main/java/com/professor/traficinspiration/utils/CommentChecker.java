package com.professor.traficinspiration.utils;


import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class CommentChecker {

    public static void getCommentsFromMarket(final int pageNumber, final String uid, String marketName/*, final CallBack callBack*/) {
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://play.google.com/store/getreviews";
        RequestParams params = new RequestParams();

        try {
            client.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:8.0) Gecko/20100101 Firefox/8.0");
            client.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            client.addHeader("Accept-Language", "ru-ru,ru;q=0.8,en-us;q=0.5,en;q=0.3");
            client.addHeader("Accept-Encoding", "gzip, deflate");
            client.addHeader("Accept-Charset", "windows-1251,utf-8;q=0.7,*;q=0.7");
            client.addHeader("Connection", "keep-alive");
            client.addHeader("Referer", "https://play.google.com");
            params.put("reviewType", "0");
            params.put("pageNum", String.valueOf(pageNumber));
            params.put("id", marketName);
            params.put("reviewSortOrder", "0");
            params.put("xhr", "1");
            params.put("hl", "ru");

            client.post(url, params, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }

//                @Override
//                public void onSuccess(String result) {
//
//                    //  postDataToServer(pageNumber, uid, result, new CallBack());
//
//                    if (result.contains(uid)) {
//                        callBack.onSuccess(true);
//                    } else {
//                        callBack.onSuccess(false);
//                    }
//                }
//
//                public void onFailure(Throwable th) {
//
//                    callBack.onFail(th.getLocalizedMessage());
//                }
            });
        } catch (Exception e) {
//            callBack.onFail(e.getLocalizedMessage());
        }
    }
}
