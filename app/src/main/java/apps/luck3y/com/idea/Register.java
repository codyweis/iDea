package apps.luck3y.com.idea;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Cody Weisenberger on 2/5/2016.
 */
public class Register extends AppCompatActivity implements View.OnClickListener{

    Button btnRegstr;
    EditText txtRegUsrnm, txtRegFrst, txtRegLst, txtRegPswrd, txtRegPswrdCnfrm, txtRegEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtRegEmail = (EditText) findViewById(R.id.txtRegEmail);
        txtRegFrst = (EditText) findViewById(R.id.txtRegFrst);
        txtRegLst = (EditText) findViewById(R.id.txtRegLst);
        txtRegPswrd = (EditText) findViewById(R.id.txtRegPswrd);
        txtRegPswrdCnfrm = (EditText) findViewById(R.id.txtRegPswrdCnfrm);
        txtRegUsrnm = (EditText) findViewById(R.id.txtRegUsrnm);

        btnRegstr = (Button) findViewById(R.id.btnRegstr);

        btnRegstr.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnRegstr:

                String fname = txtRegFrst.getText().toString();
                String lname = txtRegLst.getText().toString();
                String password = txtRegPswrd.getText().toString();
                String username = txtRegUsrnm.getText().toString();
                String email = txtRegEmail.getText().toString();

                User newUser = new User(fname, lname, password, username, email);

                break;
        }
    }
}
