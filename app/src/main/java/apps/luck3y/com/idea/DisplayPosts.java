package apps.luck3y.com.idea;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;
import java.util.Map;

/**
 * Created by Cody Weisenberger on 2/5/2016.
 */
public class DisplayPosts extends ListActivity implements View.OnClickListener{

    GridView gridView;
    //ArrayList<String> contentPosts = new ArrayList<String>();
    //ArrayList<String> userPosts = new ArrayList<String>();
    ArrayList<String> allPosts = new ArrayList<String>();
    JSONArray result;
    Button profile, logout, post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        profile = (Button) findViewById(R.id.profilePosts);
        logout = (Button) findViewById(R.id.logoutPosts);
        post = (Button) findViewById(R.id.newPost);

        profile.setOnClickListener(this);
        post.setOnClickListener(this);
        logout.setOnClickListener(this);

        getPosts();
    }

    class PostsAdapter extends ArrayAdapter<Posts>{

        //used to create views from xml
        private LayoutInflater layoutInflater;

        public PostsAdapter(Context context, int textViewResourceId, List<Posts> posts) {
            super(context, textViewResourceId, posts);
            layoutInflater = LayoutInflater.from(context);
        }

        //add to xml from dataset
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = layoutInflater.inflate(R.layout.activity_posts, null);
            Posts posts = getItem(position);

            TextView content = (TextView) view.findViewById(R.id.content);
            TextView user = (TextView) view.findViewById(R.id.user);
            content.setText(posts.getContent());
            user.setText(posts.getUser());

            return view;
        }
    }

    private void getPosts(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config.SERVER_ADDRESS + "GetPosts.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject = null;
                        try {
                            //json string to jsonobject
                            jsonObject = new JSONObject(response);

                            //get json sstring created in php and store to JSON Array
                            result = jsonObject.getJSONArray(Config.json_array_post);

                            //get topics from json array
                            setPosts(result);
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

    private void setPosts(JSONArray jsonArray){
        //go through items in array
        for(int i = 0; i < jsonArray.length(); i++) {
            try {
                //get json object
                JSONObject json = jsonArray.getJSONObject(i);

                //add content and user to arraylist
                allPosts.add(json.getString(Config.post_content));
                allPosts.add(json.getString(Config.post_user));
                System.out.println(allPosts);
            } catch (JSONException e) {

            }
        }
        ArrayList<Posts> post = new ArrayList<Posts>();
        System.out.println("FINAL: "+allPosts.size());
        for(int i = allPosts.size() - 1; i >= 0; i -= 2){
            post.add(new Posts(allPosts.get(i), allPosts.get(i-1)));
        }
        System.out.println(post);

        setListAdapter(new PostsAdapter(this, R.layout.activity_posts, post));
//        gridView = (GridView) findViewById(R.id.gridView);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, contentPosts);
//        gridView.setAdapter(adapter);
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
                        Intent intent = new Intent(DisplayPosts.this, MainActivity.class);
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

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.profilePosts:
                startActivity(new Intent(this, Profile.class));
            break;
            case R.id.logoutPosts:
                logUserOut();
                break;
            case R.id.newPost:
                startActivity(new Intent(this, Home.class));
        }
    }
}
