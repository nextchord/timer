package com.example.timer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private RecyclerView recyclerview;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing;
    private ConstraintLayout clFirst;
    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvTime;
    private TextView tvDay;
    private TextView tvHour;
    private TextView tvMinute;
    private TextView tvSecond;

    private MyAdapter mAdapter;
    private List<SaveBean> mList = new ArrayList<>();
    private int mIndex = 0;
    private long mTime;
    private static final int FLING_MIN_DISTANCE = 50;
    private static final int FLING_MIN_VELOCITY = 0;
    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setTimes();
            mHander.sendMessageDelayed(mHander.obtainMessage(), 1000);
        }
    };
    private GestureDetector mGestureDetector;
    private int mRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        recyclerview = findViewById(R.id.recyclerview);
        collapsing = findViewById(R.id.collapsing);
        clFirst = findViewById(R.id.cl_first);
        toolbar = findViewById(R.id.toolbar);
        ivImage = findViewById(R.id.iv_image);
        tvTitle = findViewById(R.id.tv_title);
        tvTime = findViewById(R.id.tv_time);
        tvDay = findViewById(R.id.tv_day);
        tvHour = findViewById(R.id.tv_hour);
        tvMinute = findViewById(R.id.tv_minute);
        tvSecond = findViewById(R.id.tv_second);

        recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mAdapter = new MyAdapter(MainActivity.this);
        recyclerview.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new OnItemClickListener<SaveBean>() {
            @Override
            public void OnItemClick(int position, SaveBean saveBean, View view) {
                startActivityForResult(new Intent(MainActivity.this, DetailActivity.class)
                                .putExtra("Bean", saveBean)
                        , 100);
            }
        });

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, AddOrUpdateActivity.class), 100);
            }
        });

        initData();

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, DetailActivity.class)
                                .putExtra("Bean", mList.get(mIndex))
                        , 100);
            }
        });

        mGestureDetector = new GestureDetector(this, myGestureListener);
        ivImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mGestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (100 == requestCode && RESULT_OK == resultCode) {
            initData();
        }
    }

    private void initData() {
        mList.clear();
        Map<String, ?> all = SpUtil.getAll(MainActivity.this);
        if (null != all) {
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                SaveBean saveBean = JsonUtil.toObject(entry.getValue().toString(), SaveBean.class);
                mList.add(saveBean);
            }
        }

        if (mList.size() == 0) {
            clFirst.setVisibility(View.GONE);
            return;
        }else{
            clFirst.setVisibility(View.VISIBLE);
        }
        Collections.sort(mList);

        setTop();
        mAdapter.setData(mList);
    }

    private void setTop() {
        SaveBean bean = mList.get(mIndex);
        switch (bean.getImage()) {
            case 2:
                ivImage.setImageResource(R.mipmap.image2);
                break;
            case 3:
                ivImage.setImageResource(R.mipmap.image3);
                break;
            default:
                ivImage.setImageResource(R.mipmap.image1);
                break;
        }
        tvTitle.setText(bean.getTitle());
        mTime = bean.getTime();
        mRepeat = bean.getRepeat();
        tvTime.setText(DateUtil.getTimeStringByMills(mTime));
        mHander.sendMessageDelayed(mHander.obtainMessage(), 1000);
        setTimes();
    }

    private void setTimes() {
        if (DateUtil.getDay1(System.currentTimeMillis(), mTime) < 0){
            if (mRepeat == 1){
                mTime = DateUtil.getNextWeek(mTime);
            }else if (mRepeat == 2){
                mTime = DateUtil.getNextMonth(mTime);
            }else if (mRepeat == 3){
                mTime = DateUtil.getNextYear(mTime);
            }
            tvTime.setText(DateUtil.getTimeStringByMills(mTime));
        }
        tvDay.setText(String.valueOf(DateUtil.getDay(System.currentTimeMillis(), mTime)));
        tvHour.setText(String.valueOf(DateUtil.getHour(System.currentTimeMillis(), mTime)));
        tvMinute.setText(String.valueOf(DateUtil.getMin(System.currentTimeMillis(), mTime)));
        tvSecond.setText(String.valueOf(DateUtil.getSecond(System.currentTimeMillis(), mTime)));
    }

    GestureDetector.SimpleOnGestureListener myGestureListener = new GestureDetector.SimpleOnGestureListener() {
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float x = e1.getX() - e2.getX();
            float x2 = e2.getX() - e1.getX();
            if (x > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // 向左手势
                mIndex++;
                if (mIndex == mList.size()) {
                    mIndex = 0;
                }
                setTop();
                return true;
            } else if (x2 > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // 向右手势
                mIndex--;
                if (mIndex < 0) {
                    mIndex = mList.size() - 1;
                }
                setTop();
                return true;
            }

            return false;
        }

        ;
    };
}
