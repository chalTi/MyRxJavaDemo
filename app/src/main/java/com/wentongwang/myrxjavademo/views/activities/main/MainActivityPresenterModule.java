package com.wentongwang.myrxjavademo.views.activities.main;

import dagger.Module;
import dagger.Provides;

/**
 * module for MainActivityPresenter,
 * provide view and model
 * Created by Wentong WANG on 2016/10/12.
 */
@Module
public class MainActivityPresenterModule {

    private MainView mainView;

    public MainActivityPresenterModule(MainView mainView) {
        this.mainView = mainView;
    }
    @Provides
    MainView provideMainView(){
        return mainView;}
}
