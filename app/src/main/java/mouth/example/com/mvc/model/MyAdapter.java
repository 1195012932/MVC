package mouth.example.com.mvc.model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mouth.example.com.mvc.R;
import mouth.example.com.mvc.entity.User;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<User> list = new ArrayList<>();
    private ViewHolder holder;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item, null);
            holder.name = (TextView) convertView.findViewById(R.id.item_city);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user = list.get(position);
        holder.name.setText(user.getName());

        return convertView;
    }

    static class ViewHolder {
        TextView name;

    }
}