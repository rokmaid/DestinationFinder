package repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.FlightData;
import model.Token;

public class AppRepository {


    private static final String TOKEN_URL ="https://api-crt.cert.havail.sabre.com/v2/auth/token";

    private static final String AUTHORIZATION ="Basic VmpFNk9UazVPVHBHT1ZWRk9rRkI6TURkU1MwWkNNalk9";

    private static  String FLIGTS_URL ="https://api.test.sabre.com/v1/lists/top/destinations";

    private static  AppRepository ourInstance ;

    private RequestQueue mRequestqueue ;

    private MutableLiveData<Token> token  ;

    private MutableLiveData<FlightData> flight_data ;


    public static AppRepository getInstance(Context context) {

        if(ourInstance==null){
            ourInstance = new AppRepository( context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {

        mRequestqueue = Volley.newRequestQueue(context);
        token = new MutableLiveData<>() ;

      flight_data=new MutableLiveData<>();

    }



    public void getTokenData(){

 final  StringRequest stringRequest = new StringRequest(Request.Method.POST, TOKEN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Gson gson = new Gson();

                        // build our Token object based on the String response

                        Token response_data=  gson.fromJson(response,Token.class);

                        token.setValue(response_data);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  serverResp.setText("Error getting response");


                error.printStackTrace();
            }

        }

){
     @Override
     public Map<String, String> getHeaders() throws AuthFailureError {
         HashMap headers = new HashMap();
         headers.put("Authorization",AUTHORIZATION);
         headers.put("Content-Type","application/x-www-form-urlencoded");
         headers.put("grant_type","client_credentials");

         return headers ;
     }
 };



        mRequestqueue.add(stringRequest);


    }

    public MutableLiveData<Token> getToken() {
        return token;
    }



    public void getFlightData(final String origin, final String country_code , final String theme  ){


        final  StringRequest stringRequest = new StringRequest(Request.Method.POST, TOKEN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Gson gson = new Gson();

                        // build our Token object based on the String response

                        Token response_data=  gson.fromJson(response,Token.class);

                        // if the response is sucesfull call the destinations API with our access token

                        getFlightData(origin,response_data.getAccess_token(),country_code,theme);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  serverResp.setText("Error getting response");


                error.printStackTrace();
            }

        }

        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization",AUTHORIZATION);
                headers.put("Content-Type","application/x-www-form-urlencoded");
                headers.put("grant_type","client_credentials");

                return headers ;
            }
        };



        mRequestqueue.add(stringRequest);



    }

    private void getFlightData(String origin, final String token ,String country_code ,String theme ){

        String url =FLIGTS_URL+="?origin="+origin+"&location="+country_code;

        if(theme!=null){
            url+="&theme="+theme ;


        }


        final  StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        Gson gson = new Gson();

                        FlightData data = gson.fromJson(response,FlightData.class);

                      //  flight_data_list.setValue(list);

                        flight_data.setValue(data);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  serverResp.setText("Error getting response");


                error.printStackTrace();
            }

        }

        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Authorization","Bearer "+token);

                return headers ;
            }
        };



        mRequestqueue.add(stringRequest);




    }


    public MutableLiveData<FlightData> getFlight_data() {
        return flight_data;
    }


}
