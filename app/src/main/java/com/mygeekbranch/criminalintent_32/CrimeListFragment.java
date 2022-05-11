package com.mygeekbranch.criminalintent_32;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container,false);
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view);
        //mCrimeRecyclerView.setHasFixedSize(true);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }


    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mSolvedImageView;
        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_crime,parent,false ));
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
            itemView.setOnClickListener(this);
        }
        public void bind(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, MMM dd, yyyy");
           // mDateTextView.setText(mCrime.getDate().toString());
            String time = simpleDateFormat.format(mCrime.getDate());
            mDateTextView.setText(time);
            mSolvedImageView.setVisibility(mCrime.isSolved() ? View.VISIBLE : View.GONE);
        };

        @Override
        public void onClick(View v) {
          /*
             Toast.makeText(getActivity()," cliked!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),CrimeActivity.class);
            Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getID());
            */
            Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getID());
            startActivity(intent);

        }

    }
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public CrimeAdapter( List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater =LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            Crime crime =mCrimes.get(position);
            holder.bind(crime);

        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }


    }
    private  void  updateUI(){
        CrimeLab crimeLab = CrimeLab.getCrimeLab(getActivity());
        List <Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null){
        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);}
        else mAdapter.notifyDataSetChanged();
    }




}