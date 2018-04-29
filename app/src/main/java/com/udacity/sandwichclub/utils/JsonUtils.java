package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();

    private static final String JSON_NAME_ARRAY_KEY = "name";
    private static final String JSON_AKA_KEY = "alsoKnownAs";
    private static final String JSON_ORIGIN_KEY = "placeOfOrigin";
    private static final String JSON_DESCRIPTION_KEY = "description";
    private static final String JSON_IMAGE_KEY = "image";
    private static final String JSON_INGREDIENTS_KEY = "ingredients";

    private static final int JSON_MAIN_NAME_INDEX = 0;
    private static final int JSON_AKA_INDEX = 1;

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONArray nameArray = sandwichJson.getJSONArray(JSON_NAME_ARRAY_KEY);
            JSONArray akaArray = nameArray.getJSONArray(JSON_AKA_INDEX);
            JSONArray ingredientsArray = sandwichJson.getJSONArray(JSON_INGREDIENTS_KEY);

            List<String> alsoKnownAs = new ArrayList<>();

            String mainName = nameArray.getString(JSON_MAIN_NAME_INDEX);

            //Else condition to handle empty akaArray case and still display something to user.
            if (akaArray != null && akaArray.length() > 0){
                for (int i = 0; i < akaArray.length(); i++){
                    alsoKnownAs.add(akaArray.getString(i));
                }
            }else{
                alsoKnownAs.add("No alternate names");
            }

            //Conditional assignment to handle empty origin with "Unknown" default.
            String originString = sandwichJson.getString(JSON_ORIGIN_KEY);
            String placeOfOrigin = (originString != null && !originString.isEmpty() ? originString : "Unknown");

            String description =




            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, )

        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
        }

        return null;
    }
}

//Property references while working through JSON parsing.
//    private String mainName;
//    private List<String> alsoKnownAs = null;
//    private String placeOfOrigin;
//    private String description;
//    private String image;
//    private List<String> ingredients = null;