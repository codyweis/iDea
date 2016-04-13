package apps.luck3y.com.idea;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Cody Weisenberger on 2/17/2016.
 */


public class Profile extends AppCompatActivity implements View.OnClickListener{

    Button btnLgout;
    Button btnProfile;
    Button btnPost;

    TextView acnt;
    TextView inbx;
    TextView snt;
    TextView pst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        acnt = (TextView) findViewById(R.id.txtViewAcnt);
        inbx = (TextView) findViewById(R.id.txtViewInbx);
        snt = (TextView) findViewById(R.id.txtViewSnt);
        pst = (TextView) findViewById(R.id.txtViewPsts);

        btnLgout = (Button) findViewById(R.id.btnlgout);
        btnProfile = (Button) findViewById(R.id.btnViewPostsProfile);
        btnPost = (Button) findViewById(R.id.btnNewPostProfile);

        btnLgout.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        acnt.setOnClickListener(this);
        pst.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnlgout:
                logUserOut();
                break;
            case R.id.txtViewAcnt:
                startActivity(new Intent(this, Account.class));
                break;
            case R.id.txtViewInbx:
                viewInbox();
                break;
            case R.id.txtViewSnt:
                viewSent();
                break;
            case R.id.txtViewPsts:
                startActivity(new Intent(this, IndPosts.class));
                break;
            case R.id.btnViewPostsProfile:
                startActivity(new Intent(this, DisplayPosts.class));
                break;
            case R.id.btnNewPostProfile:
                startActivity(new Intent(this, Home.class));
                break;
        }
    }

    private void viewInbox() {
        // TODO: 2/18/2016
    }

    private void viewSent() {
        // TODO: 2/18/2016
    }

    private void logUserOut(){
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Ya!",
                //if yes
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //removing from shared pref
                        SharedPreferences preferences = getSharedPreferences(Config.sharedPref, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        //change to false
                        editor.putBoolean(Config.sharedPrefBool, false);
                        //set username to empty string
                        editor.putString(Config.username, "");
                        editor.commit();
                        Intent intent = new Intent(Profile.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        //no, option do nothing
        alertDialogBuilder.setNegativeButton("Nah",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}