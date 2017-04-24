package com.example.hvlist.data.remote;

import android.content.Context;

import com.example.hvlist.data.DataSource;

public class RemoteDataSource implements DataSource {


    private static RemoteDataSource INSTANCE = null;

    private Context mContext;

    private RemoteDataSource() {

    }

    private RemoteDataSource(Context context) {
        mContext = context;
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }

        return INSTANCE;
    }

    @Override
    public void loadData(int from, LoadDataCallback callback) {

    }
}
