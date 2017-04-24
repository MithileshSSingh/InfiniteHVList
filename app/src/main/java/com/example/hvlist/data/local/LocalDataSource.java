package com.example.hvlist.data.local;

import android.content.Context;

import com.example.hvlist.data.DataSource;

public class LocalDataSource implements DataSource {
    private static LocalDataSource INSTANCE;

    private Context mContext;

    public LocalDataSource(Context context) {
        mContext = context;
    }

    public static LocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void loadData(
            int from,
            LoadDataCallback callback) {

    }
}
