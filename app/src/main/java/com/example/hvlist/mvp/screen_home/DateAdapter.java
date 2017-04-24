package com.example.hvlist.mvp.screen_home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hvlist.R;
import com.example.hvlist.mvp.BaseViewHolder;
import com.example.hvlist.mvp.beans.BeanDateDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mithilesh on 9/4/16.
 */
public class DateAdapter extends RecyclerView.Adapter<DateViewHolder> {


    private Context mContext;
    private List<BeanDateDetail> mListData = Collections.emptyList();
    private LayoutInflater mInflater;

    private HomeContract.OnItemSelectedListener mListener;

    public DateAdapter(Context context,
                       ArrayList<BeanDateDetail> listPosts,
                       HomeContract.OnItemSelectedListener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listPosts;
        mListener = listener;

    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_date, parent, false);
        return new DateViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(DateViewHolder holder, int position) {
        BeanDateDetail data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<BeanDateDetail> listData) {
        mListData = listData;
        notifyDataSetChanged();
    }
}


class DateViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<BeanDateDetail>, View.OnClickListener {

    private Context mContext;
    private View mView;
    private BeanDateDetail mData;
    private int mPosition;

    private HomeContract.OnItemSelectedListener mListener;

    public DateViewHolder(Context context, View itemView,
                          HomeContract.OnItemSelectedListener listener) {
        super(itemView);
        mContext = context;
        mView = itemView;
        mListener = listener;
        init();
    }

    public DateViewHolder(View itemView) {
        super(itemView);
    }

    private void init() {
        initView();
        initListener();
    }

    private TextView tvDate;

    private void initView() {
        tvDate = (TextView) mView.findViewById(R.id.tvDate);
    }

    private void initListener() {
        tvDate.setOnClickListener(this);
    }


    @Override
    public void apply(BeanDateDetail data, int position) {
        mData = data;
        mPosition = position;

        tvDate.setText(mData.getDate());

        if (data.isSelected()) {
            tvDate.setSelected(true);
        } else {
            tvDate.setSelected(false);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tvDate:

                mData.setSelected(true);
                mListener.onItemSelected(mPosition);

                break;
        }
    }

}
