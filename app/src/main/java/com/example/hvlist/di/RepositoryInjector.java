package com.example.hvlist.di;

import android.content.Context;

import com.example.hvlist.data.Repository;
import com.example.hvlist.data.fake.FakeDataSource;
import com.example.hvlist.data.local.LocalDataSource;
import com.example.hvlist.data.remote.RemoteDataSource;


/**
 * Created by mithilesh on 9/4/16.
 */
public class RepositoryInjector {

    public static Repository provideRepository(Context context) {
        return Repository.getInstance(
                context,
                LocalDataSource.getInstance(context),
                RemoteDataSource.getInstance(context),
                FakeDataSource.getInstance(context)
        );
    }
}
