package com.example.simon.intentimplicito;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.R.attr.phoneNumber;

public class ThirdActivity extends AppCompatActivity {
private EditText editTextPhone;
    private EditText editTextWeb;
    private ImageButton imgBtnPhone;
    private ImageButton imgBtnWeb;
    private ImageButton imgBtnCamera;
    private final int PHONE_CALL_CODE=100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        editTextPhone=(EditText)findViewById(R.id.editTextPhone);
        editTextWeb=(EditText)findViewById(R.id.editTextweb);
        imgBtnPhone=(ImageButton)findViewById(R.id.imageButtonPhone);
        imgBtnWeb=(ImageButton)findViewById(R.id.imageButtonWeb);
        imgBtnCamera=(ImageButton)findViewById(R.id.imageButtonCamera);

        imgBtnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber=editTextPhone.getText().toString();
                if(phoneNumber!=null){
                    //Comprobar version actual de android se esta ejecutando
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE},PHONE_CALL_CODE);

                    } else {
                        OlderVersions(phoneNumber);
                    }
                }
            }
            private void OlderVersions(String phoneNumber){ //Metodo para comprobar versiones antiguas
                Intent intentCall=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ phoneNumber)); //ACCION + URI
                if(CheckPermission(Manifest.permission.CALL_PHONE)){
                    startActivity(intentCall);
                } else{
                    Toast.makeText(ThirdActivity.this,"Acceso con permiso",Toast.LENGTH_SHORT).show();
                }


            }

        });
    }//onCreate

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //CASO DEL TELEFONO
        switch (requestCode){
            case PHONE_CALL_CODE:
                String permission=permissions[0];
                int result = grantResults[0];
                if(permission.equals(Manifest.permission.CALL_PHONE)){
                    //comprobar si ha sido aceptado o denegado la peticion de permiso
                    if(result== PackageManager.PERMISSION_GRANTED){
                        //Concedio permiso
                        String phoneNumber=editTextPhone.getText().toString();
                        Intent intentCall=new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+ phoneNumber)); //ACCION + URI
                        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)return;
                            startActivity(intentCall);



                    }else{
                        //NO concedio su permiso
                        Toast.makeText(ThirdActivity.this,"Denegaste permiso",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode,permissions,grantResults);
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private boolean CheckPermission(String permission){ //COMPRUEBA SI SE CUENTA CUENTA CON EL PERMISO DENTRO DE MANIFEST
        int result=this.checkCallingOrSelfPermission(permission);
        return result== getPackageManager().PERMISSION_GRANTED;
    }
}//class
