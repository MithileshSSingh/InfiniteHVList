package com.example.hvlist.mvp.screen_home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hvlist.R;
import com.example.hvlist.di.RepositoryInjector;
import com.example.hvlist.mvp.BaseFragment;
import com.example.hvlist.mvp.beans.BeanDateDetail;

import java.util.ArrayList;

/**
 * Created by mithilesh on 9/11/16.
 */
public class HomeFragment extends BaseFragment implements HomeContract.View, HomeContract.OnItemSelectedListener, View.OnClickListener {
    public static final String TAG = HomeFragment.class.getSimpleName();

    private HomeContract.Presenter mPresenter;

    public HomeFragment() {

    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new HomePresenter(
                RepositoryInjector.provideRepository(mActivity.getApplicationContext()),
                this
        );
        init();
    }

    @Override
    protected void init() {

        initView();
        initMembers();
        initListeners();
        initData();

    }

    private ArrayList<BeanDateDetail> mListData = new ArrayList<>();

    private DateAdapter mAdapterDate;
    private DateDetailAdapter mAdapterDateDetail;

    private RecyclerView rvDate;
    private RecyclerView rvDateDetail;

    private LinearLayoutManager mLayoutManagerHorizontalRV;
    private LinearLayoutManager mLayoutManagerVerticalRV;

    private boolean mIsLoadingData;

    @Override
    protected void initView() {
        rvDate = (RecyclerView) mView.findViewById(R.id.rvDates);
        rvDateDetail = (RecyclerView) mView.findViewById(R.id.rvDateDetail);
    }

    @Override
    protected void initMembers() {
        /**
         * Initializing Date Adapter
         */
        mAdapterDate = new DateAdapter(mActivity, mListData, this);
        mLayoutManagerHorizontalRV = new LinearLayoutManager(
                mActivity.getApplicationContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();

        rvDate.setHasFixedSize(true);
        rvDate.setLayoutManager(mLayoutManagerHorizontalRV);
        rvDate.setItemAnimator(itemAnimator);
        rvDate.setAdapter(mAdapterDate);

        mAdapterDate.notifyDataSetChanged();

        /**
         * Initializing DateDetail Adapter
         */


        mAdapterDateDetail = new DateDetailAdapter(mActivity, new ArrayList<String>(), this);
        mLayoutManagerVerticalRV = new LinearLayoutManager(
                mActivity.getApplicationContext()
        );
        RecyclerView.ItemAnimator itemAnimatorVertical = new DefaultItemAnimator();

        rvDateDetail.setHasFixedSize(true);
        rvDateDetail.setLayoutManager(mLayoutManagerVerticalRV);
        rvDateDetail.setItemAnimator(itemAnimatorVertical);
        rvDateDetail.setAdapter(mAdapterDateDetail);

        mAdapterDateDetail.notifyDataSetChanged();

    }

    @Override
    protected void initListeners() {
        rvDate.addOnScrollListener(mHorizontalScrollRecyclerView);
    }

    @Override
    protected void initData() {
        loadData(0);
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(int position) {

        ArrayList<String> listDateDetail = mListData.get(position).getDetail();
        mAdapterDateDetail.setListData(listDateDetail);
        mAdapterDateDetail.notifyDataSetChanged();

        for (int i = 0; i < mListData.size(); i++) {
            if (i == position) {
                mListData.get(i).setSelected(true);
            } else {
                mListData.get(i).setSelected(false);
            }
        }

        mAdapterDate.notifyDataSetChanged();
    }

    private void loadData(final int size) {

        mIsLoadingData = true;

        mPresenter.loadData(size, new LoadDataCallback() {
            @Override
            public void onSuccess(ArrayList<BeanDateDetail> data) {
                mListData.addAll(data);
                mAdapterDate.setListData(mListData);
//                mAdapterDate.notifyDataSetChanged();
                mAdapterDateDetail.notifyDataSetChanged();

                mIsLoadingData = false;

                /**
                 * Checking and setting default Selection
                 */

                if (size == 0 && mListData != null && mListData.size() > 0) {
                    onItemSelected(0);
                }
            }

            @Override
            public void failed(int errorCode, String msgError) {

                mIsLoadingData = false;
                Toast.makeText(mActivity, msgError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    RecyclerView.OnScrollListener mHorizontalScrollRecyclerView = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            if (mIsLoadingData)
                return;

            int visibleItemCount = mLayoutManagerHorizontalRV.findLastCompletelyVisibleItemPosition();
            if (visibleItemCount == (mListData.size() - 5)) {
                loadData(mListData.size());
            }
        }
    };
}
