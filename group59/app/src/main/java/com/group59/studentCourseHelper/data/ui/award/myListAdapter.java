package com.group59.studentCourseHelper.data.ui.award;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.group59.studentCourseHelper.R;

import java.util.List;

public class myListAdapter extends ArrayAdapter<Question> {
    private String TAG = getClass().getName();
    static class ViewHolder {
        TextView title, des,tag;

        public ViewHolder(View convertView) {
            title = convertView.findViewById(R.id.myQ_title);
            des = convertView.findViewById(R.id.myQ_des);
            tag= convertView.findViewById(R.id.myQ_tag);
        }

    }

    private int resourceId;

    public myListAdapter(Context context, int resource, List<Question> myLists){
        super(context, resource, myLists);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Question myLists = getItem(position);
        View view;
        myListAdapter.ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText("Title: "+myLists.getqTitle());
        viewHolder.tag.setText("Tag: "+myLists.getTag());
        viewHolder.des.setText("Content: "+myLists.getqDes());
        return  view;
    }

}
