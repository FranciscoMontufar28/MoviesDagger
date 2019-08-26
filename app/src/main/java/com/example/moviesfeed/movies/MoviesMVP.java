package com.example.moviesfeed.movies;

import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.Observable;

public interface MoviesMVP {

    interface model{
        Observable<ViewModel> result();
    }

    interface view{
        void upDateData(ViewModel viewModel);
        void showSnackBar(String message);
    }

    interface presenter{
        void loadDate();
        void rxUnsuscribe();
        void setView(MoviesMVP.view view);
    }
}
