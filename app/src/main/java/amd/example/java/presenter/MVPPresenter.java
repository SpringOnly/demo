package amd.example.java.presenter;


import amd.example.commonlibrary.base.CallBack;
import amd.example.java.model.MVPModel;
import amd.example.java.presenter.contract.MVPBannerContract;

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
