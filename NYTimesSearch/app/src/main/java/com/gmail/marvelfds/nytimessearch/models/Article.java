package com.gmail.marvelfds.nytimessearch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Core i7 on 5/16/2016.
 */
public class Article implements Serializable {
    // 1 define fields
    // 2 Deserialize json into object fields
    // 3 Decodes business json into business model object
    // 4 implement all the getter setter methods

    //define fields
    private String webUrl;
    private String headline;
    private String thumbNail;

    public Article(JSONObject jsonObject) {

        // Deserialize json into object fields
        try {

            this.webUrl = jsonObject.getString("web_url");
            this.headline = jsonObject.getJSONObject("headline").getString("main");
            JSONArray multimedia = jsonObject.getJSONArray("multimedia");
            if (multimedia.length() > 0) {
                JSONObject multimediaJson = (JSONObject) multimedia.get(0);
                this.thumbNail = "https://www.nytimes.com/" + multimediaJson.getString("url");
            } else {
                this.thumbNail = "";
            }


        } catch (JSONException e) {
            e.printStackTrace();

        }


    }
    // Decodes business json into business model object
    public static ArrayList<Article> fromJSONArray(JSONArray array) {

        ArrayList<Article> results = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Article((JSONObject)array.get(i)));
            }

            catch (JSONException ex) {
                ex.printStackTrace();
            }


        }
        return  results;
    }
 //implement all the getter setter methods


    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headLine) {
        this.headline = headLine;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(String thumbNail) {
        this.thumbNail = thumbNail;
    }
}
