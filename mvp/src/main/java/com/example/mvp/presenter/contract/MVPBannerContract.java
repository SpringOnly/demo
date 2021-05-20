package com.example.mvp.presenter.contract;


import com.example.commonlibrary.base.CallBack;

public interface MVPBannerContract {

    interface MVPModel {
        void MVPGetDate(String msg, CallBack callBack);
    }


    interface MVPPresenter {
        void MVPGetDate();
    }


    interface MVPView {
        void success(String result);

        void onError(String mgs);
    }

}
