package apps.luck3y.com.idea;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cody Weisenberger on 2/5/2016.
 */
public class Login extends AppCompatActivity implements View.OnClickListener{

    ProgressBar loadingPan;
    Button btnAcpt, btnCrtAcnt2;
    EditText txtUsrnm, txtPswrd;
    //check if user logged in initialized to false
    boolean isLoggedIn = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsrnm = (EditText) findViewById(R.id.txtUsrnm);
        txtPswrd = (EditText) findViewById(R.id.txtPswrd);
        btnAcpt = (Button) findViewById(R.id.btnAcpt);
        btnCrtAcnt2 = (Button) findViewById(R.id.btnCrtAcnt2);
        loadingPan = (ProgressBar) findViewById(R.id.loadingPanel);

        btnAcpt.setOnClickListener(this);
        btnCrtAcnt2.setOnClickListener(this);
        loadingPan.setVisibility(View.GONE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        //get from shared pref
        SharedPreferences sharedPreferences = getSharedPreferences(Config.sharedPref, Context.MODE_PRIVATE);

        //get boolean from sharedpref
        isLoggedIn = sharedPreferences.getBoolean(Config.sharedPrefBool, false);

        // if logged in
        if(isLoggedIn){
            //start new activity
            Intent intent = new Intent(Login.this, Profile.class);
            startActivity(intent);
        }
    }

    private void login(){
        final String username = txtUsrnm.getText().toString().trim();
        final String password = txtPswrd.getText().toString().trim();
        //create string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SERVER_ADDRESS + "Login.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String responseOne = response.substring(0,9);
                        String responseTwo = response.substring(9);
                        if(responseOne.trim().equalsIgnoreCase(Config.logInMessage)){
                            //create shared pref
                            SharedPreferences sharedPreferences = Login.this.getSharedPreferences(Config.sharedPref, Context.MODE_PRIVATE);
                            //editor stores values to the shared pref
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            //add values
                            editor.putBoolean(Config.sharedPrefBool, true);
                            editor.putString(Config.username, username);
                            editor.putString(Config.password, password);
                            editor.putString(Config.SID, responseTwo);
                            editor.commit();

                            Intent intent = new Intent(Login.this, Home.class);
                            startActivity(intent);
                        }else{
                            //display error message
                            Toast.makeText(Login.this, "Wrong Username or Password", Toast.LENGTH_LONG).show();
                            loadingPan.setVisibility(View.GONE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //from android.com: A Map is a data structure consisting of a set of keys and
                // values in which each key is mapped to a single value. The class of the objects
                // used as keys is declared when the Map is declared, as is the class of the
                // corresponding values.
                Map<String,String> hashMap = new HashMap<>();

                //maps specified string key, username and password, to specified string value
                hashMap.put(Config.username, username);
                hashMap.put(Config.password, password);

                return hashMap;
            }
        };

        //add string request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnAcpt:
                loadingPan.setVisibility(View.VISIBLE);
                login();
                break;
            case R.id.btnCrtAcnt2:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
