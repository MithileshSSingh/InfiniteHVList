package com.example.hvlist.mvp.screen_home;

import com.example.hvlist.data.DataSource;
import com.example.hvlist.data.Repository;
import com.example.hvlist.mvp.beans.BeanDateDetail;

import java.util.ArrayList;

/**
 * Created by mithilesh on 9/11/16.
 */
public class HomePresenter implements HomeContract.Presenter {
    private Repository mRepository;
    private HomeContract.View mView;

    private ArrayList<String> listSubReddits = new ArrayList<>();

    public HomePresenter(Repository repository, HomeContract.View view) {
        this.mRepository = repository;
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadData(int size, final HomeContract.View.LoadDataCallback callback) {
        mRepository.loadData(
                size,
                new DataSource.LoadDataCallback() {
            @Override
            public void onSuccess(ArrayList<BeanDateDetail> data) {
                callback.onSuccess(data);
            }

            @Override
            public void failed(int errorCode, String msgError) {
                callback.failed(errorCode,msgError);
            }
        });
    }
}
