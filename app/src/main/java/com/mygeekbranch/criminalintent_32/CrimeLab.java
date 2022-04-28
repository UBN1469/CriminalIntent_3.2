package com.mygeekbranch.criminalintent_32;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab sCrimeLab;
    private List <Crime> mCrimes;

    public List<Crime> getCrimes() {
        return mCrimes;
    }
    public Crime gerCrime(UUID id){ // метод возвращает объкт Crime c заданным индетификатором.
        for (Crime crime : mCrimes){
            if (crime.getID().equals(id)){
                return crime;
            }
        }
        return  null;
    }

    public static CrimeLab getCrimeLab(Context context) {
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context) {
        mCrimes =new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);

        }

    }
}
