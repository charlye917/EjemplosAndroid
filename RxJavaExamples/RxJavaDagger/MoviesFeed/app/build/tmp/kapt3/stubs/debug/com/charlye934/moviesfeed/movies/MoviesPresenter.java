package com.charlye934.moviesfeed.movies;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/charlye934/moviesfeed/movies/MoviesPresenter;", "Lcom/charlye934/moviesfeed/movies/MoviesMVP$Presenter;", "model", "Lcom/charlye934/moviesfeed/movies/MoviesMVP$Model;", "(Lcom/charlye934/moviesfeed/movies/MoviesMVP$Model;)V", "subscription", "Lio/reactivex/rxjava3/disposables/Disposable;", "view", "Lcom/charlye934/moviesfeed/movies/MoviesMVP$View;", "loadData", "", "rxJavaUnsuscribe", "setView", "app_debug"})
public final class MoviesPresenter implements com.charlye934.moviesfeed.movies.MoviesMVP.Presenter {
    private com.charlye934.moviesfeed.movies.MoviesMVP.View view;
    private io.reactivex.rxjava3.disposables.Disposable subscription;
    private com.charlye934.moviesfeed.movies.MoviesMVP.Model model;
    
    @java.lang.Override()
    public void loadData() {
    }
    
    @java.lang.Override()
    public void rxJavaUnsuscribe() {
    }
    
    @java.lang.Override()
    public void setView(@org.jetbrains.annotations.NotNull()
    com.charlye934.moviesfeed.movies.MoviesMVP.View view) {
    }
    
    public MoviesPresenter(@org.jetbrains.annotations.NotNull()
    com.charlye934.moviesfeed.movies.MoviesMVP.Model model) {
        super();
    }
}