package apps.luck3y.com.idea;

/**
 * Created by Cody Weisenberger on 2/8/2016.
 */
public class Config {

    //session id
    public static final String SID = "SessionID";

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

    public static final String getUsername = "username";

    //json array
    public static final String json_array = "result";

   //Check server response is equal to this string from php file
    public static final String logInMessage = "connected";

    //check server response is equal to createdPost messessage
    public static final String postCreated = "created";

    //rest of createpost vars
    public static final String content = "content";
    public static final String topic = "topic_id";

    //Sharedpreferences
    public static final String sharedPref = "sharedPref";

    //stores username
    public static final String SharedPrefUsername = "username";

    //store boolean to check if logged in
    public static final String sharedPrefBool = "loggedin";
}
