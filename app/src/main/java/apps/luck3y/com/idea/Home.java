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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cody Weisenberger on 2/8/2016.
 */
public class Home extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener{

    Button btnLgout;
    Button btnPrfile;
    Button btnSbmitPst;
    String btnTxt = "Like";

    ProgressBar load;

    EditText ideaPost;

    Spinner spinner;
    ArrayList<String> topics;
    JSONArray result;
    ArrayList<String> postData = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ideaPost = (EditText) findViewById(R.id.ideaPost);
        btnSbmitPst = (Button) findViewById(R.id.btnSbmtPst);
        btnLgout = (Button) findViewById(R.id.btnLgout);
        btnPrfile = (Button) findViewById(R.id.btnProfile);
        spinner = (Spinner) findViewById(R.id.topic_dropdown);

        load = (ProgressBar) findViewById(R.id.progBarHome);

        load.setVisibility(View.GONE);

        topics = new ArrayList<String>();

        btnSbmitPst.setOnClickListener(this);
        btnLgout.setOnClickListener(this);
        btnPrfile.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);

        getData();

    }

    private void getData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.SERVER_ADDRESS + "GetTopics.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            //json string to jsonobject
                            jsonObject = new JSONObject(response);

                            //get json sstring created in php and store to JSON Array
                            result = jsonObject.getJSONArray(Config.json_array);

                            //get topics from json array
                            getTopics(result);
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

    private void getTopics(JSONArray jsonArray){
        //go through items in array
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                //get json object
                JSONObject json = jsonArray.getJSONObject(i);

                //add topic to arraylist
                topics.add(json.getString(Config.topic_title));
            } catch (JSONException e) {

            }
        }
            //set adapter to show items in the dropdown
            spinner.setAdapter(new ArrayAdapter<String>(Home.this, android.R.layout.simple_selectable_list_item, topics));
    }

    //topic name of position
    private String getTopicTitle(int position){
        String title="";
        try {
            //get object of index
            JSONObject json = result.getJSONObject(position);

            //get title from object
            title = json.getString(Config.topic_title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return title;
    }

    //execute when item selected from dropdown
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //// TODO: 2/17/2016

    }

    //no item selected
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //// TODO: 2/17/2016
    }

    private void insertPost() {
        final String postText = ideaPost.getText().toString().trim();

        if (!postText.isEmpty()) {

            //gets topic title from drop down and puts in string
            final String topicTitle = spinner.getSelectedItem().toString();

            SharedPreferences sharedPreferences = getSharedPreferences(Config.sharedPref, Context.MODE_PRIVATE);
            final String username = sharedPreferences.getString(Config.username, "username");
            //String sessionId = sharedPreferences.getString(Config.SID, "SessionID");

            //create string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SERVER_ADDRESS + "CreatePost.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Home.this, "Posted", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(Home.this, DisplayPosts.class);
                            startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Home.this, error.toString(), Toast.LENGTH_LONG).show();
                            load.setVisibility(View.GONE);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    //from android.com: A Map is a data structure consisting of a set of keys and
                    // values in which each key is mapped to a single value. The class of the objects
                    // used as keys is declared when the Map is declared, as is the class of the
                    // corresponding values.
                    Map<String, String> hashMap = new HashMap<>();

                    //maps specified string key, username and password, to specified string value
                    hashMap.put(Config.topic, topicTitle);
                    hashMap.put(Config.username, username);
                    hashMap.put(Config.content, postText);

                    return hashMap;
                }
            };

            //add string request to queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(Home.this, "Please enter something.", Toast.LENGTH_SHORT).show();
        }
    }
    private void getSmallData(){

        //create string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.SERVER_ADDRESS + "GetPostData.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {

                            //json string to jsonobject
                            jsonObject = new JSONObject(response);


                            //get json sstring created in php and store to JSON Array
                            result = jsonObject.getJSONArray(Config.json_array_data);

                            //get id and user from json array
                            getArrayData(result);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        load.setVisibility(View.GONE);

                    }

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void getArrayData(JSONArray jsonArray){
        for(int i = 0; i < 1; i++) {
            try {
                JSONObject json = jsonArray.getJSONObject(i);

                System.out.println("HERE"+postData);
                postData.add(json.getString(Config.user_data_post));
                postData.add(json.getString(Config.id_data));

            } catch (JSONException e) {

            }
        }
        SharedPreferences sharedPreferences = Home.this.getSharedPreferences(Config.sharedPref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //add values
        editor.putString(Config.storePostId, postData.get(1));
        editor.putString(Config.storePostUser, postData.get(0));
        editor.commit();
    }

    private void addData(){

        SharedPreferences sharedPreferences = getSharedPreferences(Config.sharedPref, Context.MODE_PRIVATE);
        final String user = sharedPreferences.getString(Config.storePostUser, "user");
        final String id = sharedPreferences.getString(Config.storePostId, "post_id");

        //create string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SERVER_ADDRESS + "AddPostData.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home.this, error.toString(), Toast.LENGTH_LONG).show();
                        load.setVisibility(View.GONE);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //from android.com: A Map is a data structure consisting of a set of keys and
                // values in which each key is mapped to a single value. The class of the objects
                // used as keys is declared when the Map is declared, as is the class of the
                // corresponding values.
                Map<String,String> hashMap = new HashMap<>();

                //maps specified string key, to specified string value
                hashMap.put(Config.user, user);
                hashMap.put(Config.hid, id);

                return hashMap;
            }
        };

        //add string request to queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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
                        Intent intent = new Intent(Home.this, MainActivity.class);
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

//    //create menu option in toolbar
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.menuLogout){
//            logUserOut();
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLgout:
                load.setVisibility(View.VISIBLE);
                logUserOut();
                break;
            case R.id.btnProfile:
                startActivity(new Intent(this, Profile.class));
                break;
            case R.id.btnSbmtPst:
                load.setVisibility(View.VISIBLE);
                insertPost();
                getSmallData();
                addData();
        }
    }

}