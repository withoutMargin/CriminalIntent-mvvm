package com.example.criminalintent.view.activity;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.example.criminalintent.R;
import com.example.criminalintent.adapters.CrimeAdapter;
import com.example.criminalintent.view.fragment.CrimeDetailFragment;
import com.example.criminalintent.view.fragment.CrimeListFragment;
import com.example.criminalintent.model.Crime;

public class CrimeListActivity extends SingleFragmentActivity
        implements CrimeAdapter.OnCrimeSelectListener, CrimeDetailFragment.Callbacks {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CrimeListActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return CrimeListFragment.newInstance();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_master_detail;
    }

    @Override
    public void onCrimeSelected(Crime crime) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = CrimePagerActivity.newIntent(this, crime.getId());
            startActivity(intent);
        } else {
            CrimeDetailFragment crimeDetailFragment = CrimeDetailFragment.newInstance(crime.getId());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_fragment_container, crimeDetailFragment)
                    .commit();
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {
        CrimeListFragment crimeListFragment = (CrimeListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);

        crimeListFragment.updateUI();
    }
}