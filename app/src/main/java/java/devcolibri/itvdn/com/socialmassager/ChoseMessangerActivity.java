package java.devcolibri.itvdn.com.socialmassager;

import android.Manifest;
import android.app.ActivityManager;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.devcolibri.itvdn.com.socialmassager.adapter.ResolveInfoAdapter;
import java.util.Calendar;
import java.util.List;

public class ChoseMessangerActivity extends AppCompatActivity {

    private ListView listView;
    private ResolveInfoAdapter adapter;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);

        listView = findViewById(R.id.list_view);


        final String number = getIntent().getStringExtra("number");
        final String message = getIntent().getStringExtra("message");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 TextView packageTxt = view.findViewById(R.id.package_name);
                 String packageName = packageTxt.getText().toString();
                Log.d("package_app", packageName);

              //  if (packageName.contains("mms")) {
                    String toSms = "smsto:" + number;
                    Intent sms = new Intent(Intent.ACTION_SENDTO, Uri.parse(toSms));
                    sms.putExtra("sms_body", message);
                    sms.setPackage(packageName);
                    startActivity(sms);
//                } else {
//                    Intent email = new Intent(Intent.ACTION_SEND);
//
//                    email.setType("text/plain");
//                    // Log.d("data", data + " Intent");
//                    email.putExtra(Intent.EXTRA_SUBJECT, message);
//                    email.putExtra(Intent.EXTRA_TEXT, message);
//
//                    email.setPackage(packageName);
//                    startActivity(email);
//                }
//           Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
//        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
//        smsIntent.setPackage(packageName);
//        smsIntent.setData(Uri.parse(toSms));
//        smsIntent.putExtra("address", number);
//        smsIntent.putExtra("sms_body", message);
//        startActivity(smsIntent);
            }
        });

        Log.d("package_app", number + " " + message + " ");

        ResolveInfoAsyncTask task = new ResolveInfoAsyncTask();
        task.execute();

//        Intent sendIntent = new Intent(Intent.ACTION_SEND);
//        sendIntent.setType("text/plain");
        //sendIntent.putExtra("sms_body", "mes");

//        Intent smsIntent = new Intent(Intent.ACTION_SEND);
//        smsIntent.addCategory(Intent.CATEGORY_DEFAULT);
//
//        smsIntent.setData(Uri.parse("sms:+38(095)0750703"));
//        smsIntent.putExtra("address", "+1001002003");
//        smsIntent.putExtra("sms_body", "body  text");

//        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(sendIntent, 0);
//
//        for (ResolveInfo info : resolveInfos) {
//            Log.d("package_app", info.loadLabel(pm) + " " + info.activityInfo.packageName + " ");
//        }
//        List<ApplicationInfo> allApplications = pm.getInstalledApplications(PackageManager.GET_META_DATA);
//
//
//        for (ApplicationInfo info : allApplications) {
//
//
//            if (pm.checkPermission(Manifest.permission.INTERNET, info.packageName) == PackageManager.PERMISSION_GRANTED) {
//                Log.d("package_app", info.loadLabel(pm) + " " + info.packageName + " ");
//            }
//        }
//
     //   StartEmailIntent(this, "fdsgfddf");

//        ActivityManager activityManager = (ActivityManager) this.getSystemService( ACTIVITY_SERVICE );
//        List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
//
//
//        for (int i = 0; i < procInfos.size(); i++) {
//            Log.d("package", procInfos.get(i).processName + " ");
//        }

    }

    public static void StartEmailIntent (Context cx, String EmailAddress){
        Intent email = new Intent(Intent.ACTION_SEND);

        email.setType("text/plain");
        // Log.d("data", data + " Intent");
        email.putExtra(Intent.EXTRA_SUBJECT, "Send Default Massage");
        email.putExtra(Intent.EXTRA_TEXT, EmailAddress);

        email.setPackage("org.telegram.messenger");
        cx.startActivity(email);

     //   cx.startActivity(Intent.createChooser(email, cx.getString(R.string.app_name)));
    }


    private class ResolveInfoAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            sendIntent.setType("text/plain");
            PackageManager pm = getPackageManager();
            List<ResolveInfo> resolveInfos = pm.queryIntentActivities(sendIntent, 0);
            LayoutInflater layoutInflater = getLayoutInflater();
            adapter = new ResolveInfoAdapter(resolveInfos, layoutInflater, pm);
            return "All apps loading successful";
        }

        @Override
        protected void onPostExecute(String s) {
            listView.setAdapter(adapter);
            Toast.makeText(ChoseMessangerActivity.this, s, Toast.LENGTH_SHORT).show();
        }

    }

}
