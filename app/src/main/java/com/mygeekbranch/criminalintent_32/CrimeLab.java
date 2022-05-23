package com.mygeekbranch.criminalintent_32;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mygeekbranch.criminalintent_32.database.database.CrimeBaseHelper;
import com.mygeekbranch.criminalintent_32.database.database.CrimeCursorWrapper;
import com.mygeekbranch.criminalintent_32.database.database.CrimeDbSchema;
import com.mygeekbranch.criminalintent_32.database.database.CrimeDbSchema.CrimeTable;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {  // Класс синглтон
    private static CrimeLab sCrimeLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab getCrimeLab(Context context) { // метод возвращает экземпляр класса  CrimeLab
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }
    private CrimeLab(Context context) {  // конструктор
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();



    }

    public  void addCrime(Crime crime){
        ContentValues values = getContentValues(crime);
        mDatabase.insert(CrimeTable.NAME, null, values);

    }

    public List<Crime> getCrimes() {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor =  queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }
        return crimes;

    }
    public Crime getCrime(UUID id){  // метод возвращает объкт Crime c заданным индетификатором.
        CrimeCursorWrapper cursor  = queryCrimes(CrimeTable.Cols.UUID + " = ?"
                , new String[]{id.toString()});
       try{
           if ( cursor.getCount() == 0){
            return null;
        }
        cursor.moveToFirst();
        return  cursor.getCrime();
       } finally {
           cursor.close();
       }




    }
    //   Метод обновление БД
    public  void updateCrime (Crime crime){
        String uuidString = crime.getID().toString();
        ContentValues  values = getContentValues(crime);
        mDatabase.update(CrimeTable.NAME, values, CrimeTable.Cols.UUID + " = ?", new String[] {uuidString});

    };
    // Чтение из БД
    //private Cursor queryCrimes (String whereClause, String [] whereArgs){
    private CrimeCursorWrapper queryCrimes(String whereClause, String [] whereArgs ){

        Cursor cursor = mDatabase.query(CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
       // return  cursor;
        return new CrimeCursorWrapper(cursor);
    }

    // Запись  в БД
    private static ContentValues getContentValues (Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getID().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved()? 1  : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
        return  values;
    }




}
