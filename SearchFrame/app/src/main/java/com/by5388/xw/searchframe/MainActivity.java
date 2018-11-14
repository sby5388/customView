package com.by5388.xw.searchframe;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.by5388.xw.searchframe.call.history.CallHistoryActivity;
import com.by5388.xw.searchframe.search.contact.QueryContactsActivity;

/**
 * @author by5388
 * @date 2018/11/07
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_query_link_man).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQueryLinkManPermission();
            }
        });
        findViewById(R.id.button_query_call_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkQueryCallHistoryPermission();
            }
        });
    }

    private void checkQueryCallHistoryPermission() {
        if (withOutPermission(Manifest.permission.READ_CALL_LOG)) {
            return;
        }
        if (withOutPermission(Manifest.permission.WRITE_CALL_LOG)) {
            return;
        }

        startActivity(new Intent(MainActivity.this, CallHistoryActivity.class));
    }

    private void checkQueryLinkManPermission() {
        if (withOutPermission(Manifest.permission.READ_CONTACTS)) {
            return;
        }
        if (withOutPermission(Manifest.permission.WRITE_CONTACTS)) {
            return;
        }
        if (withOutPermission(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            return;
        }
        if (withOutPermission(Manifest.permission.ACCESS_FINE_LOCATION)) {
            return;
        }
        startActivity(new Intent(MainActivity.this, QueryContactsActivity.class));
    }


    private boolean withOutPermission(@NonNull String permission) {
        if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), permission);
            intent.setData(uri);
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_PERMISSION_CODE);
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //授权成功-->跳转页面
                    checkQueryLinkManPermission();
                } else {
                    // 授权失败！
                    Toast.makeText(this, "授权失败！", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
