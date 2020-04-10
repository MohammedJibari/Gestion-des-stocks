package com.example.surji.fellah;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surji.clayout01.R;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    GridView gridView;
    ArrayAdapter<String> adapter;
    List<String> names = new ArrayList<>();
    List<String> mob = new ArrayList<>();
    List<String> img = new ArrayList<>();

    Spinner spinner;
    String[] SPINNERVALUES = {"Tous","Telephone","Pc portable","Television","Tablette"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        //spinner = findViewById(R.id.spinner);
        //ArrayAdapter<String> ad = new ArrayAdapter<String>(ShowActivity.this, android.R.layout.simple_list_item_1, SPINNERVALUES);
        //spinner.setAdapter(ad);
        //selected = spinner.getSelectedItem().toString();
        /*ArrayAdapter<String> myadabt = new ArrayAdapter<String>(ShowActivity.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.categorie));
        myadabt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myadabt);
         */


       // spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         //   @Override
         //   public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

           //     if ((spinner.getSelectedItem().toString()).equals("Tous")) {


                    names = getIntent().getStringArrayListExtra("names");
                    mob = getIntent().getStringArrayListExtra("mob");
                    img = getIntent().getStringArrayListExtra("img");
                    gridView = findViewById(R.id.gridView);

                    adapter = new ArrayAdapter<String>(ShowActivity.this, R.layout.item_view, R.id.name, names) {
                        @NonNull
                        @Override
                        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);
                            ImageView image = view.findViewById(R.id.imageView);

                            TextView choix = view.findViewById(R.id.choisir);
                            choix.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String text = getItem(position);
                                    Toast.makeText(getApplicationContext(), text + " Bien bien ajoute", Toast.LENGTH_LONG).show();


                                    //Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
                                }
                            });

                            byte[] encodeByte = Base64.decode(img.get(position), Base64.DEFAULT);
                            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

                            image.setImageBitmap(bitmap);

                            TextView mobile = view.findViewById(R.id.mobile);
                            mobile.setText(mob.get(position));
                            return view;
                        }
                    };
                    gridView.setAdapter(adapter);

              //  }
              //  else {

               // }
           // }

           // @Override
           // public void onNothingSelected(AdapterView<?> adapterView) {

           // }
        //});

    }

    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.show_menu, menu);//Menu Resource, Menu
        //getMenuInflater().inflate(R.layout.navbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_item:
                Toast.makeText(getApplicationContext(), "Item 3 Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
