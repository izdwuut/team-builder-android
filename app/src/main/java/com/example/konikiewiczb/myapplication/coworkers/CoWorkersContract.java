package com.example.konikiewiczb.myapplication.coworkers;

import com.example.konikiewiczb.myapplication.framework.views.progress_bar.ProgressBarHandler;
import com.example.konikiewiczb.myapplication.model.User;

import java.util.List;

import retrofit2.Response;

public interface CoWorkersContract {

    interface CoWorkersPresenter {

        void fetchWorkersList();

        void onDestroy();

    }

    interface CoWorkersInteractor {

        interface OnFetchingDataFinishedListener {

            void onSuccess(Response<List<User>> response);

            void onFailure();

        }

        void fetchData(OnFetchingDataFinishedListener onFetchingDataFinishedListener);

    }

    interface CoWorkersView extends ProgressBarHandler {


        void adapterThisShit(Response<List<User>> response);

    }
}
