package apps.luck3y.com.idea;

/**
 * Created by Cody Weisenberger on 2/8/2016.
 */
public class Config {

    //session id
    public static final String SID = "SessionID";

    //store post id user
    public static final String storePostUser = "user";
    public static final String storePostId = "post_id";

    //database
    public static final String SERVER_ADDRESS = "http://ideaapp.esy.es/";

    //variables for login
    public static final String username = "username";
    public static final String password = "password";
    public static final String fname = "fname";
    public static final String lname = "lname";
    public static final String email = "email";

    //register variables
    public static final String registered = "successful";
    public static final String regerror = "Could not register";
    public static final String usererror = "Username already registered";
    public static final String emailerror = "Email already registered";

    //variables for gettopics
    public static final String topic_id = "topic_id";
    public static final String topic_title = "title";

    //variables for postdata id and user
    public static final String user_data_post = "user";
    public static final String id_data = "id";

    //variables for getposts
    public static final String post_content = "content";
    public static final String post_user = "user";
    public static final String post_topic = "topic_title";
    public static final String post_date = "post_date";
    public static final String like_count = "likes";
    public static final String post_id = "id";

    //like method in display posts
    public static final String hid = "post_id";
    public static final String user = "user";

    //variables for getuserdata
    public static final String getUsername = "username";
    public static final String getfname = "fname";
    public static final String getlname = "lname";
    public static final String getemail = "email";

    //json array
    public static final String json_array = "result";
    public static final String json_array_user = "resultUser";
    public static final String json_array_post = "resultPost";
    public static final String json_array_ind_post = "resultIndPost";
    public static final String json_array_data = "dataResult";

   //Check server response is equal to this string from php file
    public static final String logInMessage = "connected";
    public static final String likeResponse = "success";

    //check server response is equal to createdPost messessage
    public static final String postCreated = "created";

    //rest of createpost vars
    public static final String content = "content";
    public static final String topic = "topic";

    //Sharedpreferences
    public static final String sharedPref = "sharedPref";

    //stores username
    public static final String SharedPrefUsername = "username";

    //store boolean to check if logged in
    public static final String sharedPrefBool = "loggedin";
}
