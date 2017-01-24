package com.example.yassarchohan.to_do_app;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Yassar chohan on 1/12/2017.
 */
public class Custom_Adapter extends ArrayAdapter<Gettermethods>  {


    public Custom_Adapter(Context context, int resource, List<Gettermethods> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.activity_custom_view,parent,false);
        }

        TextView txt = (TextView) convertView.findViewById(R.id.getname);
        TextView txt2 = (TextView) convertView.findViewById(R.id.getmsg);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.getchecking);



        Gettermethods message = getItem(position);

        txt.setText(message.getName());
        txt2.setText(message.getSecretcode());


        return convertView;
    }
}
