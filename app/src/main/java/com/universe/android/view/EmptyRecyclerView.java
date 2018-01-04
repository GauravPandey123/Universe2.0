package com.universe.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.universe.android.R;
import com.universe.android.utility.Utility;


/**
 * Created by tanshul on 17/1/17.
 */
public class EmptyRecyclerView extends android.support.v7.widget.RecyclerView {

    // Declare all views here
    LinearLayout llNoNet;
    LinearLayout llNoData;
    ProgressBar pbLoadData;
    ProgressBar pbLoadMore;
    RelativeLayout rlEmptyList;

    public EmptyRecyclerView(Context context) {
        super(context);
        initViews();
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public EmptyRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        // Do nothing
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX *= 0.7;
        return super.fling(velocityX, velocityY);
    }

//    public void setEmptyView(View emptyView) {
//        pbLoadData = (ProgressBar) emptyView.findViewById(R.id.pb_load_data);
//        pbLoadMore = (ProgressBar) emptyView.findViewById(R.id.pb_load_more);
//        rlEmptyList = (RelativeLayout) emptyView.findViewById(R.id.rl_list_empty);
//        llNoData = (LinearLayout) emptyView.findViewById(R.id.ll_no_data);
//        llNoNet = (LinearLayout) emptyView.findViewById(R.id.ll_no_net);
//    }

    @Override
    public void setAdapter(Adapter adapter) {
        final Adapter oldAdapter = getAdapter();
        if (oldAdapter != null) {
            oldAdapter.unregisterAdapterDataObserver(observer);
        }
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(observer);
        }
    }

    final private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

    private void checkIfEmpty() {
        if (getAdapter() != null) {
            if (getAdapter().getItemCount() == 0) {
                showEmptyView(false);
            } else {
                if (rlEmptyList != null) {
                    rlEmptyList.setVisibility(GONE);
                }
                setVisibility(VISIBLE);
            }
        }
    }

    public void showLoadingView() {
        setVisibility(GONE);
        rlEmptyList.setVisibility(GONE);
        pbLoadMore.setVisibility(GONE);
        pbLoadData.setVisibility(VISIBLE);
    }

    public void showLoadMore() {
        pbLoadMore.setVisibility(VISIBLE);
    }

    public void hideLoadingView() {
        pbLoadData.setVisibility(GONE);
        pbLoadMore.setVisibility(GONE);
        checkIfEmpty();
    }

    public void showEmptyView(boolean retry) {
        setVisibility(GONE);
        try {
            pbLoadData.setVisibility(GONE);
            rlEmptyList.setVisibility(VISIBLE);
            if (retry) {
                setRetryView();
            } else {
                if (!Utility.isConnected()) {
                    setRetryView();
                } else {
                    llNoNet.setVisibility(GONE);
                    llNoData.setVisibility(VISIBLE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setRetryView() {
        llNoData.setVisibility(GONE);
        llNoNet.setVisibility(VISIBLE);
    }
}
