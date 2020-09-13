package com.pof.jettravel.controller;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.pof.jettravel.data.model.Repository;
import com.pof.jettravel.data.model.model.Article;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel {

    private final LiveData<List<Article>> liveDataArticles;

    public ArticleViewModel(@NonNull Application application) {
        super(application);
        liveDataArticles = Repository.getInstance().getArticles();
    }

    public LiveData<List<Article>> getArticleLiveData() {
        return liveDataArticles;
    }
}
