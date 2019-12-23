package com.example.timer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private Context mContext;
    private List<SaveBean> mData;
    private OnItemClickListener<SaveBean> mListener;

    public MyAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<SaveBean> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<SaveBean> listener){
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (null != mData && mData.size() > position) {
            final SaveBean bean = mData.get(position);
            int image = bean.getImage();
            switch (image) {
                case 2:
                    holder.iv_image.setImageResource(R.mipmap.image2);
                    break;
                case 3:
                    holder.iv_image.setImageResource(R.mipmap.image3);
                    break;
                default:
                    holder.iv_image.setImageResource(R.mipmap.image1);
                    break;
            }
            long time = bean.getTime();
            int repeat = bean.getRepeat();
            if (System.currentTimeMillis() >= time && 0 == repeat) {
                holder.tv_1.setText("已经");
            }else {
                holder.tv_1.setText("还有");
            }
            if (DateUtil.getDay1(System.currentTimeMillis(), time) < 0){
                if (repeat == 1){
                    time = DateUtil.getNextWeek(time);
                }else if (repeat == 2){
                    time = DateUtil.getNextMonth(time);
                }else if (repeat == 3){
                    time = DateUtil.getNextYear(time);
                }
            }
            holder.tv_day.setText(DateUtil.getDay(System.currentTimeMillis(), time)+"天");
            holder.tv_time.setText(DateUtil.getTimeStringByMills(time));
            holder.tvRemark.setText(bean.getRemark());
            holder.tv_title.setText(bean.getTitle());
            holder.iv_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) mListener.OnItemClick(position, bean, v);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return null == mData ? 0 : mData.size();
    }

    public void updateItem(int position, SaveBean bean) {
        if (null != mData && mData.size() > position) {
            mData.set(position, bean);
            notifyItemChanged(position);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_image;
        private TextView tv_1;
        private TextView tv_day;
        private TextView tv_title;
        private TextView tv_time;
        private TextView tvRemark;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_image = itemView.findViewById(R.id.iv_image);
            tv_1 = itemView.findViewById(R.id.tv_1);
            tv_day = itemView.findViewById(R.id.tv_day);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            tvRemark = itemView.findViewById(R.id.tv_remark);
        }
    }
}
