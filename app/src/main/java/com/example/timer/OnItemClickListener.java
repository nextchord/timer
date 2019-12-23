package com.example.timer;

import android.view.View;

public interface OnItemClickListener<T>{

    void OnItemClick(int position, T t, View view);
}