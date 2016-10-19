package com.wentongwang.myrxjavademo.views.activities.main;

import com.wentongwang.myrxjavademo.http.model.MovieEntity;
import com.wentongwang.myrxjavademo.http.services.MovieService;
import com.wentongwang.myrxjavademo.http.services.RxJavaService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Wentong WANG on 2016/10/12.
 */
public class MainActivityPresenter {

    private MainView mainView;

    @Inject
    MainActivityPresenter(MainView mainView) {
        this.mainView = mainView;
    }


    public void show(){
        mainView.showSomeThing("hello world!");

    }

    /**
     * Retrofit request
     */
    public void getInfo(){
        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieService movieService = retrofit.create(MovieService.class);
        Call<MovieEntity> call = movieService.getTopMovie(0, 10);
        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                mainView.showSomeThing(response.body().getTitle());
//                mainView.showSomeThing(response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                mainView.showSomeThing(t.getMessage());

            }
        });
    }

    /**
     * Retrofit+RxJava
     */
    public void getInfoRxJava(){
        String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        RxJavaService rxJavaService = retrofit.create(RxJavaService.class);

        Observable<MovieEntity> observable = rxJavaService.getTopMovie(0, 10);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.immediate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mainView.showSomeThing(e.toString());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        mainView.showSomeThing(movieEntity.getTitle());
                    }
                });

    }
}
