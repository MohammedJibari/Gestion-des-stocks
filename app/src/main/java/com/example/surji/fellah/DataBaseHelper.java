package com.example.surji.fellah;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    //database generale
    public static final String DATABASE_NAME = "G_Stock.db";
    /*//table des articles avec ses attributs
    public static final String TABLE_NAME = "Article";
    public static final String COL_1 = "Id";
    public static final String COL_2 = "Nom";
    public static final String COL_3 = "Prix";
    public static final String COL_4 = "Categorie";
     */
    //table des utilisateurs avec ses attributs
    public static final String TABLE_USER = "Utilisateur";
    public static final String COL_1_U = "Id";
    public static final String COL_2_U = "Nom";
    public static final String COL_3_U = "Password";
    public static final String COL_4_U = "Status";


    public DataBaseHelper(Context context) {
        //choisir de database utilisee pour stockee les donnees
        super(context, DATABASE_NAME, null, 1);

        // check create table
        //mais lorsequ'on ajoute la methode insertdata() on deplace cette ligne en la;
        /*SQLiteDatabase db = this.getWritableDatabase();*/
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creation de table
        //db.execSQL("create table " + TABLE_NAME + " (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nom TEXT , Prix INTEGER , Categorie TEXT)");
        db.execSQL("create table " + TABLE_USER + " (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nom TEXT , Password TEXT , Status TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //check if deja cree donc drop it
        //db.execSQL("drop table if exists " + TABLE_NAME );
        db.execSQL("drop table if exists " + TABLE_USER );
        //and create the table after that
        onCreate(db);
    }

    /*//on a ajoute 3 parametres parceque le "Id" est autoIncrement
    public boolean insertData(String nom, int prix, String categorie)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //pour mettre les donnees aux colomnes
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,nom);
        contentValues.put(COL_3,prix);
        contentValues.put(COL_4,categorie);

        //insertion des donnees en table (voir db. ....) and (clique controle et clique en insert pour voir la methode origine et le type de retour)
        /*db.insert(TABLE_NAME,null,contentValues);*/

        // on mettre l'expression en variable
       /* long result = db.insert(TABLE_NAME,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

        */

    //on a ajoute 3 parametres parceque le "Id" est autoIncrement
    public boolean insertDataUser(String nom, String password, String status)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        //pour mettre les donnees aux colomnes
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_U,nom);
        contentValues.put(COL_3_U,password);
        contentValues.put(COL_4_U,status);

        //insertion des donnees en table (voir db. ....) and (clique controle et clique en insert pour voir la methode origine et le type de retour)
        /*db.insert(TABLE_NAME,null,contentValues);*/

        // on mettre l'expression en variable
        long result = db.insert(TABLE_USER,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

   /* //recuperation des donnees
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //rawQuery (pour les syntaxes sql)
        //deuxieme parametre signifie la valeur qui remplace la premiere valeur (premiere parametre)
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    */


    //recuperation des donnees des Utilisateur
    public Cursor getAllDataUser()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        //rawQuery (pour les syntaxes sql)
        //deuxieme parametre signifie la valeur qui remplace la premiere valeur (premiere parametre)
        Cursor res = db.rawQuery("select * from " + TABLE_USER,null);
        return res;
    }



}
