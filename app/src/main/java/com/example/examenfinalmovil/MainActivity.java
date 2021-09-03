package com.example.examenfinalmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.examenfinalmovil.Utilities.Utilities;
import com.example.examenfinalmovil.adapters.ListAdapterJ;
import com.example.examenfinalmovil.models.JournalModel;
import com.mindorks.placeholderview.PlaceHolderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String URL = "https://revistas.uteq.edu.ec/";
    private RequestQueue requestQueue;

    private List<JournalModel> listJournal;

    private PlaceHolderView placeHolderViewLIST;

    private SharedPreferences preferences;
    private String journal_id, portada, abbreviation, description, journalThumbnail, name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
      //  limpiarpreferents();
        placeHolderViewLIST = (PlaceHolderView) findViewById(R.id.rvListjorunalx);
        getdata();

    }

    private void getdata(){

        listJournal= new ArrayList<>();

        //Obtención de datos del web service utilzando Volley
        // requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,URL+"ws/journals.php",
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
                             //   json_transform = new JSONObject(response);
                               JSONArray jsonArray = new JSONArray(response.replace("?",""));
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    placeHolderViewLIST.addView(new ListAdapterJ(getApplicationContext(),
                                            new JournalModel(object.get("journal_id").toString(),
                                            object.get("portada").toString(),
                                            object.get("abbreviation").toString(),
                                            object.get("description").toString(),
                                            object.get("journalThumbnail").toString(),
                                            object.get("name").toString())));

                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString("journal_id",object.get("journal_id").toString());
                                    editor.putString("portada",object.get("portada").toString());
                                    editor.putString("abbreviation",object.get("abbreviation").toString());
                                    editor.putString("description",object.get("description").toString());
                                    editor.putString("journalThumbnail",object.get("journalThumbnail").toString());
                                    editor.putString("name",object.get("name").toString());
                                    editor.commit();

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

    private void limpiarpreferents() {
        preferences.edit().clear().apply();
        Toast.makeText(MainActivity.this, "Limpiando Preferents", Toast.LENGTH_LONG).show();
    }
}