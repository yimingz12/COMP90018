package com.group59.studentCourseHelper.data.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.group59.studentCourseHelper.R;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<Search> {

    static class ViewHolder {
        TextView title, content;

        public ViewHolder(View convertView) {
            title = convertView.findViewById(R.id.question_title);
            content = convertView.findViewById(R.id.question_content);
        }

    }

    private int resourceId;

    public SearchAdapter(Context context, int resource, List<Search> search){
        super(context, resource, search);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Search search = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(parent.getContext()).inflate(resourceId,null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.title.setText(search.getTitle());
        viewHolder.content.setText(search.getContent());
        return  view;
    }
}
