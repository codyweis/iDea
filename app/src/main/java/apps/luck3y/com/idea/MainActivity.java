package apps.luck3y.com.idea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtUsrnm, txtPswrd;
    Button btnLogn1, btnCrtAcnt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogn1 = (Button) findViewById(R.id.btnLogn1);
        btnCrtAcnt1 = (Button) findViewById(R.id.btnCrtAcnt1);

        btnLogn1.setOnClickListener(this);
        btnCrtAcnt1.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogn1:
                startActivity(new Intent(this, Login.class));
                break;
            case R.id.btnCrtAcnt1:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }
}
