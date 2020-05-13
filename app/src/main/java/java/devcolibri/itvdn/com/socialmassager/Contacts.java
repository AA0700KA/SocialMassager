package java.devcolibri.itvdn.com.socialmassager;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.devcolibri.itvdn.com.socialmassager.adapter.ContactAdapter;
import java.devcolibri.itvdn.com.socialmassager.pojo.Contact;
import java.util.ArrayList;
import java.util.List;


public class Contacts extends MainActivity {

    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout1);

        ListView listView = findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView phoneTxt = view.findViewById(R.id.phone);
                String phone = phoneTxt.getText().toString();
                Intent intent = new Intent(Contacts.this, SendSMS.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
            }

        });
        LayoutInflater inflater = getLayoutInflater();
        adapter = new ContactAdapter(inflater);
        listView.setAdapter(adapter);
        ContactsTask task = new ContactsTask();
        task.execute();
    }



    private class ContactsTask extends AsyncTask<Void, Contact, String> {


        @Override
        protected String doInBackground(Void... voids) {
            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                    null, null, null, null);

            if ((cur != null ? cur.getCount() : 0) > 0) {
                while (cur != null && cur.moveToNext()) {
                    String id = cur.getString(
                            cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME));

                    if (cur.getInt(cur.getColumnIndex(
                            ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        Cursor pCur = cr.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        while (pCur.moveToNext()) {
                            String phoneNo = pCur.getString(pCur.getColumnIndex(
                                    ContactsContract.CommonDataKinds.Phone.NUMBER));
                            Contact contact = new Contact();
                            contact.setName(name);
                            contact.setPhone(phoneNo);
                            publishProgress(contact);
                            Log.i("TAG", "Name: " + name);
                            Log.i("TAG", "Phone Number: " + phoneNo);
                        }
                        pCur.close();
                    }
                }
            }
            if(cur!=null){
                cur.close();
            }
            return "All contacts added succsessfull!";
        }

        @Override
        protected void onProgressUpdate(Contact... values) {
            adapter.addContact(values[0]);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(Contacts.this, s, Toast.LENGTH_SHORT).show();
        }

    }


}
