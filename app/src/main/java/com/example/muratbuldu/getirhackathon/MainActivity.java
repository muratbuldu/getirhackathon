package com.example.muratbuldu.getirhackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawView drawView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://getir-bitaksi-hackathon.herokuapp.com/getElements";
        final ArrayList<Rectangle> rectArrayList = new ArrayList<>();
        final ArrayList<Circle> circleArrayList = new ArrayList<>();

        final JSONObject obj = new JSONObject();
        try {
            obj.put("email", "katilimciEmail");
            obj.put("name", "katilimciIsmi");
            obj.put("gsm", "katilimciGsm");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.POST, url, obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray elements = null;
                        try {
                            elements = response.getJSONArray("elements");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0; i < elements.length(); i++) {
                            JSONObject currentElement = null;
                            try {
                                currentElement = elements.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String type = "";
                            try {
                                type = currentElement.getString("type");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            if (type.equals("rectangle")) {
                                int xPosition = 0;
                                int yPosition = 0;
                                int width = 0;
                                int height = 0;
                                String color = "";
                                try {
                                    xPosition = currentElement.getInt("xPosition");
                                    yPosition = currentElement.getInt("yPosition");
                                    width = currentElement.getInt("width");
                                    height = currentElement.getInt("height");
                                    color = currentElement.getString("color");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                rectArrayList.add(new Rectangle(xPosition,
                                        yPosition,
                                        width,
                                        height,
                                        color));

                            } else {
                                int xPosition = 0;
                                int yPosition = 0;
                                int radius = 0;
                                String color = "";
                                try {
                                    xPosition = currentElement.getInt("xPosition");
                                    yPosition = currentElement.getInt("yPosition");
                                    radius = currentElement.getInt("r");
                                    color = currentElement.getString("color");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                circleArrayList.add(new Circle(xPosition,
                                        yPosition,
                                        radius,
                                        color));
                            }
                        }
                        drawView.setRectangleArrayList(rectArrayList);
                        drawView.setCircleArrayList(circleArrayList);
                        setContentView(drawView);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(jsObjRequest);

        drawView = new DrawView(this,rectArrayList,circleArrayList);



    }
}
