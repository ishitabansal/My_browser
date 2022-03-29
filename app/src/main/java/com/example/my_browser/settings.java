package com.example.my_browser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class settings extends AppCompatActivity {

    private WebView myview;

    private Switch cookieenable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Settings");
        setContentView(R.layout.activity_settings);

        myview = findViewById(R.id.myview);

        cookieenable = findViewById(R.id.cookieenable);

        cookieenable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(!b)
                {
                    Toast.makeText(settings.this, "Cookies Disabled", Toast.LENGTH_SHORT).show();
                    CookieManager.getInstance().setAcceptCookie(false);

                }
                else
                {
                    Toast.makeText(settings.this, "Cookies Enabled", Toast.LENGTH_SHORT).show();
                    CookieManager.getInstance().setAcceptCookie(true);
                }
            }
        });

    }
}