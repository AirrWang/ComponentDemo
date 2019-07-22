package com.mhd.basekit.viewkit.view;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.muheda.mhdsystemkit.systemUI.stateView.StateView;
import com.mhd.basekit.R;
import com.mhd.basekit.viewkit.mvp.view.netstateview.Loading;
import com.mhd.basekit.viewkit.mvp.view.netstateview.NetError;
import com.mhd.basekit.viewkit.mvp.view.netstateview.NoData;
import com.mhd.basekit.viewkit.util.LightStatusBarUtils;
import com.mhd.basekit.viewkit.util.ReplaceInterface;
import com.muheda.customtitleview.CustomTitleView;
import com.muheda.customtitleview.ITitleView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 13660 on 2018/10/22.
 */

public abstract class BaseDBFragment<VB extends ViewDataBinding> extends Fragment {

    public static final int NET_STATE_DISMISS = 1;
    public static final int NET_STATE_NO_DATA = 2;
    public static final int NET_STATE_ERROR = 4;

    protected final String NET_LOADING = "NET_LOADING";
    protected final String NET_NO_DATA = "NET_NO_DATA";
    protected final String NET_ERROR = "NET_ERROR";

    protected VB mBinding;

    protected Map<String, Class> stateMapConfig;

    protected abstract int getLayoutDBId();

    protected abstract void initMvp(Bundle savedInstanceState);

    protected abstract void initConfig();

    protected abstract void initDBView();

    protected abstract void initDB();

    protected abstract void replaceDBLoad();

    private boolean isShow = true;//决策是否展示 主要用于列表首次进入展示 刷新的时候不展示
    private StateView ll_load_page;
    protected CustomTitleView base_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutDBId(), container, false);
        ll_load_page = (StateView) mBinding.getRoot().findViewById(R.id.ll_load_page);
        base_title = (CustomTitleView) mBinding.getRoot().findViewById(R.id.base_title);
        base_title.setTitleColor(getResources().getColor(R.color.color_22262E));
        initBaseTitle();
        initStateMapConfig();
        initMvp(savedInstanceState);
        initConfig();
        initDBView();
        initDB();
        return mBinding.getRoot();
    }

    protected void initBaseTitle() {
        base_title.post(new Runnable() {
            @Override
            public void run() {
                int top = base_title.getTop();
                if (top > 10) {
                    base_title.setTop(LightStatusBarUtils.getStatusBarHeight(getActivity()));
                }
            }
        });
    }

    protected void hideLeftBack(boolean isHide) {
        base_title.enableLeftShow(isHide);
    }

    protected void setTitle(String title) {
        base_title.setTitle(title);
    }

    protected void initStateMapConfig() {
        stateMapConfig = new HashMap<>();
        stateMapConfig.put(NET_LOADING, Loading.class);
        stateMapConfig.put(NET_NO_DATA, NoData.class);
        stateMapConfig.put(NET_ERROR, NetError.class);
    }

    /**
     * 自定义左侧布局
     *
     * @param titleView
     */
    protected void setTitleLeftLayout(ITitleView titleView) {
        base_title.setTitleLeftLayout(titleView);
    }

    /**
     * 自定义中间布局
     *
     * @param titleView
     */
    protected void setTitleCenterLayout(ITitleView titleView) {
        base_title.setTitleCenterLayout(titleView);
    }

    /**
     * 自定义右侧布局
     *
     * @param titleView
     */
    protected void setTitleRightLayout(ITitleView titleView) {
        base_title.setTitleRightLayout(titleView);
    }

    /**
     * 顶部全自定义布局
     *
     * @param titleView
     */
    protected void setCustomTitle(ITitleView titleView) {
        base_title.setCustomTitle(titleView);
    }


    protected void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }

    protected boolean isShow() {
        return isShow;
    }

    protected void showLoading() {
        if (isShow()) {
            ll_load_page.setVisibility(View.VISIBLE);
            ll_load_page.setData(NET_LOADING, stateMapConfig);
        }
    }

    public void dismiss() {
        ll_load_page.setVisibility(View.GONE);
    }

    protected void dismiss(int type) {
        //type 1 成功 2 成功-数据为空 (包括非200) 4 请求失败  网络
        if (isShow()) {
            ll_load_page.setVisibility(View.VISIBLE);
            switch (type) {
                case NET_STATE_DISMISS:
                    ll_load_page.setVisibility(View.GONE);
                    break;
                case NET_STATE_NO_DATA:
                    ll_load_page.setData(NET_NO_DATA, stateMapConfig);
                    break;
                case NET_STATE_ERROR:
                    ll_load_page.setData(NET_ERROR, stateMapConfig, new ReplaceInterface() {
                        @Override
                        public void replace() {
                            replaceDBLoad();
                        }
                    });
                    break;
            }
        }
    }
}
