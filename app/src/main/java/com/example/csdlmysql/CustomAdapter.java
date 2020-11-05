package com.example.csdlmysql;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resoure;
    private List<Student> lsStudent;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resource;
        this.lsStudent = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHoler viewHoler;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_student,parent,false);
            viewHoler = new ViewHoler();
            viewHoler.tvId = convertView.findViewById(R.id.tv_id);
            viewHoler.tvName = convertView.findViewById(R.id.tv_name);
            viewHoler.tvLop = convertView.findViewById(R.id.tv_lop);
            viewHoler.tvPhone = convertView.findViewById(R.id.tv_phone);
            viewHoler.tvEmail = convertView.findViewById(R.id.tv_email);
            convertView.setTag(viewHoler);
        }else {
            viewHoler = (ViewHoler) convertView.getTag();
        }
        Student st =lsStudent.get(position);
        viewHoler.tvId.setText(String.valueOf(st.getId()));
        viewHoler.tvName.setText(st.getName());
        viewHoler.tvLop.setText(st.getlop());
        viewHoler.tvPhone.setText(st.getPhone());
        viewHoler.tvEmail.setText(st.getemail());

        return convertView;
    }
    public class ViewHoler{
        private TextView tvId;
        private TextView tvName;
        private TextView tvLop;
        private TextView tvPhone;
        private TextView tvEmail;

    }
}
