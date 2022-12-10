package com.example.request_permissions_using_tedpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RequestRunTimeActivity extends AppCompatActivity {


    private static final int PERMISSION_REQUEST_CODE = 10;
    private Button btnRequestRunTime, btnOpenSetting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_run_time);
        createinit();
    }

    private void createinit() {

        btnRequestRunTime = findViewById(R.id.btn_request_permissions);
        btnOpenSetting = findViewById(R.id.btn_open_settings);
        btnRequestRunTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermissions();
            }
        });
        btnOpenSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenSeting();
            }
        });
    }

    private void OpenSeting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package",getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void requestPermissions() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return;
        }
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA ) == PackageManager.PERMISSION_GRANTED
                &&  ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE ) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(RequestRunTimeActivity.this, "Permission Granted Successful", Toast.LENGTH_SHORT).show();
        }else {
            String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE };
            ActivityCompat.requestPermissions(this,  permissions, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if( requestCode == PERMISSION_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(RequestRunTimeActivity.this, "Permission Granted 2", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(RequestRunTimeActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
}