package com.charlye934.moviesfeed.movies;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0003\u0002\u0003\u0004\u00a8\u0006\u0005"}, d2 = {"Lcom/charlye934/moviesfeed/movies/MoviesMVP;", "", "Model", "Presenter", "View", "app_debug"})
public abstract interface MoviesMVP {
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&\u00a8\u0006\t"}, d2 = {"Lcom/charlye934/moviesfeed/movies/MoviesMVP$View;", "", "showSnackBar", "", "s", "", "updateData", "viewmodel", "Lcom/charlye934/moviesfeed/movies/ViewModel;", "app_debug"})
    public static abstract interface View {
        
        public abstract void updateData(@org.jetbrains.annotations.NotNull()
        com.charlye934.moviesfeed.movies.ViewModel viewmodel);
        
        public abstract void showSnackBar(@org.jetbrains.annotations.NotNull()
        java.lang.String s);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/charlye934/moviesfeed/movies/MoviesMVP$Presenter;", "", "loadData", "", "rxJavaUnsuscribe", "setView", "view", "Lcom/charlye934/moviesfeed/movies/MoviesMVP$View;", "app_debug"})
    public static abstract interface Presenter {
        
        public abstract void loadData();
        
        public abstract void rxJavaUnsuscribe();
        
        public abstract void setView(@org.jetbrains.annotations.NotNull()
        com.charlye934.moviesfeed.movies.MoviesMVP.View view);
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H&\u00a8\u0006\u0005"}, d2 = {"Lcom/charlye934/moviesfeed/movies/MoviesMVP$Model;", "", "result", "Lio/reactivex/rxjava3/core/Observable;", "Lcom/charlye934/moviesfeed/movies/ViewModel;", "app_debug"})
    public static abstract interface Model {
        
        @org.jetbrains.annotations.NotNull()
        public abstract io.reactivex.rxjava3.core.Observable<com.charlye934.moviesfeed.movies.ViewModel> result();
    }
}