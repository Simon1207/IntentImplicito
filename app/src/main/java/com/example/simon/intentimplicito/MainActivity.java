package com.example.simon.intentimplicito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private EditText edit;
    private String GREETER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=(Button)findViewById(R.id.buttonMain);
        edit=(EditText)findViewById(R.id.editMain);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtener el Valor EditText
                GREETER=edit.getText().toString();

                //Acceder al segundo Activity y mandarle un string
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("greeter",GREETER); //ID + dato a enviar a Second Acitivty
                startActivity(intent);
            }
        });
    }
}
