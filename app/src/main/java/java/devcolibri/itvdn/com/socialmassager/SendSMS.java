package java.devcolibri.itvdn.com.socialmassager;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.net.Uri;
import android.widget.TextView;

public class SendSMS extends AppCompatActivity {

    private TextView phoneTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sms);
        String phone = getIntent().getStringExtra("phone");

        phoneTxt = findViewById(R.id.number);
        phoneTxt.setText(phone);
    }

    public void smsSend(View v) {
        String number = phoneTxt.getText().toString();
        EditText message= findViewById(R.id.message);
       // String toSms="smsto:"+number.getText().toString();
        String messageText= message.getText().toString();
        Intent intent = new Intent(this, ChoseMessangerActivity.class);
        intent.putExtra("number", number);
        intent.putExtra("message", messageText);
        startActivity(intent);
//        Intent sms = new Intent(Intent.ACTION_SEND, Uri.parse(toSms));
//
//        sms.putExtra("sms_body", messageText);
//        startActivity(sms);

//        Intent smsIntent = new Intent(Intent.ACTION_SEND);
//        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
//        smsIntent.setPackage("com.viber.voip");
//        smsIntent.setData(Uri.parse("sms:+38(095)0750703"));
//        smsIntent.putExtra("address", "+38(095)0750703");
//        smsIntent.putExtra("sms_body", "body  text");
//        startActivity(smsIntent);
    }
}
