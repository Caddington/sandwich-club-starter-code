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

    private static final String JSON_NAME_KEY = "name";
    private static final String JSON_MAIN_NAME_KEY = "mainName";
    private static final String JSON_AKA_KEY = "alsoKnownAs";
    private static final String JSON_ORIGIN_KEY = "placeOfOrigin";
    private static final String JSON_DESCRIPTION_KEY = "description";
    private static final String JSON_IMAGE_KEY = "image";
    private static final String JSON_INGREDIENTS_KEY = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject sandwichJson = new JSONObject(json);
            JSONObject nameObject = sandwichJson.getJSONObject(JSON_NAME_KEY);
            JSONArray akaArray = nameObject.getJSONArray(JSON_AKA_KEY);
            JSONArray ingredientsArray = sandwichJson.getJSONArray(JSON_INGREDIENTS_KEY);

            String mainName = nameObject.getString(JSON_MAIN_NAME_KEY);

            List<String> alsoKnownAs = new ArrayList<>();
            //Else condition to handle empty akaArray case and display useful default.
            if (akaArray != null && akaArray.length() > 0){
                for (int i = 0; i < akaArray.length(); i++){
                    alsoKnownAs.add(akaArray.getString(i));
                }
            }else{
                alsoKnownAs.add("No alternate names");
            }

            //Conditional assignment to handle empty origin with useful default.
            String originString = sandwichJson.getString(JSON_ORIGIN_KEY);
            String placeOfOrigin = (originString != null && !originString.isEmpty() ? originString : "Unknown");

            //Conditional assignment to handle empty description with useful default.
            String descriptionString = sandwichJson.getString(JSON_DESCRIPTION_KEY);
            String description = (descriptionString != null && !descriptionString.isEmpty() ? descriptionString : "Unavailable");

            String imageString = sandwichJson.getString(JSON_IMAGE_KEY);

            List<String> ingredients = new ArrayList<>();
            //Else condition to handle empty ingredients case and display useful default.
            if (ingredientsArray != null && ingredientsArray.length() > 0){
                for (int i = 0; i < ingredientsArray.length(); i++){
                    ingredients.add(akaArray.getString(i));
                }
            }else{
                ingredients.add("Unavailable");
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, imageString, ingredients);

        } catch (JSONException e) {
            Log.e(TAG, "Error parsing JSON: " + e.getMessage());
            return null;
        }
    }
}