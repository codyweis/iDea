package apps.luck3y.com.idea;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cody Weisenberger on 2/5/2016.
 */
public class Register extends AppCompatActivity implements View.OnClickListener{

    Button btnRegstr;
    EditText txtRegUsrnm, txtRegFrst, txtRegLst, txtRegPswrd, txtRegPswrdCnfrm, txtRegEmail;
    ProgressBar load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtRegEmail = (EditText) findViewById(R.id.txtRegEmail);
        txtRegFrst = (EditText) findViewById(R.id.txtRegFrst);
        txtRegLst = (EditText) findViewById(R.id.txtRegLst);
        txtRegPswrd = (EditText) findViewById(R.id.txtRegPswrd);
        txtRegUsrnm = (EditText) findViewById(R.id.txtRegUsrnm);
        btnRegstr = (Button) findViewById(R.id.btnRegstr);
        load = (ProgressBar) findViewById(R.id.progressBarReg);

        btnRegstr.setOnClickListener(this);
        load.setVisibility(View.GONE);
    }

    private void register() {
        final String fname;
        final String lname;
        final String username;
        final String password;
        final String email;

        if (txtRegFrst.getText().toString().trim().isEmpty() || txtRegLst.getText().toString().trim().isEmpty() || txtRegUsrnm.getText().toString().trim().isEmpty() || txtRegPswrd.getText().toString().trim().isEmpty() || txtRegEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(Register.this, "Fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            fname = txtRegFrst.getText().toString().trim();
            lname = txtRegLst.getText().toString().trim();
            username = txtRegUsrnm.getText().toString().trim();
            password = txtRegPswrd.getText().toString().trim();
            email = txtRegEmail.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SERVER_ADDRESS + "Register.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.trim().equalsIgnoreCase(Config.registered)) {
                                Toast.makeText(Register.this, "successful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                            } else if (response.trim().equalsIgnoreCase(Config.usererror)) {
                                Toast.makeText(Register.this, "Username already registered", Toast.LENGTH_LONG).show();
                                load.setVisibility(View.GONE);

                            } else if (response.trim().equalsIgnoreCase(Config.emailerror)) {
                                Toast.makeText(Register.this, "Email already registered", Toast.LENGTH_LONG).show();
                                load.setVisibility(View.GONE);
                            } else {
                                Toast.makeText(Register.this, "error registering, contact developer", Toast.LENGTH_LONG).show();
                                load.setVisibility(View.GONE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Register.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(Config.fname, fname);
                    params.put(Config.lname, lname);
                    params.put(Config.username, username);
                    params.put(Config.password, password);
                    params.put(Config.email, email);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        load.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegstr:
                load.setVisibility(View.VISIBLE);
                register();
        }
    }
}
