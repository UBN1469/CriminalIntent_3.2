package com.mygeekbranch.criminalintent_32.database.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mygeekbranch.criminalintent_32.Crime;

import java.util.Date;
import java.util.UUID;

public class CrimeCursoreWrapper extends CursorWrapper {  // Класс обёртка для курсора

    public CrimeCursoreWrapper(Cursor cursor) {
        super(cursor);
    }
    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.TITLE));
        String date = getString(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.DATE));
      int isSolved = getInt(getColumnIndex(CrimeDbSchema.CrimeTable.Cols.SOLVED));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date());
        crime.setSolved(isSolved != 0);



        return  crime;
    }
}
