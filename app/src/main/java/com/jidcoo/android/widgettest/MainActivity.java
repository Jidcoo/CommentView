package com.jidcoo.android.widgettest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jidcoo.android.widgettest.custom.CustomUseInLocalActivity;
import com.jidcoo.android.widgettest.simple.DefaultUseInLocalActivity;
import com.jidcoo.android.widgettest.simple.DefaultUseWithServerActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button, button1, button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button:
                intent = new Intent(MainActivity.this, DefaultUseWithServerActivity.class);
                startActivity(intent);
                break;
            case R.id.button1:
                intent = new Intent(MainActivity.this, DefaultUseInLocalActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                intent = new Intent(MainActivity.this, CustomUseInLocalActivity.class);
                startActivity(intent);
                break;
        }
    }
}
