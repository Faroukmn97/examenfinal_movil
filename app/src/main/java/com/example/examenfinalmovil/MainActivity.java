package com.example.examenfinalmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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

    private RecyclerView rvListJournal;
    
    private List<JournalModel> listJournal;

    private PlaceHolderView placeHolderViewLIST;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                                    Log.d("journal_id",object.get("journal_id").toString());
                                    //  Log.d("name",object.get("user_id").toString());

                                    placeHolderViewLIST.addView(new ListAdapterJ(getApplicationContext(),
                                            new JournalModel(object.get("journal_id").toString(),
                                            object.get("portada").toString(),
                                            object.get("abbreviation").toString(),
                                            object.get("description").toString(),
                                            object.get("journalThumbnail").toString(),
                                            object.get("name").toString())));




                               //    JournalModel journalModel = new JournalModel();
                                  // journalModel.se
                                  //
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
}