package com.example.hvlist.mvp.screen_home;

import com.example.hvlist.mvp.BasePresenter;
import com.example.hvlist.mvp.BaseView;
import com.example.hvlist.mvp.beans.BeanDateDetail;

import java.util.ArrayList;

/**
 * Created by mithilesh on 9/11/16.
 */
public interface HomeContract {
    interface OnItemSelectedListener {
        void onItemSelected(int position);

    }

    interface View extends BaseView<Presenter> {
        interface LoadDataCallback {
            void onSuccess(ArrayList<BeanDateDetail> data);

            void failed(int errorCode, String msgError);
        }
    }

    interface Presenter extends BasePresenter {
        void loadData(
                int size, View.LoadDataCallback callback
        );
    }
}
