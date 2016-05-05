package com.example.sergio.spotify_angular;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sergio.spotify_angular.adapters.CategoriesAdapter;
import com.example.sergio.spotify_angular.fragments.CategoriesSelectorFragment;
import com.example.sergio.spotify_angular.fragments.PlaylistsSelectorFragment;



public class MainActivity extends AppCompatActivity implements CategoriesSelectorFragment.OnCategorySelectedListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment frag, int target, boolean replace, boolean addToBackStack )
    {
        FragmentManager fm = getFragmentManager();
        if (fm.findFragmentById(target) == null) {
            FragmentTransaction transaction = fm.beginTransaction();
            if(replace){
                transaction.replace(target, frag);
            }else{
                transaction.add(target, frag);
            }

            if(addToBackStack) transaction.addToBackStack(null);

            transaction.commit();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.left_content) != null){
            CategoriesAdapter adapter = new CategoriesAdapter(this,null);
            CategoriesSelectorFragment fragment = new CategoriesSelectorFragment(adapter);
            setFragment(fragment, R.id.left_content, false, false);
        }
    }

    @Override
    public void onCategorySelected(String categoryId) {
        Bundle args = new Bundle();
        args.putString(PlaylistsSelectorFragment.ARG_CATEGORY_ID, categoryId);

    }
}
