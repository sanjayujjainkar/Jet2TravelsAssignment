package com.pof.jettravel.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pof.jettravel.R;
import com.pof.jettravel.controller.ArticleViewModel;
import com.pof.jettravel.data.model.Repository;
import com.pof.jettravel.data.model.model.Article;

import java.util.List;

public class ArticleListFragment extends Fragment {

    private ArticleListAdapter articleListAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtErrorMsg;
    private Callback callback;
    public static String TAG = "ArticleListFragment";
    private int page=1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        articleListAdapter = new ArticleListAdapter(getActivity());
        progressBar = view.findViewById(R.id.progressBar);
        txtErrorMsg = view.findViewById(R.id.txtError);
        recyclerView = view.findViewById(R.id.commit_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(articleListAdapter);

        //====================
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1) && newState==RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("RecycleView","end");
                    Toast.makeText(getActivity(), "Loading next page...", Toast.LENGTH_SHORT).show();
                    Repository.getInstance().getArticles(++page);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ArticleViewModel viewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        viewModel.getArticleLiveData().observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(@Nullable List<Article> articles) {
                if(articles!=null) {
                    articleListAdapter.updateArticleList(articles);
                    Log.d("Article list fragment", "adapter updated with new list");
                } else {
                    ErrorHandling.showToast(getActivity(),"Something went wrong...Try again later");
                    txtErrorMsg.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity)context;
    }

    public interface Callback {
        void call(Bundle bundle);
    }

}
