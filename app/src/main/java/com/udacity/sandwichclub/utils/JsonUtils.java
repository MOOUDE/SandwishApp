package com.udacity.sandwichclub.utils;
import android.content.Context;

import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sand;

        try {

            //Create JSON OBJ FROM STRING
            JSONObject sand_json = new JSONObject(json);

            //GET THE JSON ARRAY NAME
            JSONObject name = sand_json.getJSONObject("name");

            //GET THE INNER JSON OBJ FROM JSON OBJECT
          //  JSONObject main_name = name.getString("mainName");

            //GET THE MAIN NAME
            String MainName = name.getString("mainName");//*/

            //ARRAY LIST TO STORE ARRAY VALUES FROM OBJ
            ArrayList known_as=new ArrayList();

            JSONArray also = name.getJSONArray("alsoKnownAs");
            for(int i=0 ; i<also.length() ; i++){
                known_as.add(also.getString(i));
            }


          // GET THE VALUE PLACE
            String place_str = sand_json.getString("placeOfOrigin");


            // GET THE VALUE DESCRIPTION
            String description = sand_json.getString("description");

            // GET THE VALUE IMAGE
            String image = sand_json.getString("image");


            //GET THE ARRAY OBJ ingredients
            JSONArray ingre = sand_json.getJSONArray("ingredients");

            ArrayList ingres =new ArrayList(); /**/

            for(int i=0 ; i<ingre.length() ; i++){
                ingres.add(ingre.getString(i));
            }


            //create sandwich object
             sand = new Sandwich(MainName , known_as ,place_str, description ,image, ingres);

            return sand;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
