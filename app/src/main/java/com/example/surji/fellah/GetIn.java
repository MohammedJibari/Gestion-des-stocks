package com.example.surji.fellah;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.surji.clayout01.R;

public class GetIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_in);
    }

    public void getIn(View view) {

        Intent intent = new Intent(GetIn.this, Categories.class);
        startActivity(intent);

    }
}
