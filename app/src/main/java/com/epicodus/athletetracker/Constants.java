package com.epicodus.athletetracker;


public class Constants {

        public static final String WORKOUT_KEY = BuildConfig.WORKOUT_KEY;
        public static final String WORKOUT_BASE_URL = "https://wger.de/api/v2/exercise/?language=2";

        public static final String NEWS_KEY = BuildConfig.NEWS_KEY;
        public static final String NEWS_BASE_URL = "https://newsapi.org/v1/articles?source=new-scientist";
        public static final String NEWS_KEY_QUERY = "apiKey";

        public static final String FIREBASE_CHILD_WORKOUTS_SAVED = "workout";
        public static final String FIREBASE_QUERY_INDEX = "index";

        public static final String EXTRA_KEY_POSITION = "position";
        public static final String EXTRA_KEY_WORKOUT = "workout";
}

