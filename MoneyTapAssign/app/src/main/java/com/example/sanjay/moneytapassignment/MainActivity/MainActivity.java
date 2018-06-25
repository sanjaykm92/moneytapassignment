package com.example.sanjay.moneytapassignment.MainActivity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sanjay.moneytapassignment.MainActivity.presenter.MainAccPresenterImpl;
import com.example.sanjay.moneytapassignment.MainActivity.view.MainActivityView;
import com.example.sanjay.moneytapassignment.R;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    private RecyclerView mRecyclerView;
    private EditText mEditText;
    private MainAccPresenterImpl mainAccPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainAccPresenter = new MainAccPresenterImpl();
        mainAccPresenter.init(this);
        mainAccPresenter.onStart();

        mRecyclerView = (RecyclerView) findViewById(R.id.recylrview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mEditText = (EditText) findViewById(R.id.custom_toolbar_title);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                toggleSearchVisibility(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mainAccPresenter.PingServer(s.toString().trim());
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
            default:
                break;
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean isAdapterNuull() {
        return mRecyclerView.getAdapter() == null;
    }

    @Override
    public void refreshAdapter() {
        mRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void setRecycAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public Context getContext1() {
        return this;
    }

    @Override
    public void showTaost(String string) {
        Toast.makeText(this,string,Toast.LENGTH_SHORT).show();
    }
}
