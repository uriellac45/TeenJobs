package com.example.user.teenjobs;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FireBaseDB firebase;
    Context context=this;
    VariableListener variableListener;
    String mail;
    Intent t;
    Intent g;
    EditText email;
    EditText password;
    String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        email = (EditText) findViewById(R.id.email);
        t = new Intent(this, Cards.class);
        g=  new Intent(this, SignupEmployer.class);
        password = (EditText) findViewById(R.id.password);
        variableListener= new VariableListener();
        firebase= new FireBaseDB(this);
    }


    public void login(View view) {
        mail = email.getText().toString();
        Password = password.getText().toString();
        if (!mail.isEmpty() && !Password.isEmpty()) {

            variableListener.setListener(new VariableListener.ChangeListener() {
                @Override
                public void onChange() {
                    String message = (String) variableListener.getVar();
                    if (message == null)
                        startActivity(t);
                    else
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                }
            });
            firebase.signInWithEmailAndPassword(mail, Password, variableListener);
        }
    }
    public void sign (View view){
        startActivity(g);

    }
}

