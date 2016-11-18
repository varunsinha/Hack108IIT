package com.varunsinha.jam;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;

public class MainActivity extends Activity {
private  Button login;
    private  EditText username,password;
    private  String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.user);
        password = (EditText) findViewById(R.id.pass);
        // login = (Button) findViewById(R.id.log);
        //signup = (Button) findViewById(R.id.sign);


        s1 = username.getText().toString();
        s2 = password.getText().toString();

    }

    public void onclick(View view)
    {
        if(view.getId()==R.id.log) {




                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.putExtra("key", s1);
                startActivity(intent);
            }
        }





}
