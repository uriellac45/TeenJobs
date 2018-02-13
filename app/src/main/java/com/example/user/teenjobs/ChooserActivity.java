package com.example.user.teenjobs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
    }

    public void signInEmployer(View view) {
        Intent intent = new Intent(this, SignupEmployer.class);
        startActivity(intent);
    }

    public void signInEmployee(View view) {
        Intent intent = new Intent(this, SignupEmployer.class);
        startActivity(intent);
    }
}
