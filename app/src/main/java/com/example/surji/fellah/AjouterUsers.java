package com.example.surji.fellah;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.surji.clayout01.R;


public class AjouterUsers extends AppCompatActivity {

    DataBaseHelper mydb;
    EditText nom , pswd , Cpswd;
    Button ajouter_user;
    TextView toConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_users);

        // nouvelle instance de class DataBaseHelper pour appeler le constructeur de class DataBaseHelper donc (creer DB et Table)
        mydb = new DataBaseHelper(this);

        //liasion des champs et de bouton
        nom = findViewById(R.id.z1);
        pswd = findViewById(R.id.z2);
        Cpswd = findViewById(R.id.z3);
        ajouter_user = findViewById(R.id.users);
        //toConn = findViewById(R.id.addUser);

        //l'appele au methode AddUser()
        AddUser();
    }


    //methode pour ajouter des donnees
    public  void AddUser()
    {
        //ajout des donnees si on clique au bouton "Ajouter"
        ajouter_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((pswd.getText().toString()).equals(Cpswd.getText().toString()))
                {
                    //on met l'expression en variable parceque la methode insertData retourne una valeur de type boolean
                    boolean isInserted = mydb.insertDataUser(nom.getText().toString(),pswd.getText().toString(),"utilisateur");

                    if (isInserted)
                        Toast.makeText(AjouterUsers.this,"Utilisateur ajouté", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AjouterUsers.this,"Utilisateur non ajouté", Toast.LENGTH_LONG).show();


                    //vider les champs
                    nom.setText("");
                    pswd.setText("");
                    Cpswd.setText("");

                    //ouvrir app
                    Intent i = new Intent(AjouterUsers.this, CategorieAfterLogin.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(AjouterUsers.this,"Les mots de passe ne sont pas semblables", Toast.LENGTH_LONG).show();


            }
        });
    }


    public void Connecter(View view) {
        Intent i = new Intent(AjouterUsers.this, Users.class);
        startActivity(i);
    }
}
