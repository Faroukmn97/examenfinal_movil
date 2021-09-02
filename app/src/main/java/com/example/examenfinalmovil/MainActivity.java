package com.example.examenfinalmovil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.examenfinalmovil.adapters.ListAdapterJournal;
import com.example.examenfinalmovil.models.JournalModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String URL = "https://revistas.uteq.edu.ec/";
    private RequestQueue requestQueue;

    private RecyclerView rvListJournal;

    private ListAdapterJournal adapterJournal;
    private List<JournalModel> listJournal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addPharm();

    }

    private void addPharm(){

        rvListJournal = findViewById(R.id.rvListjorunal);
        rvListJournal.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        rvListJournal.setHasFixedSize(true);

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