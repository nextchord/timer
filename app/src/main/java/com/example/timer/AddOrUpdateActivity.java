package com.example.timer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
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

public class AddOrUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBack;
    private ImageView ivConfirm;
    private ImageView ivImage;
    private EditText etTitle;
    private EditText etRemark;
    private TextView tvDate;
    private TextView tvRepeat;
    private int mYear;
    private int mMonthOfYear;
    private int mDayOfMonth;
    private Calendar mCalendar;

    private SaveBean mBean;
    private int mImage = 1;
    private int mRepeat;
    private AlertDialog mRepeatDialog;
    private AlertDialog mSelectPictureialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_update);

        ivBack = findViewById(R.id.iv_back);
        ivConfirm = findViewById(R.id.iv_confirm);
        ivImage = findViewById(R.id.iv_image);
        etTitle = findViewById(R.id.et_title);
        etRemark = findViewById(R.id.et_remark);
        tvDate = findViewById(R.id.tv_date1);
        tvRepeat = findViewById(R.id.tv_repeat1);

        ivBack.setOnClickListener(this);
        ivConfirm.setOnClickListener(this);
        findViewById(R.id.tv_date).setOnClickListener(this);
        findViewById(R.id.tv_date1).setOnClickListener(this);
        findViewById(R.id.iv_date).setOnClickListener(this);
        findViewById(R.id.tv_repeat).setOnClickListener(this);
        findViewById(R.id.tv_repeat1).setOnClickListener(this);
        findViewById(R.id.iv_repeat).setOnClickListener(this);
        findViewById(R.id.tv_picture).setOnClickListener(this);
        findViewById(R.id.iv_picture).setOnClickListener(this);

        mCalendar = Calendar.getInstance();

        mBean = getIntent().getParcelableExtra("Bean");
        if (null == mBean) mBean = new SaveBean();
        else {
            etTitle.setText(mBean.getTitle());
            etRemark.setText(mBean.getRemark());
            tvDate.setText(DateUtil.getTimeStringByMills(mBean.getTime()));
            mCalendar.setTimeInMillis(mBean.getTime());
            mRepeat = mBean.getRepeat();
            switch (mRepeat) {
                case 1:
                    tvRepeat.setText("每周");
                    break;
                case 2:
                    tvRepeat.setText("每月");
                    break;
                case 3:
                    tvRepeat.setText("每年");
                    break;
                default:
                    tvRepeat.setText("无");
                    break;
            }
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
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_confirm:
                String title = etTitle.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show();
                    return;
                }
                mBean.setTitle(title);
                mBean.setRemark(etRemark.getText().toString().trim());
                mBean.setImage(mImage);
                mBean.setRepeat(mRepeat);
                mBean.setTime(mCalendar.getTimeInMillis());
                mBean.setCreateTime(System.currentTimeMillis());
                SpUtil.putString(AddOrUpdateActivity.this, title, JsonUtil.toJSONString(mBean));
                setResult(RESULT_OK);
                finish();
                break;
            case R.id.tv_date:
            case R.id.tv_date1:
            case R.id.iv_date:

                new DatePickerDialog(AddOrUpdateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mYear = year;
                        mMonthOfYear = monthOfYear;
                        mDayOfMonth = dayOfMonth;
                        new TimePickerDialog(AddOrUpdateActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        mCalendar.set(mYear, mMonthOfYear, mDayOfMonth, hourOfDay, minute, 0);
                                        tvDate.setText(DateUtil.getTimeString(mCalendar.getTime()));
                                    }
                                }
                                , mCalendar.get(Calendar.HOUR_OF_DAY)
                                , mCalendar.get(Calendar.MINUTE)
                                , true).show();
                    }
                }
                        , mCalendar.get(Calendar.YEAR)
                        , mCalendar.get(Calendar.MONTH)
                        , mCalendar.get(Calendar.DAY_OF_MONTH)).show();
                break;
            case R.id.tv_repeat:
            case R.id.tv_repeat1:
            case R.id.iv_repeat:
                showRepeatDialog();
                break;
            case R.id.tv_picture:
            case R.id.iv_picture:
                showPictureSelectDialog();
                break;
        }
    }

    private void showPictureSelectDialog() {
        View view = LayoutInflater.from(AddOrUpdateActivity.this).inflate(R.layout.dialog_select_picture, null);
        view.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPictureialog.dismiss();
                mImage = 1;
                ivImage.setImageResource(R.mipmap.image1);
            }
        });
        view.findViewById(R.id.iv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPictureialog.dismiss();
                mImage = 2;
                ivImage.setImageResource(R.mipmap.image2);
            }
        });
        view.findViewById(R.id.iv3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectPictureialog.dismiss();
                mImage = 3;
                ivImage.setImageResource(R.mipmap.image3);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(AddOrUpdateActivity.this);
        mSelectPictureialog = builder.create();
        mSelectPictureialog.setView(view);
        mSelectPictureialog.show();
    }

    private void showRepeatDialog() {
        View view = LayoutInflater.from(AddOrUpdateActivity.this).inflate(R.layout.dialog_repeat, null);
        view.findViewById(R.id.tv_week).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepeatDialog.dismiss();
                mRepeat = 1;
                tvRepeat.setText("每周");
            }
        });
        view.findViewById(R.id.tv_month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepeatDialog.dismiss();
                mRepeat = 2;
                tvRepeat.setText("每月");
            }
        });
        view.findViewById(R.id.tv_year).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRepeatDialog.dismiss();
                mRepeat = 3;
                tvRepeat.setText("每年");
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(AddOrUpdateActivity.this);
        mRepeatDialog = builder.create();
        mRepeatDialog.setView(view);
        mRepeatDialog.show();
    }
}
