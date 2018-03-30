package pl.lodz.p.telecommunication.xmlexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pl.lodz.p.telecommunication.xmlexercise.R;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);//powiazanie klasy z plikiem widoku
    }

    public void onStartClick(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
