package com.wentongwang.myrxjavademo.views.activities.main;

import com.wentongwang.myrxjavademo.utils.ActivityScope;

import dagger.Component;

/**
 * Created by Wentong WANG on 2016/10/12.
 */
@ActivityScope
@Component(modules = MainActivityPresenterModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
