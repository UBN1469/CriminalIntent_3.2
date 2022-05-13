package com.mygeekbranch.criminalintent_32;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mygeekbranch.criminalintent_32.database.database.CrimeBaseHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {  // Класс синглтон
    private static CrimeLab sCrimeLab;
    private List <Crime> mCrimes;
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
        mCrimes =new ArrayList<>();

        // заполняем временно лист
//        for (int i = 0; i < 100; i++) {
//            Crime crime = new Crime();
//            crime.setTitle("Crime #" + i);
//            crime.setSolved(i % 2 == 0);
//            mCrimes.add(crime);
//        }
    }

    public  void addCrime(Crime crime){
        mCrimes.add(crime);
    }

    public List<Crime> getCrimes() {
        return mCrimes;
    }
    public Crime getCrime(UUID id){ // метод возвращает объкт Crime c заданным индетификатором.
        for (Crime crime : mCrimes){
            if (crime.getID().equals(id)){
                return crime;
            }
        }
        return  null;
    }




}
