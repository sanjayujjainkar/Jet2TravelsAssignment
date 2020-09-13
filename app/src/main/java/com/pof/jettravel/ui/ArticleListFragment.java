package com.pof.jettravel.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pof.jettravel.R;

public class ArticleListFragment extends Fragment {

    private ArticleListAdapter articleListAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView txtErrorMsg;
    private Callback callback;
    public static String TAG = "ArticleListFragment";

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
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity)context;
        callback = (Callback)mainActivity;
    }

    public interface Callback {
        void call(Bundle bundle);
    }

}
