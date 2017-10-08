package com.developer.aashish.userappdesign;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button login,newuser,existing;
    EditText enterpassword,entermobile;
    LoginDataBaseAdapter loginDataBaseAdapter;
    DataBaseHelper db;
    TextView forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
forget=(TextView)findViewById(R.id.forget);
        login = (Button) findViewById(R.id.login);
        newuser = (Button) findViewById(R.id.newuser);
        enterpassword=(EditText)findViewById(R.id.enterpassword);
        entermobile=(EditText)findViewById(R.id.entermobile);
        loginDataBaseAdapter = new LoginDataBaseAdapter(getApplicationContext());
        loginDataBaseAdapter.open();
        existing = (Button) findViewById(R.id.existing);
        existing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String Password=enterpassword.getText().toString();
                 String mobile=entermobile.getText().toString();
                String storedPassword=loginDataBaseAdapter.getSinlgeEntry(Password);
                String storedmobile=loginDataBaseAdapter.getSinlgeEntr(mobile);

                if(Password.equals(storedPassword)&&mobile.equals(storedmobile))
                {
                    Toast.makeText(MainActivity.this, "Congrats: Login Successfully", Toast.LENGTH_LONG).show();
                    Intent ii=new Intent(MainActivity.this,Home.class);
                    startActivity(ii);
                }
                else
                if(Password.equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter Your Password", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Password Incorrect", Toast.LENGTH_LONG).show();
                }
            }        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent forg=new Intent(MainActivity.this,forgetpassword.class);
                startActivity(forg);
            }
        });
    }
//            public void ShowMessage(String title,String Message)
//            {
//                AlertDialog.Builder builder=new AlertDialog.Builder(this);
//                builder.setTitle(title);
//                builder.setMessage(Message);
//                builder.setCancelable(true);
//                builder.show();
//            }
//


}
