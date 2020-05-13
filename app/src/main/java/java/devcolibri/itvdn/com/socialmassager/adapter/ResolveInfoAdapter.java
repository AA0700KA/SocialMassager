package java.devcolibri.itvdn.com.socialmassager.adapter;

import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.devcolibri.itvdn.com.socialmassager.R;
import java.devcolibri.itvdn.com.socialmassager.pojo.Contact;
import java.util.ArrayList;
import java.util.List;

public class ResolveInfoAdapter extends BaseAdapter {

    private List<ResolveInfo> resolveInfos;
    private LayoutInflater inflater;
    private PackageManager pm;

    public ResolveInfoAdapter(List<ResolveInfo> resolveInfos, LayoutInflater inflater, PackageManager pm) {
        this.resolveInfos = resolveInfos;
        this.inflater = inflater;
        this.pm = pm;
    }

    @Override
    public int getCount() {
        return resolveInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return resolveInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            v = inflater.inflate(R.layout.app_item, parent, false);
        }

        TextView name = v.findViewById(R.id.app_name);
        TextView packageName = v.findViewById(R.id.package_name);
        ImageView imageView = v.findViewById(R.id.img);

        ResolveInfo info = (ResolveInfo) getItem(position);

        name.setText(info.loadLabel(pm));
        packageName.setText(info.activityInfo.packageName);
        imageView.setImageDrawable(info.loadIcon(pm));

        return v;
    }

}
