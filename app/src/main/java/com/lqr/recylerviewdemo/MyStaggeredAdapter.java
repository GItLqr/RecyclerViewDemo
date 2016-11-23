package com.lqr.recylerviewdemo;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 CSDN_LQR
 * @描述 瀑布流适配器（主要是控制控件的高度，需要配合StaggeredLayoutManager使用才会有瀑布流效果）
 */
class MyStaggeredAdapter extends RecyclerView.Adapter<MyStaggeredAdapter.MyStaggeredViewHolder> {

    private Context mContext;
    private List<String> mData;
    private List<Integer> mHeight = new ArrayList<>();
    public int select = 0;

    public MyStaggeredAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public MyStaggeredViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_text, null);
        return new MyStaggeredViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyStaggeredViewHolder holder, final int position) {
        //随机生产控件高度值
        if (mHeight.size() <= position) {
            mHeight.add((int) (100 + Math.random() * 300));
        }

        //设置文本控件的高度
        ViewGroup.LayoutParams lp = holder.mTv.getLayoutParams();
        lp.height = mHeight.get(position);
        holder.mTv.setLayoutParams(lp);

        //设置文本内容
        holder.mTv.setText(mData.get(position));

        if (select == position) {
            holder.mTv.setBackgroundColor(Color.RED);
        } else {
            holder.mTv.setBackgroundColor(Color.WHITE);
        }

        holder.mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                select = position;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyStaggeredViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;

        public MyStaggeredViewHolder(View itemView) {
            super(itemView);
            mTv = (TextView) itemView.findViewById(R.id.tv);
        }
    }

    public void remove(int position) {
        mData.remove(position);
        this.notifyItemRemoved(position);

        //使用下面的刷新方法没有动画效果
//        this.notifyDataSetChanged();
    }
}
