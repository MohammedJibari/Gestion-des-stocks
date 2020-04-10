package com.example.surji.fellah;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;

import com.example.surji.clayout01.R;

import java.util.ArrayList;

public class CategorieAfterLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie_after_login);
    }


    public void showArticle(View view) {
        Cursor cursor = getContentResolver().query(MyContentProvider.content_uri, null, null, null, "mobile");
        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> mob = new ArrayList<>();
        ArrayList<String> imgaa = new ArrayList<>();
        while (cursor.moveToNext()) {
            names.add(cursor.getString(0));
            mob.add(String.valueOf(cursor.getLong(1)) + " DH");
            imgaa.add( Base64.encodeToString(cursor.getBlob(2), Base64.DEFAULT) );
        }
        Intent intent = new Intent(CategorieAfterLogin.this, ShowActivity.class);
        intent.putExtra("names", names);
        intent.putExtra("mob", mob );
        intent.putExtra("img", imgaa);
        startActivity(intent);
    }
}
