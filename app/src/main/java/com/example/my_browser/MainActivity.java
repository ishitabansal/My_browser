package com.example.my_browser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressbar;
    private WebView myview;

    private EditText editurl;
    private Button editbtn;
    private Button bkmrk ;
    private Button accessme;
    private Button accesshist;
    private ListView bkmarks22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myview = findViewById(R.id.myview);

        progressbar = findViewById(R.id.progressbar);
        editurl = findViewById(R.id.editurl);
        editbtn = findViewById(R.id.gotobtn);

        progressbar.setMax(100);
        myview.loadUrl("https://www.google.com");
        myview.setWebViewClient(new WebViewClient());
        myview.getSettings().setLoadsImagesAutomatically(true);
        myview.getSettings().setJavaScriptEnabled(true);


        if(isNetworkAvailable())
        { Toast.makeText(MainActivity.this, "Connected to Internet" , Toast.LENGTH_SHORT).show();}
        else
        {Toast.makeText(MainActivity.this, "Not Connected to Internet" , Toast.LENGTH_SHORT).show();}


        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String irl = editurl.getText().toString();
                myview.loadUrl(irl);

            }
        });


        myview.setWebChromeClient(new WebChromeClient()
                                  {
                                      @Override
                                      public void onProgressChanged(WebView view, int newProgress) {
                                          super.onProgressChanged(view, newProgress);
                                          progressbar.setProgress(newProgress);

                                   }

                                      @Override
                                      public void onReceivedTitle(WebView view, String title) {
                                          super.onReceivedTitle(view, title);
                                          getSupportActionBar().setTitle(title);

                                          String histUrl = myview.getUrl();
                                          history hm;


                                          hm = new history(1, histUrl);
                                          mydatahandler histdbhelper = new mydatahandler(MainActivity.this);
                                          boolean success2 = histdbhelper.add_One(hm);
                                      }
                                  }

        );

    }

    public void openbookmarks()
    {
        Intent intent = new Intent(this, bookmarklist.class);
        startActivity(intent);

    }

    public void openhistory()
    {
        Intent intent = new Intent(this, historylist.class);
        startActivity(intent);
    }
    public void opensettings()
    {
        Intent intent = new Intent(this, settings.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)  getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            return true;
        }
        return false;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_back:
                onBackPressed();
                break;
            case R.id.menu_frwrd:
                Onforward();
                break;
            case R.id.accessbookmarks:
                openbookmarks();
                break;
            case R.id.accesshistory:
                openhistory();
                break;
            case R.id.settingsme:
                opensettings();
                break;
            case R.id.makebkmrk:
                bookmarks bm;
                myview = findViewById(R.id.myview);
                //   historysimple hm;

                String webUrl = myview.getUrl();
                bm = new bookmarks(1,  webUrl);

                databasehandler databasehelper = new databasehandler(MainActivity.this);
                boolean success = databasehelper.addOne(bm);
                Toast.makeText(MainActivity.this, "Page Added to bookmarks" , Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    private void Onforward()
    {
        if(myview.canGoForward())
        {

            myview.goForward();
        }

        else{
            Toast.makeText(this, "Can't go forward", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if(myview.canGoBack())
        {
            myview.goBack();
        }
        else
        {finish();}
    }

}