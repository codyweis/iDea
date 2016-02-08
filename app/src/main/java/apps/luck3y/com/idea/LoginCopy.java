//package apps.luck3y.com.idea;
//
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
///**
// * Created by Cody Weisenberger on 2/5/2016.
// */
//public class LoginCopy extends AppCompatActivity implements View.OnClickListener{
//
//    Button btnAcpt, btnCrtAcnt2;
//    EditText txtUsrnm, txtPswrd;
//    UserLocalData userLocalData;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        txtUsrnm = (EditText) findViewById(R.id.txtUsrnm);
//        txtPswrd = (EditText) findViewById(R.id.txtPswrd);
//        btnAcpt = (Button) findViewById(R.id.btnAcpt);
//        btnCrtAcnt2 = (Button) findViewById(R.id.btnCrtAcnt2);
//
//        btnAcpt.setOnClickListener(this);
//        btnCrtAcnt2.setOnClickListener(this);
//
//        userLocalData = new UserLocalData(this);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()){
//            case R.id.btnAcpt:
//                String username = txtUsrnm.getText().toString();
//                String password = txtPswrd.getText().toString();
//
//                User user = new User(username, password);
//
//                authenticate(user);
//
//                break;
//
//            case R.id.btnCrtAcnt2:
//                startActivity(new Intent(this, Register.class));
//
//                break;
//        }
//    }
//
//    //see if username and passwwrod are in database
//    private void authenticate(User user){
//        ServerRequest serverRequest = new ServerRequest(this);
//        serverRequest.getUserDataInBackground(user, new GetUserCallBack() {
//            @Override
//            public void done(User returnUser) {
//                if(returnUser == null){
//                    showErrorMessage();
//                }
//                else{
//                    logUserIn(returnUser);
//                }
//            }
//        });
//    }
//
//    private void showErrorMessage(){
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(LoginCopy.this);
//        dialogBuilder.setMessage("Incorrect user details");
//        dialogBuilder.setPositiveButton("Ok", null);
//        dialogBuilder.show();
//    }
//
//    private void logUserIn(User returnUser){
//        userLocalData.storeUserData(returnUser);
//        userLocalData.setUserLoggedIn(true);
//        startActivity(new Intent(this, .class));
//
//    }
//}
