package com.websoft.trial.rajawalibasicproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import com.websoft.trial.rajawalibasicproject.fragment.ExperimentFragment;
import com.websoft.trial.rajawalibasicproject.util.FileUtils;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);

        initViews();

        load();
    }

    private void load() {

        copyAssets();

        //launchFragment(EarthFragment.getInstance());
        launchFragment(ExperimentFragment.getInstance());
    }

    private void copyAssets() {

        final ProgressDialog progressDialog = new ProgressDialog(this);

        progressDialog.setMessage(getString(R.string.please_wait));

        progressDialog.show();

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {

                FileUtils.copyFolder("car2", MainActivity.this);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {

                super.onPostExecute(aVoid);

                if(progressDialog != null && progressDialog.isShowing()){

                    progressDialog.dismiss();
                }

            }

        }.execute();

    }

    private void initViews() {

    }

    private void launchFragment(Fragment fragment) {

        final FragmentManager fragmentManager = getSupportFragmentManager();

        final FragmentTransaction transaction = fragmentManager.beginTransaction();

        try {

            transaction.replace(R.id.frame_layout, fragment, "");
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
