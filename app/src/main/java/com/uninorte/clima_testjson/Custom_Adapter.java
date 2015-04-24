package com.uninorte.clima_testjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ricardo on 24/04/2015.
 */
public class Custom_Adapter extends BaseAdapter  {
            private final Context context;
            private List<Data> list;

    public Custom_Adapter(Context context, List<Data> list) {
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
        Data input = list.get(position);

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.main_row,null);
        }

        TextView d1 = (TextView) convertView.findViewById(R.id.Day);
        TextView d2 = (TextView) convertView.findViewById(R.id.min);
        TextView d3 = (TextView) convertView.findViewById(R.id.max);
        TextView d4 = (TextView) convertView.findViewById(R.id.Night);
        TextView d5 = (TextView) convertView.findViewById(R.id.Evening);
        TextView d6 = (TextView) convertView.findViewById(R.id.Morning);

        d1.setText(String.valueOf(input.getDia()));
        d2.setText(String.valueOf(input.getMin()));
        d3.setText(String.valueOf(input.getMax()));
        d4.setText(String.valueOf(input.getNoche()));
        d5.setText(String.valueOf(input.getTarde()));
        d6.setText(String.valueOf(input.getMañana()));

        convertView.setTag(input);


        return convertView;
    }
}
