package com.wentongwang.myrxjavademo.views.activities.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wentongwang.myrxjavademo.R;

import javax.inject.Inject;

/**
 * Created by Wentong WANG on 2016/10/12.
 */
public class MainActivity extends AppCompatActivity implements MainView {

    @Inject MainActivityPresenter presenter;
    private Button button;
    private TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        button = (Button) findViewById(R.id.btn);
        content = (TextView) findViewById(R.id.content);

        creatPresenter();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getInfo();
            }
        });
    }
    //inject le presenter
    private void creatPresenter(){
        DaggerMainActivityComponent.builder().mainActivityPresenterModule(new MainActivityPresenterModule(this))
        .build()
        .inject(this);
    }

    @Override
    public void showSomeThing(String info) {
        content.setText(info);
    }
}
