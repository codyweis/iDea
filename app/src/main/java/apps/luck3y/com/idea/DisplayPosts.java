package apps.luck3y.com.idea;

import android.app.Activity;
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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cody Weisenberger on 2/5/2016.
 */
public class DisplayPosts extends ListActivity implements View.OnClickListener{

    //ArrayList<String> contentPosts = new ArrayList<String>();
    //ArrayList<String> userPosts = new ArrayList<String>();
    ArrayList<String> allPosts = new ArrayList<String>();
    JSONArray result;
    JSONArray result2;
    Button profile, logout, post;
    ArrayList<String> likeCount = new ArrayList<String>();
    int n = 0;

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

    public class PostsAdapter extends ArrayAdapter<Posts>  {
        //used to create views from xml
        Context context;
        int textViewResourceId;
        ArrayList<Posts> mPosts = new ArrayList<Posts>();

        public PostsAdapter(Context context, int textViewResourceId, ArrayList<Posts> posts) {
            super(context, textViewResourceId, posts);
            this.textViewResourceId = textViewResourceId;
            this.context = context;
            this.mPosts = posts;
        }

        // view convertview = recycled view
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final Holder holder;
            //checks if recycled view is null, thewn creates new view, if not null, use same view
            if(view == null){
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                view = inflater.inflate(textViewResourceId, parent, false);

                Button like = (Button) view.findViewById(R.id.btnLike);
                TextView content = (TextView) view.findViewById(R.id.content);
                TextView user = (TextView) view.findViewById(R.id.user);
                TextView topic = (TextView) view.findViewById(R.id.topic);
                TextView date = (TextView) view.findViewById(R.id.date);
                TextView likes = (TextView) view.findViewById(R.id.likeCount);
                TextView hiddenId = (TextView) view.findViewById(R.id.hiddenID);
                holder = new Holder(content, user, topic, date, likes, hiddenId, like);

                holder.like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        createLike(position, holder.like, holder.hiddenId, holder.likes);//Added one more parameter "position"
                    }
                });

                view.setTag(holder);
            }
            else{
                holder = (Holder) view.getTag();
            }
//            try{
//                HashMap<String, String> map = mPosts.get(position);
//                holder.like.setText(map.get("name"));
//
//            }catch (Exception e){
//
//            }

            Posts posts = mPosts.get(position);
            holder.content.setText(posts.getContent());
            holder.user.setText(posts.getUser());
            holder.topic.setText(posts.getTopic());
            holder.date.setText(posts.getDate());
            holder.likes.setText(posts.getLikes());
            holder.hiddenId.setText(posts.getId());

            return view;
        }

        private void createLike(final int position, final Button like, final TextView hiddenid, final TextView likes){
            final String hid = hiddenid.getText().toString().trim();

            if(like.getText().toString().equalsIgnoreCase("like")){
                System.out.println("POSITION: "+position);
                System.out.println("MPOOSTSPOSITION: "+mPosts.get(position));
                mPosts.get(position);
                like.setText("UnLike");
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.SERVER_ADDRESS + "LikePost.php",
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                JSONObject jsonObject = null;
                                try {
                                    //counter
                                    n+=1;

                                    //json string to jsonobject
                                    jsonObject = new JSONObject(response);
                                    //get json sstring created in php and store to JSON Array
                                    result2 = jsonObject.getJSONArray(Config.json_array_likes);
                                    //get username from json array
                                    String likestring = getLikeCount(result2);
                                    String likenum = mPosts.get(position).getLikes(likestring);
                                    likes.setText(likenum);
                                    mPosts.get(position).setLikes(likenum);

                                } catch (JSONException e) {
                                    e.printStackTrace();
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

                        // corresponding values.
                        Map<String,String> hashMap = new HashMap<>();
                        //maps specified string key, to specified string value
                        hashMap.put(Config.hid, hid);
                        return hashMap;
                    }
                };

                //add string request to queue
                RequestQueue requestQueue = Volley.newRequestQueue(DisplayPosts.this);
                requestQueue.add(stringRequest);
            }
            else{
                like.setText("Like");
                //mPosts.get(position);

            }
        }
        private String getLikeCount(JSONArray jsonArray){
            System.out.println("JSON: " + jsonArray);
            String lc = null;
            for(int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject json = jsonArray.getJSONObject(i);
                    likeCount.add(json.getString(Config.getLike));
                    System.out.println("i: " + i);
                    lc = likeCount.get(n - 1);
                } catch (JSONException e) {
                }
            }
            System.out.println("LC: " + lc);
            return lc;
        }

         class Holder{
            public TextView content;
            public TextView user;
            public TextView topic;
            public TextView date;
            public TextView likes;
            public TextView hiddenId;
            public Button like;

            public Holder(TextView content, TextView user, TextView topic, TextView date, TextView likes, TextView hiddenId, Button like) {
                this.content = content;
                this.user = user;
                this.topic = topic;
                this.date = date;
                this.likes = likes;
                this.hiddenId = hiddenId;
                this.like = like;
            }
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

                allPosts.add(json.getString(Config.like_count));
                allPosts.add(json.getString(Config.post_date));
                allPosts.add(json.getString(Config.post_topic));
                allPosts.add(json.getString(Config.post_user));
                allPosts.add(json.getString(Config.post_content));
                allPosts.add(json.getString(Config.post_id));

            } catch (JSONException e) {

            }
        }
        ArrayList<Posts> post = new ArrayList<Posts>();
        for(int i = allPosts.size() - 1; i >= 0; i -= 6){
            post.add(new Posts(allPosts.get(i), allPosts.get(i - 1), allPosts.get(i - 2), allPosts.get(i - 3), allPosts.get(i - 4), allPosts.get(i - 5)));
        }
        setListAdapter(new PostsAdapter(this, R.layout.activity_posts, post));
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
                break;
        }
    }
}