package com.example.surji.fellah;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.surji.clayout01.R;


public class Users extends AppCompatActivity {

    DataBaseHelper mydb;
    EditText nom, pswd;
    Button conn;
    TextView toAdd;
    int i,ii;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        // nouvelle instance de class DataBaseHelper pour appeler le constructeur de class DataBaseHelper donc (creer DB et Table)
        mydb = new DataBaseHelper(this);

        //liasion des champs et de bouton
        nom = findViewById(R.id.z1);
        pswd = findViewById(R.id.z2);
        conn = findViewById(R.id.users);
        //toAdd = findViewById(R.id.addUser);

        ViewAll();

    }


    //methode pour recuperer les donnees
    public void ViewAll()
    {
        conn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getAllDataUser();
                if (res.getCount() == 0)
                {
                    //affichage de message (voir methode showMessage())
                    showMessage("Error","Nom d'utilisateur ou mot de passe est incorrect!");
                    return;
                }

                //l'ajout des donnees recuperees au stringBuffer
                while (res.moveToNext())
                {
                    if ((nom.getText().toString()).equals("jibari") && (pswd.getText().toString()).equals("jibari"))
                    {
                        ii++;
                    }
                    else if ((nom.getText().toString()).equals(res.getString(1)) && (pswd.getText().toString()).equals(res.getString(2)))
                    {
                        i++;
                    }
                }

                if (ii>0)
                {
                    Intent i = new Intent(Users.this, MainActivity.class);
                    startActivity(i);
                }
                else if (i>0)
                {
                    Intent i = new Intent(Users.this, CategorieAfterLogin.class);
                    startActivity(i);

                }
                else
                //show message (voir methode showMessage())
                showMessage("Error","Nom d'utilisateur ou mot de passe est incorrect!");


            }
        });
    }


    public void showMessage(String title, String Message)
    {
        //affichage de message box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    public void Connecter(View view) {
        Intent i = new Intent(Users.this, AjouterUsers.class);
        startActivity(i);
    }
}
