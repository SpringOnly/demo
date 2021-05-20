package com.example.mvvm.viewmodel;

import androidx.databinding.ObservableField;

import com.example.commonlibrary.base.CallBack;
import com.example.mvvm.model.BannerModel;


public class ViewModel {

    private BannerModel mBannerModel;

    public  final ObservableField<String> mResult = new ObservableField<>();

    public ViewModel(BannerModel bannerModel) {
        mBannerModel = bannerModel;
    }


    public void getModel() {
        mBannerModel.getBanner("msg", new CallBack() {
            @Override
            public void onSuccess(String result) {
                mResult.set(result);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }
}
