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
    private Button login;
    private EditText name;
    public String s1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name);

        // login = (Button) findViewById(R.id.log);
        //signup = (Button) findViewById(R.id.sign);

    }

    public void onclick(View view) {

        s1 = name.getText().toString();
        if (s1 == null) {

            Toast.makeText(getApplicationContext(), "please enter name", Toast.LENGTH_SHORT).show();
        }

        if (view.getId() == R.id.search) {

            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            //Bundle bundle= new Bundle();
            //bundle.putString("key","s1");
            intent.putExtra("key", s1);
            startActivity(intent);
        }
    }


}
