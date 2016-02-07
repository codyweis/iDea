package apps.luck3y.com.idea;

/**
 * Created by Cody Weisenberger on 2/5/2016.
 */
public class User {

    String fname, lname, username, password, email;

    public User(String fname, String lname, String username, String password, String email){
        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
}
