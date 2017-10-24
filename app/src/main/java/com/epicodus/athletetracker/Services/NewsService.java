package com.epicodus.athletetracker.Services;


import com.epicodus.athletetracker.Constants;
import com.epicodus.athletetracker.Models.News;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsService {

    public static void findNews(Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NEWS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.NEWS_KEY_QUERY, Constants.NEWS_KEY);
        String url = urlBuilder.build().toString();

        okhttp3.Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<News> processResults(Response response){
        ArrayList<News> news = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            JSONObject newsJSON = new JSONObject(jsonData);
            JSONArray storiesJSON = newsJSON.getJSONArray("articles");
            for(int i = 0; i < storiesJSON.length(); i++){
                JSONObject newStory = storiesJSON.getJSONObject(i);
                String author = newStory.getString("author");
                String title = newStory.getString("title");
                String image = newStory.getString("urlToImage");
                String url = newStory.getString("url");

                News story = new News(title, author, image, url);
                news.add(story);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        System.out.println(news);
        return news;
    }
}
