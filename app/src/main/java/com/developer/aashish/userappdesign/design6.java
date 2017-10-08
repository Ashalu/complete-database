package com.developer.aashish.userappdesign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Aashish on 10/8/2017.
 */

public class design6 extends forgetpassword {
    Button confirm,cancel;
    TextView passs;
    EditText newpass,conpass;
forgetpassword m=new forgetpassword();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.design6);
        newpass=(EditText)findViewById(R.id.newpass);
        conpass=(EditText)findViewById(R.id.conpass);
//        passs=(TextView)findViewById(R.id.passs);
//        cancel=(Button)findViewById(R.id.cancel) ;
        confirm=(Button)findViewById(R.id.done);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = getIntent().getStringExtra("password");
//                passs.setText(id);
                String a=newpass.getText().toString();
                String b=conpass.getText().toString();
                loginDataBaseAdapter.updateEntry(a,b,id);

Intent io=new Intent(design6.this,MainActivity.class);
                startActivity(io);




            }
        });
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent main=new Intent(design6.this,MainActivity.class);
//                startActivity(main);
//            }
//        });

    }
}
