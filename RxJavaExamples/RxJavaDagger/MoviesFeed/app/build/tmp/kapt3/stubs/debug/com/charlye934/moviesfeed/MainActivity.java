package com.charlye934.moviesfeed;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0014J\b\u0010\u0016\u001a\u00020\u0012H\u0014J\u0010\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0005H\u0016J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u0010H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u00020\t8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/charlye934/moviesfeed/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/charlye934/moviesfeed/movies/MoviesMVP$View;", "()V", "TAG", "", "listAdapter", "Lcom/charlye934/moviesfeed/ListAdapter;", "presenter", "Lcom/charlye934/moviesfeed/movies/MoviesMVP$Presenter;", "getPresenter", "()Lcom/charlye934/moviesfeed/movies/MoviesMVP$Presenter;", "setPresenter", "(Lcom/charlye934/moviesfeed/movies/MoviesMVP$Presenter;)V", "resultList", "", "Lcom/charlye934/moviesfeed/movies/ViewModel;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onStop", "showSnackBar", "s", "updateData", "viewmodel", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity implements com.charlye934.moviesfeed.movies.MoviesMVP.View {
    private final java.lang.String TAG = null;
    private com.charlye934.moviesfeed.ListAdapter listAdapter;
    private java.util.List<com.charlye934.moviesfeed.movies.ViewModel> resultList;
    @org.jetbrains.annotations.NotNull()
    @javax.inject.Inject()
    public com.charlye934.moviesfeed.movies.MoviesMVP.Presenter presenter;
    private java.util.HashMap _$_findViewCache;
    
    @org.jetbrains.annotations.NotNull()
    public final com.charlye934.moviesfeed.movies.MoviesMVP.Presenter getPresenter() {
        return null;
    }
    
    public final void setPresenter(@org.jetbrains.annotations.NotNull()
    com.charlye934.moviesfeed.movies.MoviesMVP.Presenter p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void updateData(@org.jetbrains.annotations.NotNull()
    com.charlye934.moviesfeed.movies.ViewModel viewmodel) {
    }
    
    @java.lang.Override()
    public void showSnackBar(@org.jetbrains.annotations.NotNull()
    java.lang.String s) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    public MainActivity() {
        super();
    }
}