package com.example.hvlist.data.fake;

import android.content.Context;

import com.example.hvlist.data.DataSource;
import com.example.hvlist.mvp.beans.BeanDateDetail;
import com.example.hvlist.utils.AppConstants;

import java.util.ArrayList;


public class FakeDataSource implements DataSource {
    private static FakeDataSource INSTANCE;

    private Context mContext;

    public FakeDataSource(Context context) {
        mContext = context;
    }

    public static FakeDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new FakeDataSource(context);
        }
        return INSTANCE;
    }

    private static class Error {
        private static final int ERROR_UNKNOWN = 0;
    }

    @Override
    public void loadData(
            int from,
            LoadDataCallback callback) {

        ArrayList<BeanDateDetail> listData = createData(from);

        try {
            callback.onSuccess(listData);
        } catch (Exception e) {
            callback.failed(Error.ERROR_UNKNOWN, "Unknown error");
        }
    }

    private ArrayList<BeanDateDetail> createData(int from) {

        ArrayList<BeanDateDetail> listData = new ArrayList<>();

        for (int i = from; i < (from + 10); i++) {

            BeanDateDetail data = new BeanDateDetail();
            data.setSelected(false);
            data.setDate(String.valueOf(i * 20));

            ArrayList<String> dateDetailList = new ArrayList<>();

            for (int j = 0; j < 20; j++) {
                String dateDetail = String.valueOf((i * 20) + j);
                dateDetailList.add(dateDetail);
            }

            data.setDetail(dateDetailList);
            listData.add(data);
        }

        if (from == 0) {
            listData.get(0).setSelected(true);
        }
        return listData;
    }
}
