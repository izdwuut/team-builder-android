package com.example.konikiewiczb.myapplication.welcome;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.konikiewiczb.myapplication.R;
import com.example.konikiewiczb.myapplication.login.LoginActivity;
import com.example.konikiewiczb.myapplication.model.TokenRepository;

public class WelcomeActivity extends Activity implements View.OnClickListener, WelcomeContract.View {
    Button logout;
    WelcomeContract.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        logout = findViewById(R.id.logout);
        logout.setOnClickListener(this);
        presenter = new WelcomePresenter(new TokenRepository(getApplicationContext()));
    }

    @Override
    public void onClick(View view) {
        presenter.logOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
