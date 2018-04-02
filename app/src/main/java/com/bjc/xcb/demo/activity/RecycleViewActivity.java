package com.bjc.xcb.demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bjc.xcb.demo.MyOnItemClickListener;
import com.bjc.xcb.demo.R;
import com.bjc.xcb.demo.util.LogX;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends AppCompatActivity {
    private static final String TAG = "xcb";
    private List<String> mDatas = new ArrayList<>();
    private RecyclerView mRecycleView;
    private MyRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogX.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recycledview);

        initView();
    }

    private void initView() {
        LogX.d(TAG, "initView");
        for (int i = 0; i < 20; i++) {
            mDatas.add("String" + i);
        }

        mRecycleView = findViewById(R.id.id_recyclerview);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MyRecyclerViewAdapter();
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        mRecycleView.setItemAnimator(new DefaultItemAnimator());

        mAdapter.setOnItemClickLitener(new MyOnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this,  "click me:" + position,
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(RecycleViewActivity.this,  "long click me:" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {
        private MyOnItemClickListener mMyOnItemClickLitener;

        public void setOnItemClickLitener(MyOnItemClickListener mOnItemClickLitener) {
            this.mMyOnItemClickLitener = mOnItemClickLitener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, parent, false);
            MyViewHolder myViewHolder = new MyViewHolder(itemView);

            LogX.d(TAG, "onCreateViewHolder, viewType:" + viewType);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            LogX.d(TAG, "onBindViewHolder, position:" + position);
            holder.textView.setText(mDatas.get(position));
            if (null != mMyOnItemClickLitener) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMyOnItemClickLitener.onItemClick(holder.itemView, holder.getLayoutPosition());
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mMyOnItemClickLitener.onItemLongClick(holder.itemView, holder.getLayoutPosition());
                        return true;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            LogX.d(TAG, "getItemCount:" + mDatas.size());
            return mDatas.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public MyViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.id_num);
                LogX.d(TAG, "MyViewHolder construct");
            }
        }

        public void addData(int position) {
            mDatas.add(position, "Insert One");
            notifyItemInserted(position);
        }

        public void removeData(int position) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add: {
                mAdapter.addData(1);
                break;
            }
            case R.id.delete: {
                mAdapter.removeData(1);
                break;
            }
        }
        return true;
    }
}
