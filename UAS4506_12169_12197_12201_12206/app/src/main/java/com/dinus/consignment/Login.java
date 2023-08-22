package com.dinus.consignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[@#$%^&*+=_])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,20}" +               //at least 8 characters
                    "$");
    EditText textPass, textUser;
    Button btl_login;
    DBhelper DB;
    Boolean utkTombol = false;
    SharedPreferences sharedPreferences;
    public static final String SHARED = "1";
    public static final String tombol = "utkTombol";
    private boolean switchon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textPass = findViewById(R.id.Pass);
        textUser = findViewById(R.id.Username);
        btl_login=findViewById(R.id.btl_login);
        DB=new DBhelper(this);

        sharedPreferences = getSharedPreferences(SHARED, MODE_PRIVATE);


        btl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=textUser.getText().toString().trim();
                String PW = textPass.getText().toString().trim();

                if(TextUtils.isEmpty(textUser.getText().toString().trim())
                        &&
                        TextUtils.isEmpty(textPass.getText().toString().trim())){
                    Toast.makeText((view.getContext()), "Email and Password cannot be empty", Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(textUser.getText().toString().trim())){
                    Toast.makeText(view.getContext(), "Email cannot be empty!", Toast.LENGTH_LONG).show();
                }
                else
                if(!isValidEmail(textUser.getText().toString().trim())){
                    Toast.makeText(view.getContext(), "Invalid email!", Toast.LENGTH_LONG).show();
                }
                else if(!PASSWORD_PATTERN.matcher(PW).matches()){
                    Toast.makeText(view.getContext(), "Invalid password!", Toast.LENGTH_LONG).show();
                }
                else if(TextUtils.isEmpty(textPass.getText().toString())){
                    Toast.makeText(view.getContext(), "Password cannot be empty!", Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean checkuserpass=DB.checkusernamepassword(user,PW);
                    if (checkuserpass==true){
                        Toast.makeText(Login.this, "Login in succsess", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Login.this, main_market.class);
                        i.putExtra("textUser",user);
                        saveData();
                        startActivity(i);
                    }
                    else if(checkuserpass==false){
                        Toast.makeText(Login.this, "Invalid Username Or password please Register if u dont have an account", Toast.LENGTH_SHORT).show();
                    }

                }
//

            }
        });
        loadData();
    }

    public void saveData() {
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(tombol, true);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED, MODE_PRIVATE);
        switchon = sharedPreferences.getBoolean(tombol, false);
    }

    public void updateViews(){

    }


//    public void postLogin(View view) {
//        String PW = textPass.getText().toString().trim();
//        if(TextUtils.isEmpty(textUser.getText().toString().trim())
//                &&
//                TextUtils.isEmpty(textPass.getText().toString().trim())){
//            Toast.makeText((view.getContext()), "Email and Password cannot be empty", Toast.LENGTH_LONG).show();
//        }
//        if(TextUtils.isEmpty(textUser.getText().toString().trim())){
//            Toast.makeText(view.getContext(), "Email cannot be empty!", Toast.LENGTH_LONG).show();
//        }
//        else
//        if(!isValidEmail(textUser.getText().toString().trim())){
//            Toast.makeText(view.getContext(), "Invalid email!", Toast.LENGTH_LONG).show();
//        }
//        else if(!PASSWORD_PATTERN.matcher(PW).matches()){
//            Toast.makeText(view.getContext(), "Invalid password!", Toast.LENGTH_LONG).show();
//        }
//        else if(TextUtils.isEmpty(textPass.getText().toString())){
//            Toast.makeText(view.getContext(), "Password cannot be empty!", Toast.LENGTH_LONG).show();
//        }
//        else{
//            Intent i = new Intent(Login.this, main_market.class);
//            startActivity(i);
//        }
//
//    }

    public void sign(View view) {
        Intent i = new Intent(Login.this, sign_acc.class);
        startActivity(i);
    }

    public void forgat(View view) {
        Intent i = new Intent(Login.this, forgot_pw.class);
        startActivity(i);
    }

    public static boolean isValidEmail (CharSequence email){
        return (Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}