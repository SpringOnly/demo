package com.example.mvvm.view;

import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.mvvm.R;
import com.example.mvvm.databinding.ActivityMvvmBinding;
import com.example.mvvm.model.MvvmModel;
import com.example.mvvm.viewmodel.ViewModel;

@Route(path = ARouterConstant.MVVM)
public class MvvmActivity extends Activity {

    ViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMvvmBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_mvvm);

        mViewModel = new ViewModel(new MvvmModel());
        binding.setBanner(mViewModel);

        binding.result.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("age", 18);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}