package com.mygeekbranch.criminalintent_32.database.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mygeekbranch.criminalintent_32.Crime;
import com.mygeekbranch.criminalintent_32.database.database.CrimeDbSchema.CrimeTable;

import java.util.Date;
import java.util.UUID;

public class CrimeCursorWrapper extends CursorWrapper {  // Класс обёртка для курсора

    public CrimeCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Crime getCrime(){
        String uuidString = getString(getColumnIndex(CrimeTable.Cols.UUID));
        String title = getString(getColumnIndex(CrimeTable.Cols.TITLE));
       Long date = getLong(getColumnIndex(CrimeTable.Cols.DATE));
      int isSolved = getInt(getColumnIndex(CrimeTable.Cols.SOLVED));
      String suspect = getString(getColumnIndex(CrimeTable.Cols.SUSPECT));

        Crime crime = new Crime(UUID.fromString(uuidString));
        crime.setTitle(title);
        crime.setDate(new Date(date));
        crime.setSolved(isSolved != 0);
        crime.setSuspect(suspect);

        return  crime;
    }
}
