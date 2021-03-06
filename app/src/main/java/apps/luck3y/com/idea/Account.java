package apps.luck3y.com.idea;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.CookieStore;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cody Weisenberger on 2/17/2016.
 */
public class Account extends AppCompatActivity implements View.OnClickListener {

    Button btnBack;

    TextView getFname;
    TextView getLname;
    TextView getUsername;
    TextView getEmail;


    ArrayList<String> userInfo;
    JSONArray result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        getFname = (TextView) findViewById(R.id.getFname);
        getLname = (TextView) findViewById(R.id.getLname);
        getUsername = (TextView) findViewById(R.id.getUsername);
        getEmail = (TextView) findViewById(R.id.getEmail);

        userInfo = new ArrayList<String>();

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        getUserData();

    }

//    private void logUserOut(){
//        //Creating an alert dialog to confirm logout
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
//        alertDialogBuilder.setMessage("Are you sure you want to logout?");
//        alertDialogBuilder.setPositiveButton("Ya!",
//                //if yes
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                        //removing from shared pref
//                        SharedPreferences preferences = getSharedPreferences(Config.sharedPref, Context.MODE_PRIVATE);
//                        SharedPreferences.Editor editor = preferences.edit();
//                        //change to false
//                        editor.putBoolean(Config.sharedPrefBool, false);
//                        //set username to empty string
//                        editor.putString(Config.username, "");
//                        editor.commit();
//                        Intent intent = new Intent(Account.this, MainActivity.class);
//                        startActivity(intent);
//                    }
//                });
//        //no, option do nothing
//        alertDialogBuilder.setNegativeButton("Nah",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface arg0, int arg1) {
//
//                    }
//                });
//        AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.show();
//    }


    private void getUserData() {

        SharedPreferences sharedPreferences = getSharedPreferences(Config.sharedPref, Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString(Config.SID, "SessionID");

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.SERVER_ADDRESS + "GetUserData.php?PHPSESSID=" + sessionId,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {

                            //json string to jsonobject
                            jsonObject = new JSONObject(response);


                            //get json sstring created in php and store to JSON Array
                            result = jsonObject.getJSONArray(Config.json_array_user);

                            //get username from json array
                            getUserInfo(result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getUserInfo(JSONArray jsonArray){
            for(int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject json = jsonArray.getJSONObject(i);

                userInfo.add(json.getString(Config.getUsername));
                userInfo.add(json.getString(Config.getfname));
                userInfo.add(json.getString(Config.getlname));
                userInfo.add(json.getString(Config.getemail));

            } catch (JSONException e) {

            }
        }

        for(int i = 0; i < userInfo.size(); i++){
            switch(i){
                case 0: getUsername.setText("Username: " + userInfo.get(i));
                    break;
                case 1: getFname.setText("First: " + userInfo.get(i));
                    break;
                case 2: getLname.setText("Last: " + userInfo.get(i));
                    break;
                case 3: getEmail.setText("Email: " + userInfo.get(i));
                    break;
            }
        }
    }



    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnBack:
                startActivity(new Intent(this, Profile.class));
                break;
        }
    }

}
