package com.example.mvp.demo;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvp.adapter.ChatAdapter;
import com.example.mvp.adapter.GridLayoutAdapter;
import com.example.mvp.bean.GridlayoutBean;
import com.example.mvp.bean.TypeLeftBean;
import com.example.mvp.bean.TypeRightBean;

import java.util.ArrayList;
import java.util.List;

public class BaseAdapterDemo {

    public static void GridRV() {
//        List<GridlayoutBean> list = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            GridlayoutBean gridlayoutBean = new GridlayoutBean();
//            gridlayoutBean.setMessage("消息：" + i);
//            list.add(gridlayoutBean);
//        }
//
//        GridLayoutAdapter gridLayoutAdapter = new GridLayoutAdapter(this);
//        Binding.RecyclerView.setAdapter(gridLayoutAdapter);
//        gridLayoutAdapter.setData(list);
//
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                List<GridlayoutBean> data = gridLayoutAdapter.getData();
//                if (data.size() < 5) {
//                    if (position == 0) {
//                        return 2;
//                    }
//                } else {
//                    if (position == 4) {
//                        return 2;
//                    }
//                }
//                return 1;
//            }
//        });
//        Binding.RecyclerView.setLayoutManager(gridLayoutManager);

    }



    public void chatRV() {
//        ChatAdapter mChatAdapter = new ChatAdapter(this);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        Binding.RecyclerView.setLayoutManager(layoutManager);
//        Binding.RecyclerView.setAdapter(mChatAdapter);
//
//        //聊天对话框的demo
//        List<Object> list = new ArrayList<>();
//
//        TypeLeftBean typeLeftBean = new TypeLeftBean();
//        typeLeftBean.setLeftMessage("左边1");
//        list.add(typeLeftBean);
//
//        TypeRightBean typeRightBean = new TypeRightBean();
//        typeRightBean.setRightMessage("右边1");
//        list.add(typeRightBean);
//
//        TypeRightBean typeRightBean2 = new TypeRightBean();
//        typeRightBean2.setRightMessage("右边2");
//        list.add(typeRightBean2);
//
//        TypeLeftBean typeLeftBean2 = new TypeLeftBean();
//        typeLeftBean2.setLeftMessage("左边2");
//        list.add(typeLeftBean2);
//
//        mChatAdapter.replaceData(list);
    }

}
