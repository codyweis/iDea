//package apps.luck3y.com.idea;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
///**
// * Created by Cody Weisenberger on 2/5/2016.
// */
//public class UserLocalData {
//    public static final String NAME = "user";
//    SharedPreferences userLocalData;
//
//    public UserLocalData(Context context){
//        userLocalData = context.getSharedPreferences(NAME,0);
//    }
//
//    public void storeUserData(User user){
//        SharedPreferences.Editor ed = userLocalData.edit();
//        ed.putString("fname", user.fname);
//        ed.putString("lname", user.lname);
//        ed.putString("username", user.username);
//        ed.putString("password", user.password);
//        ed.putString("email", user.email);
//        ed.commit();
//    }
//
//    public User getUserLoggedIn(){
//        String fname = userLocalData.getString("fname", "");
//        String lname = userLocalData.getString("lname", "");
//        String username = userLocalData.getString("username", "");
//        String password = userLocalData.getString("password", "");
//        String email = userLocalData.getString("email", "");
//
//        User userStored = new User(fname, lname, username, password, email);
//
//        return userStored;
//    }
//
//    public void setUserLoggedIn(boolean loggedIn){
//        SharedPreferences.Editor ed = userLocalData.edit();
//        ed.putBoolean("loggedIn", loggedIn);
//        ed.commit();
//    }
//
//    public boolean isUserLoggedIn(){
//        if(userLocalData.getBoolean("logegdIn", false) == true){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
//
//    public void clearUserData(){
//        SharedPreferences.Editor ed = userLocalData.edit();
//        ed.clear();
//        ed.commit();
//    }
//}
