package com.example.surji.fellah;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surji.clayout01.R;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText name, mobile;
    Button save;
    TextView image,showAll;
    byte[] img = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        save = findViewById(R.id.save);
        image = findViewById(R.id.myimg);
        showAll = findViewById(R.id.show);

        image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 90);
            }
        });

        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = name.getText().toString();
                String m = mobile.getText().toString();
                if (n.isEmpty()) {
                    name.setError("Entrer le nom d'article");
                } else if (m.isEmpty()) {
                    mobile.setError("Entrer le prix d'article");
                }else {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", n);
                    contentValues.put("mobile", m );
                    contentValues.put("image", img);
                    Uri uri = getContentResolver().insert(MyContentProvider.content_uri, contentValues);
                    /*if (uri == null)
                    {
                        mobile.setError("Mobile no. already exists. Try Another one");
                    } else
                        {

                     */
                        Toast.makeText(MainActivity.this, "Sucessfully added", Toast.LENGTH_SHORT).show();
                        name.setText("");
                        mobile.setText("");

                   // }
                }
            }
        });

        showAll.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = getContentResolver().query(MyContentProvider.content_uri, null, null, null, "mobile");
                ArrayList<String> names = new ArrayList<>();
                ArrayList<String> mob = new ArrayList<>();
                ArrayList<String> imgaa = new ArrayList<>();
                while (cursor.moveToNext()) {
                    names.add(cursor.getString(0));
                    mob.add(String.valueOf(cursor.getLong(1)) + " DH");
                    imgaa.add( Base64.encodeToString(cursor.getBlob(2), Base64.DEFAULT) );
                }
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("names", names);
                intent.putExtra("mob", mob );
                intent.putExtra("img", imgaa);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 90) {
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            img = stream.toByteArray();
            ((ImageView)findViewById(R.id.aaaaaa)).setImageBitmap(BitmapFactory.decodeByteArray(img, 0 , img.length));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}