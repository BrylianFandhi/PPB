package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class reset_pw extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&*+=_])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,20}" +               //at least 8 characters
                    "$");

    TextInputLayout editTextResetCode;
    TextInputLayout editTextNewPassword;
    TextInputLayout editTextConfirmPw;
    TextView user;
    Button btl_login;
    DBhelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pw);
        user=findViewById(R.id.Username);
        editTextConfirmPw = findViewById(R.id.confirm);
        editTextNewPassword = findViewById(R.id.Pass);
        editTextResetCode = findViewById(R.id.reset_code);
        btl_login=findViewById(R.id.btl_login);
        DB=new DBhelper(this);
        Intent i=getIntent();
        user.setText(i.getStringExtra("textUser"));

        btl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=user.getText().toString();
                String repass=editTextConfirmPw.getEditText().getText().toString();
                String password=editTextNewPassword.getEditText().getText().toString();
                Boolean checkpasswordupdate=DB.updatepassword(username,password);
                if (password.equals(repass)){
                    if (checkpasswordupdate==true){
                        Intent intent=new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                        Toast.makeText(reset_pw.this, "Update Password Succsess", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(reset_pw.this, "Update Password Failed", Toast.LENGTH_SHORT).show();
                    }
                    
                }
                else{
                    Toast.makeText(reset_pw.this, "Password didnt match", Toast.LENGTH_SHORT).show();
                }
                

            }
        });

    }

    private boolean validateCode(){
        String code = editTextResetCode.getEditText().getText().toString().trim();

        if (code.isEmpty()){
            editTextResetCode.setError("Field can't be empty");
            return false;
        }else {
            editTextResetCode.setError(null);
            return true;
        }
    }

    private boolean validatePass(){
        String pass = editTextNewPassword.getEditText().getText().toString().trim();

        if (pass.isEmpty()){
            editTextNewPassword.setError("Field can't be empty");
            return false;
        }else if (!PASSWORD_PATTERN.matcher(pass).matches()){
            editTextNewPassword.setError("Password too weak");
            return false;
        }else {
            editTextNewPassword.setError(null);
            return true;
        }
    }

    private boolean validateConf(){
        String conf = editTextConfirmPw.getEditText().getText().toString().trim();
        String pass = editTextNewPassword.getEditText().getText().toString().trim();
        if (conf.isEmpty()){
            editTextConfirmPw.setError("Field can't be empty");
            return false;
        }else if (!pass.equals(conf)){
            editTextConfirmPw.setError("Confirm password not match with password");
            return false;
        }else {
            editTextConfirmPw.setError(null);
            return true;
        }
    }

//    public void postReset(View view) {
//        if(!validateCode() | !validatePass() | !validateConf()){
//            if(!validatePass()) {
//                Toast.makeText(view.getContext(), "Password must have 1 number, \nuppercase, lowercase, no space, \nspecial char, 8 character", Toast.LENGTH_LONG).show();
//            }else{return;}
//        }else{Intent i = new Intent(reset_pw.this, sukses_regist.class);
//            startActivity(i);}
//
//
//    }
}