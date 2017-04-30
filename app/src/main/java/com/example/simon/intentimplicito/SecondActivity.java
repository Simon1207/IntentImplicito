package com.example.simon.intentimplicito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    private TextView textVIew;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textVIew=(TextView)findViewById(R.id.textViewMain);
        btnNext=(Button)findViewById(R.id.buttonGoSharing);
        //Recuperar los datos del Intent con Bundle
        Bundle bundle=getIntent().getExtras();
        //comprobar que Bundle no este vacio


        if(bundle != null && bundle.getString("greeter") !=null ){
            String greeter=bundle.getString("greeter");
            Toast.makeText(SecondActivity.this,greeter,Toast.LENGTH_LONG).show();
            textVIew.setText(greeter);
        } else  {
            Toast.makeText(SecondActivity.this,"Esta vacio!", Toast.LENGTH_LONG).show();
        }
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(intent);
            }
        });
    }//onCreate
}
