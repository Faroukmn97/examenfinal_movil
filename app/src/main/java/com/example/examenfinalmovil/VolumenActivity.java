package com.example.examenfinalmovil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examenfinalmovil.Utilities.Utilities;
import com.example.examenfinalmovil.adapters.ListAdapterP;
import com.example.examenfinalmovil.models.PublicationsModels;
import com.google.gson.JsonArray;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VolumenActivity extends AppCompatActivity {

    private String URL = "https://revistas.uteq.edu.ec/";
    private RequestQueue requestQueue;

    private PlaceHolderView placeHolderViewPublications;

    private SharedPreferences preferences;
    private String journal_id, portada, abbreviation, description, journalThumbnail, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volumen);

        init();
        journals();

        placeHolderViewPublications = (PlaceHolderView) findViewById(R.id.rvListpublications);

        Bundle bundle = this.getIntent().getExtras();
        Log.d("journal_id",bundle.getString("journal_id"));

        getdatapublications(bundle.getString("journal_id"));


    }

    private void getdatapublications(String idjournal){

        //Obtención de datos del web service utilzando Volley
        // requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,URL+"ws/pubs.php?i_id="+idjournal,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int size = response.length();
                        response = Utilities.fixEncoding(response);
                        JSONObject json_transform = null;
                        if (size > 0)
                        {
                            Log.d("Response",response);
                            try {
                                JSONArray jsonArray = new JSONArray(response);
                                  for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                             //       Log.d("publication_id",object.get("publication_id").toString());
                                      JSONArray jsonArrayUsers = new JSONArray(object.get("authors").toString());
                                      JSONObject objusers = null;

                                      for (int j = 0; j < jsonArrayUsers.length();j++)
                                      {
                                          objusers = jsonArrayUsers.getJSONObject(j);
                                          try { objusers.put("Name", objusers.get("nombres").toString()); }
                                          catch (JSONException e){

                                          };
                                      }
                                   placeHolderViewPublications.addView(new ListAdapterP(getApplicationContext(),
                                            new PublicationsModels(object.get("section").toString(),
                                                    object.get("publication_id").toString(),
                                                    object.get("title").toString(),
                                                    object.get("doi").toString(),
                                                    object.get("abstract").toString(),
                                                    object.get("date_published").toString(),
                                                    object.get("submission_id").toString(),
                                                    object.get("section_id").toString(),
                                                    object.get("seq").toString(),
                                                    object.get("galeys").toString(),
                                                    object.get("keywords").toString(),
                                                    objusers.toString())));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("Content-Type", "application/json; charset=utf-8");
                params.put("Accept", "application/json");
                return params;
            }
        };
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(request);
        } else {
            requestQueue.add(request);
        }
    }

    private void init() {
        preferences = getSharedPreferences("Preferences", MODE_PRIVATE);
    }

    public void journals() {
        journal_id = preferences.getString("journal_id", null);
        portada = preferences.getString("portada", null);
        abbreviation = preferences.getString("abbreviation", null);
        description = preferences.getString("description", null);
        journalThumbnail = preferences.getString("journalThumbnail", null);
        name = preferences.getString("name", null);
    }


}