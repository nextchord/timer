package com.example.timer;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveBean implements Comparable, Parcelable {
    private long time;
    private int repeat;
    private int image;
    private String title;
    private String remark;
    private long createTime;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof  SaveBean) {
            SaveBean comBean = (SaveBean) o;
            if (this.createTime > comBean.getCreateTime()) return -1;
            if (this.createTime < comBean.getCreateTime()) return 1;
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.time);
        dest.writeInt(this.repeat);
        dest.writeInt(this.image);
        dest.writeString(this.title);
        dest.writeString(this.remark);
        dest.writeLong(this.createTime);
    }

    public SaveBean() {
    }

    protected SaveBean(Parcel in) {
        this.time = in.readLong();
        this.repeat = in.readInt();
        this.image = in.readInt();
        this.title = in.readString();
        this.remark = in.readString();
        this.createTime = in.readLong();
    }

    public static final Parcelable.Creator<SaveBean> CREATOR = new Parcelable.Creator<SaveBean>() {
        @Override
        public SaveBean createFromParcel(Parcel source) {
            return new SaveBean(source);
        }

        @Override
        public SaveBean[] newArray(int size) {
            return new SaveBean[size];
        }
    };
}
