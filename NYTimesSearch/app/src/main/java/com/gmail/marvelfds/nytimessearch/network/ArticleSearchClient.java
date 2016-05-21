package com.gmail.marvelfds.nytimessearch.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gmail.marvelfds.nytimessearch.adapters.ArticleArrayAdapter;
import com.gmail.marvelfds.nytimessearch.models.Article;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Core i7 on 5/19/2016.
 */

public class ArticleSearchClient {
    public static final String AP_KEY="a61facb60c0a43bf9f04e3dd4ddd9e92";
    public static final String urlSite="https://api.nytimes.com/svc/search/v2/articlesearch.json";
    public static String beginDate;
    public static String newsDeskValue;
    public static String sortOrder;
    public static int page = 0;
    JSONArray articleResultsJSON;

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }

    public static void searchClient(final Context context,String query, final ArticleArrayAdapter aArticles){
        // verify the internet connection
     if(isOnline()) {
         AsyncHttpClient client = new AsyncHttpClient();
         String url = urlSite;
         RequestParams params = new RequestParams();
         params.put("api-key", AP_KEY);
         params.put("page", page);
       params.put("q", query);
         params.put("fq",newsDeskValue);
         params.put("begin_date",beginDate);

         // Send an API request to retrieve appropriate data using the offset value as a parameter.
         client.get(url, params, new JsonHttpResponseHandler() {
             JSONArray articleResultsJSON;

             @Override
             public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                 super.onSuccess(statusCode, headers, response);
                 Log.i("DEBUG", response.toString());
                 try {
                     // Deserialize API response and then construct new objects to append to the adapter
                     articleResultsJSON = response.getJSONObject("response").getJSONArray("docs");
                     aArticles.clear();
                     // Add the new objects to the data source for the adapter
                     aArticles.addAll(Article.fromJSONArray(articleResultsJSON));
                     aArticles.notifyDataSetChanged();

                 } catch (JSONException ex) {
                     ex.printStackTrace();
                 }

             }

             @Override
             public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                 Toast.makeText(context, "your request http is failed", Toast.LENGTH_LONG).show();

             }
         });
     }
     else{
         Toast.makeText(context, "internet connection failed", Toast.LENGTH_LONG).show();
     }
    }

    public static void searchClient(final Context context,String query, final ArticleArrayAdapter aArticles,int page){
        // verify the internet connection
        if(isOnline()) {
            AsyncHttpClient client = new AsyncHttpClient();
            String url = urlSite;
            RequestParams params = new RequestParams();
            params.put("api-key", AP_KEY);
            params.put("page", page);
            params.put("q", query);

            // Send an API request to retrieve appropriate data using the offset value as a parameter.
            client.get(url, params, new JsonHttpResponseHandler() {
                JSONArray articleResultsJSON;

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    super.onSuccess(statusCode, headers, response);
                    Log.i("DEBUG", response.toString());
                    try {
                        // Deserialize API response and then construct new objects to append to the adapter
                        articleResultsJSON = response.getJSONObject("response").getJSONArray("docs");
                        aArticles.clear();
                        // Add the new objects to the data source for the adapter
                        aArticles.addAll(Article.fromJSONArray(articleResultsJSON));
                        aArticles.notifyDataSetChanged();

                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Toast.makeText(context, "your request http is failed", Toast.LENGTH_LONG).show();

                }
            });
        }
        else{
            Toast.makeText(context, "internet connection failed", Toast.LENGTH_LONG).show();
        }
    }

}
