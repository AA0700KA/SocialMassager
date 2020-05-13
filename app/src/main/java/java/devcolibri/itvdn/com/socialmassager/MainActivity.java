package java.devcolibri.itvdn.com.socialmassager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button allAppsBtn = findViewById(R.id.applications);
        allAppsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, Send.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent1 = new Intent(this, Contacts.class);
                startActivity(intent1);
                break;
            case R.id.applications :
                Intent intent2 = new Intent(this, ChoseMessangerActivity.class);
                startActivity(intent2);
                break;
        }


    }
}
