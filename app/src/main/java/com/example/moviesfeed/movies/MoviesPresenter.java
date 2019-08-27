package com.example.moviesfeed.movies;


import io.reactivex.disposables.Disposable;

public class MoviesPresenter implements MoviesMVP.presenter {
    private MoviesMVP.view view;
    private MoviesMVP.model model;

    private Disposable subscription = null;

    public MoviesPresenter(MoviesMVP.model model){
        this.model = model;
    }

    @Override
    public void loadDate() {

    }

    @Override
    public void rxUnsuscribe() {
        if (subscription != null && !subscription.isDisposed()){
            subscription.dispose();
        }
    }

    @Override
    public void setView(MoviesMVP.view view) {
        this.view = view;
    }
}
