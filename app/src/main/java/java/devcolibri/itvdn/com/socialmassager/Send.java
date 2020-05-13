package java.devcolibri.itvdn.com.socialmassager;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.widget.EditText;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import android.widget.EditText;

public class Send extends MainActivity {
    private EditText text;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);

        text = findViewById(R.id.text);
    }

    public void send(View v) {
        data = text.getText().toString();
        Log.d("data", data);

        Log.d("data", data + " Clicking");

        Intent intent = getDefaultShareIntent();
        /** Setting a share intent */
        mShareActionProvider.setShareIntent(intent);
    }


    private ShareActionProvider mShareActionProvider;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        /** Inflating the current activity's menu with res/menu/items.xml */
        getMenuInflater().inflate(R.menu.ex_002_manu_main, menu);

        MenuItem shareItem = menu.findItem(R.id.share);
        /** Getting the actionprovider associated with the menu ex_005_item whose id is share */
        mShareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(shareItem);
        Log.d("data", data + " Options menu");

        Intent intent = getDefaultShareIntent();
        /** Setting a share intent */
        mShareActionProvider.setShareIntent(intent);

        return super.onCreateOptionsMenu(menu);

    }

    /** Returns a share intent */
    private Intent getDefaultShareIntent(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
       // Log.d("data", data + " Intent");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Send Default Massage");
        intent.putExtra(Intent.EXTRA_TEXT,data);
        return intent;
    }
}
