package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign_acc extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&*+=_])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$");

    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");


    TextInputLayout teksUser, teksEmail, teksPass, teksConf;
    Button signin;
    DBhelper DB;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        teksConf = findViewById(R.id.Confirm);
        teksEmail = findViewById(R.id.Email);
        teksPass = findViewById(R.id.Pass);
        teksUser = findViewById(R.id.Username);
        signin=findViewById(R.id.btl_login);
        DB=new DBhelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=teksEmail.getEditText().getText().toString();
                String pass=teksPass.getEditText().getText().toString();
                String repass=teksConf.getEditText().getText().toString();
                if (email.equals("")|| pass.equals("")||repass.equals("")){
                    Toast.makeText(sign_acc.this, "Please Enter All fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuse=DB.checkusername(email);
                    if(validatePass()&&validateConf()){
                        Boolean checkuser=DB.checkusername(email);
                        if (checkuser==false){
                            Boolean insert=DB.insertdata(email,pass);
                            if (insert==true){
                                Toast.makeText(sign_acc.this, "RegSucsess", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(getApplicationContext(),sukses_regist.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(sign_acc.this, "RegFail", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(sign_acc.this, "User alredy exist pleas login", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(sign_acc.this, "Password Invalid", Toast.LENGTH_SHORT).show();
                    }
                }
                boolean check=DB.checkusername(email);

                if(!validateEmail() |  !validateConf() | !validateUser() || !validatePass()&&check==false){
                    if(!validatePass()) {
                        Toast.makeText(view.getContext(), "Password must have 1 number, \nuppercase, lowercase, no space, \nspecial char, 8 character", Toast.LENGTH_LONG).show();
                    }else{return;}

                } else if(validateEmail()|validateConf()|validatePass()|validateUser()&&check==false){
                    Intent i = new Intent(sign_acc.this, sukses_regist.class);
                    startActivity(i);}

            }
        });
    }

    private boolean validatePass(){
        String pass = teksPass.getEditText().getText().toString().trim();

        if (pass.isEmpty()){
            teksPass.setError("Field can't be empty");
            return false;
        }else if (!PASSWORD_PATTERN.matcher(pass).matches()){
            teksPass.setError("Password too weak");
            return false;
        }else {
            teksPass.setError(null);
            return true;
        }
    }

    private boolean validateConf(){
        String conf = teksConf.getEditText().getText().toString().trim();
        String pass = teksPass.getEditText().getText().toString().trim();
        if (conf.isEmpty()){
            teksConf.setError("Field can't be empty");
            return false;
        }else if (!pass.equals(conf)){
            teksConf.setError("Confirm password not match with password");
            return false;
        }else {
            teksConf.setError(null);
            return true;
        }
    }

    private boolean validateUser(){
        String user = teksUser.getEditText().getText().toString().trim();
        if (user.isEmpty()){
            teksUser.setError("Field can't be empty");
            return false;
        }else if (!USERNAME_PATTERN.matcher(user).matches()){
            teksUser.setError("Username must not have spaces and minimum 6 char");
            return false;
        }else {
            teksUser.setError(null);
            return true;
        }

    }
        private boolean validateEmail(){
            String i_email = teksEmail.getEditText().getText().toString().trim();
            if (i_email.isEmpty()){
                teksEmail.setError("Field can't be empty");
                return false;
            }else if (!Patterns.EMAIL_ADDRESS.matcher(i_email).matches()){
                teksEmail.setError("Please enter a valid email address");
                return false;
            }else {
                teksEmail.setError(null);
                return true;

            }
        }

//    public void postSign(View view) {
//        if(!validateEmail() |  !validateConf() | !validateUser() || !validatePass()){
//            if(!validatePass()) {
//                Toast.makeText(view.getContext(), "Password must have 1 number, \nuppercase, lowercase, no space, \nspecial char, 8 character", Toast.LENGTH_LONG).show();
//            }else{return;}
//
//        } else{
//            Intent i = new Intent(sign_acc.this, sukses_regist.class);
//            startActivity(i);}
//    }


}