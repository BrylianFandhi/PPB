package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class forgot_pw extends AppCompatActivity {
    TextView textUser;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DB=new DBhelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pw);
        textUser = findViewById(R.id.Email);


    }
    public static boolean isValidEmail (CharSequence email){
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public void postEmail(View view) {
        String user=textUser.getText().toString();
        Boolean checkuser=DB.checkusername(user);
        if (checkuser==true){
            Intent i = new Intent(getApplicationContext(), reset_pw.class);
            i.putExtra("textUser",user);
            startActivity(i);
        }
        else{
            Toast.makeText(forgot_pw.this, "Cant find Email", Toast.LENGTH_SHORT).show();
        }


        if(TextUtils.isEmpty(textUser.getText().toString().trim())){
            Toast.makeText(view.getContext(), "Email cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else if(!isValidEmail(textUser.getText().toString().trim())){
            Toast.makeText(view.getContext(), "Invalid email!", Toast.LENGTH_LONG).show();
        }
//        else{
//            Intent i = new Intent(forgot_pw.this, reset_pw.class);
//            startActivity(i);
//        }
    }
}