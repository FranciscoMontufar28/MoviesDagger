package com.example.moviesfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

import com.example.moviesfeed.movies.ListAdapter;
import com.example.moviesfeed.movies.MoviesMVP;
import com.example.moviesfeed.movies.ViewModel;
import com.example.moviesfeed.root.App;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MoviesMVP.view {

    private final String TAG = MainActivity.class.getName();

    @BindView(R.id.activity_root_view)
    ViewGroup viewGroup;
    @BindView(R.id.recycler_view_movies)
    RecyclerView recyclerView;

    @Inject
    MoviesMVP.presenter presenter;

    private ListAdapter listAdapter;
    private List<ViewModel> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App)getApplication()).getApplicationComponent().inject(this);

        listAdapter = new ListAdapter(resultList);
        recyclerView.setAdapter(listAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadDate();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.rxUnsuscribe();
        resultList.clear();
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void upDateData(ViewModel viewModel) {
        resultList.add(viewModel);
        listAdapter.notifyItemChanged(resultList.size()-1);
        Log.d(TAG,"Informacion nueva: "+ viewModel.getTitle());
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(viewGroup,message,Snackbar.LENGTH_SHORT).show();
    }
}
