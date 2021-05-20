package com.example.mvp.presenter;


import com.example.commonlibrary.base.CallBack;
import com.example.mvp.model.MVPModel;
import com.example.mvp.presenter.contract.MVPBannerContract;

public class MVPPresenter implements MVPBannerContract.MVPPresenter {
    MVPBannerContract.MVPModel mMVPModel;
    MVPBannerContract.MVPView mMVPView;

    public MVPPresenter(MVPBannerContract.MVPView mvpView) {
        mMVPView = mvpView;
        mMVPModel = new MVPModel();
    }

    @Override
    public void MVPGetDate() {
        mMVPModel.MVPGetDate("msg", new CallBack() {
            @Override
            public void onSuccess(String result) {
                mMVPView.success(result);
            }

            @Override
            public void onError(String msg) {
                mMVPView.onError(msg);
            }
        });
    }
}
