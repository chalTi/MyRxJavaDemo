package com.wentongwang.myrxjavademo.http.services;

import com.wentongwang.myrxjavademo.http.model.MovieEntity;


import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Wentong WANG on 2016/10/19.
 */
public interface RxJavaService {
    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
