package java.devcolibri.itvdn.com.socialmassager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.devcolibri.itvdn.com.socialmassager.R;
import java.devcolibri.itvdn.com.socialmassager.pojo.Contact;
import java.util.ArrayList;
import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private List<Contact> contacts;
    private LayoutInflater inflater;

    public ContactAdapter(LayoutInflater inflater) {
        this.contacts = new ArrayList<>();
        this.inflater = inflater;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.contact_item, parent, false);
        }

        TextView name = v.findViewById(R.id.contact);
        TextView phone = v.findViewById(R.id.phone);

        Contact contact = (Contact) getItem(position);

        name.setText(contact.getName());
        phone.setText(contact.getPhone());

        return v;
    }

}
