package com.example.mvp.view;

import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Toast;


import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.commonlibrary.base.BaseActivity;
import com.example.commonlibrary.base.arouter.ARouterConstant;
import com.example.commonlibrary.util.LogUtil;
import com.example.mvc.databinding.ActivityMvpBinding;
import com.example.mvp.demo.DynamicCircleDemo;
import com.example.mvp.presenter.MVPPresenter;
import com.example.mvp.presenter.contract.MVPBannerContract;
import com.smartfoxserver.v2.entities.data.SFSObject;

import java.util.Timer;
import java.util.TimerTask;

import sfs2x.client.SmartFox;
import sfs2x.client.core.BaseEvent;
import sfs2x.client.core.IEventListener;
import sfs2x.client.core.SFSEvent;
import sfs2x.client.entities.Room;
import sfs2x.client.entities.SFSUser;
import sfs2x.client.requests.ExtensionRequest;
import sfs2x.client.requests.JoinRoomRequest;
import sfs2x.client.requests.LoginRequest;
import sfs2x.client.util.ClientDisconnectionReason;
import sfs2x.client.util.ConfigData;


@Route(path = ARouterConstant.MVP)
public class MVPActivity extends BaseActivity<ActivityMvpBinding> implements MVPBannerContract.MVPView {

    MVPPresenter mMVPPresenter;

    @Autowired(name = "age")
    int age;

    @Override
    protected ActivityMvpBinding getViewBinding() {
        return ActivityMvpBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void initView() {
        mMVPPresenter = new MVPPresenter(this);
        Binding.mvpResult.setText(String.valueOf(age));
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sfs == null) {
                    ToastUtils.showLong("先点连接");
                    return;
                }
                sfs.send(new ExtensionRequest("zhangsan", new SFSObject()));
            }
        });
        Binding.ConnectStateBtn.setOnClickListener(v -> {
            if (sfs == null) {
                Connect();
            } else if (sfs.isConnected()) {
                sfs.disconnect();
            }
        });
    }

    private SmartFox sfs;
    private ConfigData cfg;
    //心跳间隔 15s
    private static final int INTERVAL_HEARTBEAT = 5 * 1000;
    //每隔15s发送一次心跳
    private Timer mTimer;

    private void Connect() {
        String ConnectHost = Binding.host.getText().toString().trim();
        int ConnectPort = Integer.parseInt(Binding.port.getText().toString().trim());

        sfs = new SmartFox();
        cfg = new ConfigData();

        cfg.setHost(ConnectHost);
        cfg.setPort(ConnectPort);
        cfg.setZone("BasicExamples");
        cfg.setDebug(true);

        sfs.addEventListener(SFSEvent.CONNECTION, this::MessageCallBack);
        sfs.addEventListener(SFSEvent.CONNECTION_LOST, this::MessageCallBack);
        sfs.addEventListener(SFSEvent.LOGIN, this::MessageCallBack);
        sfs.addEventListener(SFSEvent.LOGIN_ERROR, this::MessageCallBack);
        sfs.addEventListener(SFSEvent.ROOM_JOIN, this::MessageCallBack);
        sfs.addEventListener(SFSEvent.EXTENSION_RESPONSE, this::MessageCallBack);
        sfs.addEventListener(SFSEvent.ADMIN_MESSAGE, this::MessageCallBack);
        sfs.addEventListener(SFSEvent.PUBLIC_MESSAGE, this::MessageCallBack);

        sfs.connect(cfg);
    }

    private void MessageCallBack(BaseEvent evt) {
        String eventType = evt.getType();
        switch (eventType) {
            case SFSEvent.CONNECTION:
                boolean success = (boolean) evt.getArguments().get("success");
                if (success) {
                    ThreadUtils.runOnUiThread(() -> {
                        Binding.MessageResult.append("Connect Success! \n");
                        Binding.ConnectStateBtn.setText("Disconnect");
                    });
                    sfs.send(new LoginRequest("zhangsan"));
                } else {
                    ThreadUtils.runOnUiThread(() -> {
                        Binding.MessageResult.append("Connection failed. Is the server running at " +
                                cfg.getHost() + ":" + cfg.getPort() + "?\n");
                    });
                }
                break;
            case SFSEvent.CONNECTION_LOST:
                Binding.ConnectStateBtn.setText("Connect");
                Binding.MessageResult.append(":: Connection with server was lost :: \n");
                String reason = (String) evt.getArguments().get("reason");
                switch (reason) {
                    case ClientDisconnectionReason.MANUAL://手动断开连接
                        //不做任何事情
                        ThreadUtils.runOnUiThread(() -> {
                            Binding.MessageResult.append("手动断开连接 \n");
                        });
                        break;
                    case ClientDisconnectionReason.UNKNOWN://未知事件
                    case ClientDisconnectionReason.IDLE://一定时间内没有交互而断开的连接
                        ThreadUtils.runOnUiThread(() -> {
                            Binding.MessageResult.append("不知道发生了什么 \n");
                        });
                        break;
                    case ClientDisconnectionReason.KICK://被踢下线
                    case ClientDisconnectionReason.BAN://被禁止连接
                        ThreadUtils.runOnUiThread(() -> {
                            Binding.MessageResult.append("登陆过期了 重新登陆 \n");
                        });
                        break;
                }
                break;
            case SFSEvent.LOGIN:
                ThreadUtils.runOnUiThread(() -> {
                    Binding.MessageResult.append("Logged in as: " + sfs.getMySelf().getName() + "\n");
                });
                sfs.send(new JoinRoomRequest("The Lobby"));
                break;
            case SFSEvent.LOGIN_ERROR:
                ThreadUtils.runOnUiThread(() -> {
                    Binding.MessageResult.append(" Login error \n");
                });
                break;
            case SFSEvent.ROOM_JOIN:
                ThreadUtils.runOnUiThread(() -> {
                    Binding.MessageResult.append(" room join \n");
                });
                break;
            case SFSEvent.ROOM_JOIN_ERROR:
                ThreadUtils.runOnUiThread(() -> {
                    Binding.MessageResult.append(" room join error \n");
                });
                break;
            case SFSEvent.EXTENSION_RESPONSE:
                Binding.MessageResult.append(" EXTENSION_RESPONSE \n");
                break;
            case SFSEvent.PUBLIC_MESSAGE:

                String message = (String) evt.getArguments().get("message");
                Binding.MessageResult.append(message + "  \n");
                break;
            case SFSEvent.ADMIN_MESSAGE:
                String commandObject = (String) evt.getArguments().get("message");
                SFSUser userInfo = (SFSUser) evt.getArguments().get("sender");
                ThreadUtils.runOnUiThread(() -> {
                    Binding.MessageResult.append(" ADMIN_MESSAGE \n " + commandObject + userInfo.toString());
                });
                break;
        }
    }


    public void getData(View view) {
        mMVPPresenter.MVPGetDate();
    }

    @Override
    public void success(String result) {
        runOnUiThread(() -> Binding.mvpResult.setText(result));
    }

    @Override
    public void onError(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}