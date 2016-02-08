//package apps.luck3y.com.idea;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.os.AsyncTask;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.params.BasicHttpParams;
//import org.apache.http.params.HttpConnectionParams;
//import org.apache.http.params.HttpParams;
//import org.apache.http.util.EntityUtils;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//
///**
// * Created by Cody Weisenberger on 2/6/2016.
// */
//public class ServerRequest {
//
//    //creates loading bar as well as time until a timeout and server address to access php files
//    ProgressDialog progressDialog;
//    public static final int CONNECTION_TIMEOUT = 1000*15;
//    public static final String SERVER_ADDRESS = "http://10.0.2.2:8080/iDea/";
//
//    //instantiate progress dialog
//    public ServerRequest(Context context){
//        progressDialog = new ProgressDialog(context);
//        progressDialog.setCancelable(false);
//        progressDialog.setTitle("Processing");
//        progressDialog.setMessage("......");
//    }
//
//    //once called, start async task and pass it user and callback
//    public void storeUserDataInBackground(User user, GetUserCallBack userCallBack){
//        progressDialog.show();
//        new StoreUserDataAsyncTask(user, userCallBack).execute();
//    }
//
//    public void getUserDataInBackground(User user, GetUserCallBack callBack){
//        progressDialog.show();
//        new fetchUserDataAsyncTask(user, callBack).execute();
//    }
//
//    //start background task (In Android: Async Class) passing in user and callback
//    //<void,    ,    > -->Sending to constructor instead of async task
//    //<   , void,   > --> how it receives the progress dialog, dont need to receive progress dialog,
//    // simply starting when async task starts and closes when it finishes
//    //<   ,   , void> --> what async returns
//    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{
//        User user;
//        GetUserCallBack userCallBack;
//
//        public StoreUserDataAsyncTask(User user, GetUserCallBack userCallBack){
//            this.user = user;
//            this.userCallBack = userCallBack;
//        }
//
//        //once async starts, do this in background and access server
//        @Override
//        protected Void doInBackground(Void... params) {
//            ArrayList<NameValuePair> dataToSend = new ArrayList();
//            dataToSend.add(new BasicNameValuePair("fname", user.fname));
//            dataToSend.add(new BasicNameValuePair("lname", user.lname));
//            dataToSend.add(new BasicNameValuePair("username", user.username));
//            dataToSend.add(new BasicNameValuePair("password", user.password));
//            dataToSend.add(new BasicNameValuePair("email", user.email));
//
//            HttpParams httpRequestParam = new BasicHttpParams();
//            HttpConnectionParams.setConnectionTimeout(httpRequestParam, CONNECTION_TIMEOUT);
//            HttpConnectionParams.setSoTimeout(httpRequestParam, CONNECTION_TIMEOUT);
//
//            HttpClient client = new DefaultHttpClient(httpRequestParam);
//            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");
//
//            try{
//                post.setEntity(new UrlEncodedFormEntity(dataToSend));
//                client.execute(post);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        //once async is finished
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            progressDialog.dismiss();
//            userCallBack.done(null);
//
//            super.onPostExecute(aVoid);
//        }
//    }
//
//    //getting user data, return a user
//    public class fetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {
//        User user;
//        GetUserCallBack userCallBack;
//
//        public fetchUserDataAsyncTask(User user, GetUserCallBack userCallBack) {
//            this.user = user;
//            this.userCallBack = userCallBack;
//        }
//
//        @Override
//        protected User doInBackground(Void... params) {
//            ArrayList<NameValuePair> dataToSend = new ArrayList();
//            dataToSend.add(new BasicNameValuePair("username", user.username));
//            dataToSend.add(new BasicNameValuePair("password", user.password));
//
//            HttpParams httpRequestParam = new BasicHttpParams();
//            HttpConnectionParams.setConnectionTimeout(httpRequestParam, CONNECTION_TIMEOUT);
//            HttpConnectionParams.setSoTimeout(httpRequestParam, CONNECTION_TIMEOUT);
//
//            HttpClient client = new DefaultHttpClient(httpRequestParam);
//            HttpPost post = new HttpPost(SERVER_ADDRESS + "GetUserData.php");
//
//            User returnedUser = null;
//            try{
//                post.setEntity(new UrlEncodedFormEntity(dataToSend));
//                HttpResponse httpResponse = client.execute(post);
//
//                HttpEntity entity = httpResponse.getEntity();
//                String result = EntityUtils.toString(entity);
//                JSONObject jObject = new JSONObject(result);
//
//                if(jObject.length() == 0){
//                    returnedUser = null;
//                }
//                else{
//                    String fname = jObject.getString("fname");
//                    String lname = jObject.getString("lname");
//                    String email = jObject.getString("email");
//
//                    returnedUser = new User(fname, lname, user.username, user.password, email);
//
//                }
//
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//
//            return returnedUser;
//        }
//        @Override
//        protected void onPostExecute(User returnedUser) {
//            progressDialog.dismiss();
//            userCallBack.done(returnedUser);
//            super.onPostExecute(returnedUser);
//        }
//    }
//}
