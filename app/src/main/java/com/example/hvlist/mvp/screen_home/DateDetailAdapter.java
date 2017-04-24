package com.example.hvlist.mvp.screen_home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hvlist.R;
import com.example.hvlist.mvp.BaseViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mithilesh on 9/4/16.
 */
public class DateDetailAdapter extends RecyclerView.Adapter<DateDetailViewHolder> {


    private Context mContext;
    private List<String> mListData = Collections.emptyList();
    private LayoutInflater mInflater;

    private HomeContract.OnItemSelectedListener mListener;

    public DateDetailAdapter(Context context,
                             ArrayList<String> listPosts,
                             HomeContract.OnItemSelectedListener listener) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mListData = listPosts;
        mListener = listener;

    }

    @Override
    public DateDetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = mInflater.inflate(R.layout.item_date_detail, parent, false);
        return new DateDetailViewHolder(mContext, convertView, mListener);
    }

    @Override
    public void onBindViewHolder(DateDetailViewHolder holder, int position) {
        String data = mListData.get(position);
        holder.apply(data, position);
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public void setListData(ArrayList<String> data) {
        mListData = data;
    }
}


class DateDetailViewHolder extends RecyclerView.ViewHolder implements BaseViewHolder<String>, View.OnClickListener {

    private Context mContext;
    private View mView;
    private String mData;
    private int mPosition;

    private HomeContract.OnItemSelectedListener mListener;

    public DateDetailViewHolder(Context context, View itemView,
                                HomeContract.OnItemSelectedListener listener) {
        super(itemView);
        mContext = context;
        mView = itemView;
        mListener = listener;
        init();
    }

    public DateDetailViewHolder(View itemView) {
        super(itemView);
    }

    private void init() {
        initView();
        initListener();
    }

    private TextView tvDateDetail;

    private void initView() {
        tvDateDetail = (TextView) mView.findViewById(R.id.tvDateDetail);

    }

    private void initListener() {
    }


    @Override
    public void apply(String data, int position) {
        mData = data;
        mPosition = position;

        tvDateDetail.setText(mData);
    }


    @Override
    public void onClick(View view) {
    }
}
