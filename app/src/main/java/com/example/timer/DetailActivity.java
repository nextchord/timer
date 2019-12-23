package com.example.timer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private ImageView ivDelete;
    private ImageView ivEdit;
    private ImageView ivImage;
    private TextView tvTitle;
    private TextView tvTime;
    private TextView tvDay;
    private TextView tvHour;
    private TextView tvMinute;
    private TextView tvSecond;
    private Calendar mCalendar;

    private SaveBean mBean;
    private long mTime;
    private int mImage = 1;
    private Handler mHander = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setTimes();
            mHander.sendMessageDelayed(mHander.obtainMessage(), 1000);
        }
    };
    private int mRepeat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivBack = findViewById(R.id.iv_back);
        ivDelete = findViewById(R.id.iv_delete);
        ivEdit = findViewById(R.id.iv_edit);
        ivImage = findViewById(R.id.iv_image);
        tvTitle = findViewById(R.id.tv_title);
        tvTime = findViewById(R.id.tv_time);
        tvDay = findViewById(R.id.tv_day);
        tvHour = findViewById(R.id.tv_hour);
        tvMinute = findViewById(R.id.tv_minute);
        tvSecond = findViewById(R.id.tv_second);

        ivBack.setOnClickListener(this);
        ivDelete.setOnClickListener(this);
        ivEdit.setOnClickListener(this);

        mCalendar = Calendar.getInstance();

        mBean = getIntent().getParcelableExtra("Bean");
        if (null == mBean) return;
        else {
            resetView();
        }
    }

    private void resetView() {
        mTime = mBean.getTime();
        mRepeat = mBean.getRepeat();
        tvTitle.setText(mBean.getTitle());
        tvTime.setText(DateUtil.getTimeStringByMills(mTime));
        mCalendar.setTimeInMillis(mTime);
        mImage = mBean.getImage();
        switch (mImage) {
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
        }
        tvDay.setText(String.valueOf(DateUtil.getDay(System.currentTimeMillis(), mTime)));
        tvHour.setText(String.valueOf(DateUtil.getHour(System.currentTimeMillis(), mTime)));
        tvMinute.setText(String.valueOf(DateUtil.getMin(System.currentTimeMillis(), mTime)));
        tvSecond.setText(String.valueOf(DateUtil.getSecond(System.currentTimeMillis(), mTime)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.iv_delete:
                new AlertDialog.Builder(DetailActivity.this)
                        .setMessage("是否删除该倒计时？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SpUtil.remove(DetailActivity.this, mBean.getTitle());
                                setResult(RESULT_OK);
                                finish();
                            }
                        })
                        .setNegativeButton("取消", null)
                        .show();
                break;
            case R.id.iv_edit:
                startActivityForResult(new Intent(DetailActivity.this, AddOrUpdateActivity.class)
                                .putExtra("Bean", mBean)
                        , 100);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (100 == requestCode && RESULT_OK == resultCode) {
            String string = SpUtil.getString(DetailActivity.this, mBean.getTitle(), null);
            if (TextUtils.isEmpty(string)){
                setResult(RESULT_OK);
                finish();
            }
            mBean = JsonUtil.toObject(string, SaveBean.class);
            resetView();
        }
    }
}
