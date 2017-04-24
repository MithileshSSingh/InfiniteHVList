package com.example.hvlist.data;

import com.example.hvlist.mvp.beans.BeanDateDetail;

import java.util.ArrayList;

/**
 * Created by mithilesh on 8/23/16.
 */
public interface DataSource {
    interface LoadDataCallback {
        void onSuccess(ArrayList<BeanDateDetail> data);

        void failed(int errorCode, String msgError);
    }

    void loadData(
            int from,
            LoadDataCallback callback
    );
}
