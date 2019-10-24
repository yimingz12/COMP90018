package com.group59.studentCourseHelper.data.ui.details;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group59.studentCourseHelper.R;
import com.group59.studentCourseHelper.data.ui.post.Question;

public class DetailsActivity extends AppCompatActivity implements SensorEventListener {
    //TextView answer;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    Button ans_submit;
    Button ans_back;
    TextView title, name, des;
    DatabaseReference myRef = database.getReference("questions");
    private String TAG = getClass().getName();
    private SensorManager sm;
    private Sensor mSensorOrientation;
    private final Handler mHandler = new Handler();
    private double x;
    private double y;
    private double z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ans_back=(Button)findViewById(R.id.back_btn);
        ans_submit=(Button)findViewById(R.id.ans_btn);
        title = findViewById(R.id.detail_title);
        name = findViewById(R.id.detail_name);
        des = findViewById(R.id.detail_des);
        ans_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Intent intent = getIntent();
        final String a = intent.getStringExtra("qid");
        showView(a);
        Log.i(TAG, "onShow::"+a);
        ans_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.ANSWER");
                intent.putExtra("qid",a);
                startActivity(intent);
            }
        });
        x=0;
        y=0;
        z=0;
        iniHolder();
    }

    private void showView(String a) {
        myRef.child("8CMYneZckXOzjUMWQRKEoxDrmTF3").child(a).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.child("tag").getValue().toString());
                title.setText(dataSnapshot.child("quesionTitle").getValue().toString());
                des.setText(dataSnapshot.child("questionDesc").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x=(float) (Math.round(event.values[0] * 100)) / 100;
        y=(float) (Math.round(event.values[1] * 100)) / 100;
        z=(float) (Math.round(event.values[2] * 100)) / 100;
        Holder.scroll.post(ScrollRunnable);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    private void iniHolder() {
        Holder.scroll = (ScrollView) findViewById(R.id.sv_scroll);
        Holder.mlayout = (LinearLayout) findViewById(R.id.l_test);
        Holder.tv_null = (TextView) findViewById(R.id.detail_des);
        Holder.tv_null.setHeight(getWindowManager().getDefaultDisplay().getHeight() - 50);


    }
    private Runnable ScrollRunnable = new Runnable() {
        @Override
        public void run() {
            int off = Holder.mlayout.getMeasuredHeight()
                    - Holder.scroll.getHeight();// 判断高度,ScrollView的最大高度，就是屏幕的高度
            if ((off > 0) && (x<1) && (y<10) && (z<2) && (x>-1) && (y>0) && (z>-5)) {
                Holder.scroll.scrollBy(0, 2);
                if (Holder.scroll.getScrollY() == off) {
                    Thread.currentThread().interrupt();
                } else {
                    mHandler.postDelayed(this, 10);
                }
            }
            if ((off > 0) && (x<1) && (y<4) && (z<10) && (x>-1) && (y>0) && (z>8)) {
                Holder.scroll.scrollBy(0, -2);
                if (Holder.scroll.getScrollY() == off) {
                    Thread.currentThread().interrupt();
                } else {
                    mHandler.postDelayed(this, 10);
                }
            }
        }
    };
    static class Holder {
        static ScrollView scroll;
        static LinearLayout mlayout;
        static TextView tv_null;
    }
}