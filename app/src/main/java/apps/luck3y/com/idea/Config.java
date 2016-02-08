package apps.luck3y.com.idea;

/**
 * Created by Cody Weisenberger on 2/8/2016.
 */
public class Config {

    //database
    public static final String SERVER_ADDRESS = "http://10.0.2.2:8080/iDea/";

    //variables for php files
    public static final String username = "username";
    public static final String password = "password";

   //Check server response is equal to this string from php file
    public static final String logInMessage = "connected";

    //Sharedpreferences
    public static final String sharedPref = "sharedPref";

    //stores username
    public static final String SharedPrefUsername = "username";

    //store boolean to check if logged in
    public static final String sharedPrefBool = "loggedin";
}
