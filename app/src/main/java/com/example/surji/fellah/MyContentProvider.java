package com.example.surji.fellah;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    public static final String dbname = "m_stock";
    public static final int dbv = 1;
    public static final String authority = "com.example.surjitsingh.myprovider";
    public static final Uri content_uri = Uri.parse("content://" + authority + "/info");
    static int per = 1;
    static int per_id = 2;
    static UriMatcher myuri = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        myuri.addURI(authority, "info", per);
        myuri.addURI(authority, "info", per_id);
    }

    SQLiteDatabase myDataBase;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long row = myDataBase.insert("info", null, values);
        if (row == -1) {
            return null;
        } else {
            uri = ContentUris.withAppendedId(content_uri, row);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }
    }

    @Override
    public boolean onCreate() {
        MyDataBase mydb = new MyDataBase(getContext());
        myDataBase = mydb.getWritableDatabase();
        if (myDataBase != null) {
            return true;
        } else
            return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder myquery = new SQLiteQueryBuilder();
        myquery.setTables("info");
        Cursor cr = myquery.query(myDataBase, null, null, null, null, null, sortOrder);
        cr.setNotificationUri(getContext().getContentResolver(), uri);

        return cr;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException("Not yet implemented");
    }



     class MyDataBase extends SQLiteOpenHelper {
        Context ct;

        MyDataBase(Context ct) {
            super(ct, dbname, null, dbv);
            this.ct = ct;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String s = "create table info (name text, mobile integer, image BLOB)";
            sqLiteDatabase.execSQL(s);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        }

        public  Cursor getAllData()
        {
            SQLiteDatabase db = this.getWritableDatabase();
            //rawQuery (pour les syntaxes sql)
            //deuxieme parametre signifie la valeur qui remplace la premiere valeur (premiere parametre)
            Cursor res = db.rawQuery("select * from info where name like '%ar1'" ,null);
            return res;
        }
    }
}