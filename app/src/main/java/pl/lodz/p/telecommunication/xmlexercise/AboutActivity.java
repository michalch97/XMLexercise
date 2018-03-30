package pl.lodz.p.telecommunication.xmlexercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);//powiazanie klasy z plikiem widoku
    }

    public void onBackClick(View view) {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);//wywolywanie activity ekranu powitania po wyborze odpowiedniej opcji z menu
    }
}
