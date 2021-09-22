package amd.example.java.model;


import java.io.IOException;

import amd.example.commonlibrary.base.CallBack;
import amd.example.commonlibrary.base.api.Api;
import amd.example.java.presenter.contract.MVPBannerContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class MVPModel implements MVPBannerContract.MVPModel {

    @Override
    public void MVPGetDate(String msg, CallBack callBack) {
        Api.getService().getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody value) {
                        try {
                            callBack.onSuccess(value.string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
