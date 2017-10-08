package com.developer.aashish.userappdesign;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Aashish on 10/8/2017.
 */

public class forgetpassword extends MainActivity {
    EditText mobfor;
    TextView getpass;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design4);
        send=(Button)findViewById(R.id.send);
        mobfor=(EditText)findViewById(R.id.mobfor);
        getpass=(TextView)findViewById(R.id.getpass);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=mobfor.getText().toString();
                if(mobile.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter your securityhint", Toast.LENGTH_SHORT).show();
                }
                else {
                    String storedPassword = loginDataBaseAdapter.getAllTags(mobile);
                    if (storedPassword == null) {
                        Toast.makeText(getApplicationContext(), "Please enter correct securityhint", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.d("GET PASSWORD", storedPassword);
                        getpass.setText(storedPassword);

                        Intent passs=new Intent(forgetpassword.this,design6.class).putExtra("password",getpass.getText().toString());
                        startActivity(passs);
//Intent kl=new Intent(forgetpassword.this,design6.class);
//                startActivity(kl);

                    }
                }
            }
        });
    }
}
