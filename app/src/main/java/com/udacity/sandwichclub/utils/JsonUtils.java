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

            String mainName = nameArray.getString(JSON_MAIN_NAME_INDEX);

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