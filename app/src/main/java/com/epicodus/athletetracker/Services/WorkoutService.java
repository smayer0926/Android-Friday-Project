package com.epicodus.athletetracker.Services;





import com.epicodus.athletetracker.Constants;
import com.epicodus.athletetracker.Models.Workout;

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

public class WorkoutService {
    public static void findWorkout(Callback callback){
        System.out.println("in findworkout");
        OkHttpClient client = new OkHttpClient.Builder().build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.WORKOUT_BASE_URL).newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", Constants.WORKOUT_KEY)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Workout> processResults(Response response){
        ArrayList<Workout> workoutsReady = new ArrayList<>();
        System.out.println("in process results");

        try{
            String jsonData = response.body().string();

            if(response.isSuccessful()){
                System.out.println("in success");
                JSONObject workoutJSON  = new JSONObject(jsonData);
                System.out.println(workoutJSON);
                JSONArray workoutInformationJSON = workoutJSON.getJSONArray("results");
                System.out.println(workoutInformationJSON);
                for(int i = 0; i< workoutInformationJSON.length(); i++){
                    JSONObject movieStuffJSON = workoutInformationJSON.getJSONObject(i);
                    String comment = movieStuffJSON.getString("comment");
                    String creation = movieStuffJSON.getString("creation_date");
                    System.out.println(comment);

                    Workout workouts = new Workout(comment, creation);
                    workoutsReady.add(workouts);
                    System.out.println(workouts);
                }

            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        return workoutsReady;
    }
}


