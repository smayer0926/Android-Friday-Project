package com.epicodus.athletetracker.Services;

import android.content.Intent;
import android.net.Uri;

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

import static android.R.attr.description;
import static android.R.attr.start;

public class WorkoutService {
    public static void findWorkout(Callback callback){
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

        try{

            String jsonData = response.body().string();
            JSONObject workoutJSON  = new JSONObject(jsonData);
            JSONArray workoutInformationJSON = workoutJSON.getJSONArray("results");

                for(int i = 2; i< workoutInformationJSON.length(); i++){
                    JSONObject newWorkoutJSON = workoutInformationJSON.getJSONObject(i);
                    if(newWorkoutJSON.getString("license_author").equals("Magenta") || newWorkoutJSON.getString("license_author").equals("admintest123")){
                        continue;
                    }
                    String name = newWorkoutJSON.getString("name");
                    String description = newWorkoutJSON.getString("description");


                    Workout workout = new Workout(name, description);
                    workoutsReady.add(workout);

                }


        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
        System.out.println("here" + workoutsReady);
        return workoutsReady;
    }
}


