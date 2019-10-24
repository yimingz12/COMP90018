package com.group59.studentCourseHelper.data.ui.Account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.group59.studentCourseHelper.R;
import com.group59.studentCourseHelper.data.ui.Account.questionList;

import java.util.List;


public class myListAdapter extends ArrayAdapter<questionList> {

        static class ViewHolder {
            TextView title, content;

            public ViewHolder(View convertView) {
                title = convertView.findViewById(R.id.question_title);
                content = convertView.findViewById(R.id.question_content);
            }

        }

        private int resourceId;

        public myListAdapter(Context context, int resource, List<questionList> myList){
            super(context, resource, myList);
            this.resourceId = resource;
        }

        @Override
        public View getView(int position, View convertView,  ViewGroup parent) {
            questionList questionList = getItem(position);
            View view;
            com.group59.studentCourseHelper.data.ui.search.SearchAdapter.ViewHolder viewHolder;
            if(convertView == null){
                view = LayoutInflater.from(parent.getContext()).inflate(resourceId,null);
                viewHolder = new com.group59.studentCourseHelper.data.ui.search.SearchAdapter.ViewHolder(view);
                view.setTag(viewHolder);
            }else{
                view = convertView;
                viewHolder = (com.group59.studentCourseHelper.data.ui.search.SearchAdapter.ViewHolder) view.getTag();
            }
            viewHolder.title.setText(questionList.getTitle());
            viewHolder.content.setText(questionList.getContent());
            return  view;
        }
    }

