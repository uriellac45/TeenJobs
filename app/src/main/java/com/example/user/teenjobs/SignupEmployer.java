package com.example.user.teenjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

public class SignupEmployer extends AppCompatActivity {
    FireBaseDB firebase;
    VariableListener variableListener;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    String city;
    String district;
    String businessName;
    String businessID;

    EditText etFirstName;
    EditText etLastName;
    EditText etEmail;
    EditText etPassword;
    EditText etPhone;
    EditText etCity;
    AutoCompleteTextView tvDistrict;
    EditText etBusinessName;
    EditText etBusinessNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_employer);


        etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.lastName);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etPhone = findViewById(R.id.phone);
        etCity = findViewById(R.id.city);
        tvDistrict = findViewById(R.id.tvDistrict);
        etBusinessName = findViewById(R.id.bName);
        etBusinessNumber = findViewById(R.id.bNum);


        firebase = new FireBaseDB(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, getResources()
                .getStringArray(R.array.DiscritArray));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tvDistrict.setAdapter(adapter);
    }

    public void next(View view) {

        firstName = etFirstName.getText().toString();
        lastName = etLastName.getText().toString();

        email = etEmail.getText().toString();
        password = etPassword.getText().toString();
        phone = etPhone.getText().toString();
        district = tvDistrict.getText().toString();
        city = etCity.getText().toString();
        businessName = etBusinessName.getText().toString();
        businessID = etBusinessNumber.getText().toString();
        String checkDiscrit = tvDistrict.getAdapter().toString();

        Intent g = new Intent(this, MainActivity.class);



        if (!(firstName.isEmpty() ||lastName .isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || city.isEmpty() || checkDiscrit.contains(district))) {

                Intent intent = new Intent(this, EmployerRequirements.class);
                intent.putExtra("Fname", firstName);
                intent.putExtra("Lname", lastName);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                intent.putExtra("Pnum", phone);
                intent.putExtra("city", city);
                intent.putExtra("district", district);
                intent.putExtra("Bnum", businessID);
                intent.putExtra("Bname", businessName);
                startActivity(intent);
        } else
            Toast.makeText(this, "fill all fields", Toast.LENGTH_SHORT).show();

    }

}

