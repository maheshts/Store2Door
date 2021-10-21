package com.android.store2door.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.store2door.R;
import com.android.store2door.api.Callbacks.pendingOrder.Datum;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PendingListAdapter extends RecyclerView.Adapter<PendingListAdapter.ItemViewHolder> {


    private List<Datum> responseDatumList;
    private Context mContext;

    private boolean isFRomDashBoard;

    private OnClickListener mOnClickListener;

    public PendingListAdapter(List<Datum> mResponseDatumList, Context context, OnClickListener onClickListener
            , boolean isDashBoard) {

        this.responseDatumList = mResponseDatumList;
        this.isFRomDashBoard = isDashBoard;
        this.mContext = context;
        this.mOnClickListener = onClickListener;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from
                (parent.getContext()).inflate(R.layout.pending_list_layout, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        if (mContext == null) {
            return;
        }
        Datum datum = responseDatumList.get(position);

        holder.tvProductTitle.setText(datum.getFranchise().getTitle());
        holder.tvProductDescription.setText(String.valueOf(datum.getNumberOfItem()));
        holder.tvProductPrice.setText(mContext.getResources().getString(R.string.rs) + datum.getTotal());

        if (isFRomDashBoard) {
            holder.order_status_tv.setVisibility(View.GONE);
        } else {
            holder.order_status_tv.setVisibility(View.VISIBLE);
            holder.order_status_tv.setText(getOrderStatusName(datum.getStatus()));
            holder.order_status_tv.setBackground(getOrderStatusColor(datum.getStatus()));

        }
        holder.cvMainLayout.setOnClickListener(view -> {

            if (mOnClickListener != null) {
                mOnClickListener.clickLayout(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return responseDatumList.size();
    }


    public interface OnClickListener {
        void clickLayout(int position);
    }

    private String getOrderStatusName(int id) {
        String status = "Completed / Delivered";
        if (id == 1) {
            status = "Accepted Order";
        } else if (id == 2) {
            status = "Franchise Decline";
        } else if (id == 3) {
            status = "Waiting for vendor action";
        } else if (id == 4) {
            status = "Confirmed By Vendor";
        } else if (id == 5) {
            status = "Vendor Decline";
        } else if (id == 6) {
            status = "Completed / Delivered";
        }
        return status;

    }

    private Drawable getOrderStatusColor(int id) {
        Drawable status = mContext.getResources().getDrawable(R.drawable.round_corner_8_green);
        if (id == 1) {
            status = mContext.getResources().getDrawable(R.drawable.round_corner_8_green);
        } else if (id == 2) {
            status = mContext.getResources().getDrawable(R.drawable.round_corner_8_red);
        } else if (id == 3) {
            status = mContext.getResources().getDrawable(R.drawable.round_corner_8_orange);
        } else if (id == 4) {
            status = mContext.getResources().getDrawable(R.drawable.round_corner_8_green);
        } else if (id == 5) {
            status = mContext.getResources().getDrawable(R.drawable.round_corner_8_red);
        } else if (id == 6) {
            status = mContext.getResources().getDrawable(R.drawable.round_corner_8_green);
        }
        return status;

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {


        CardView cvMainLayout;
        ImageView ivProductImage;
        TextView tvProductPrice;
        TextView order_status_tv;
        TextView tvProductDescription;
        TextView tvProductTitle;

        ItemViewHolder(View itemView) {
            super(itemView);

            cvMainLayout = itemView.findViewById(R.id.cvMainLayout);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            order_status_tv = itemView.findViewById(R.id.order_status_tv);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
        }
    }

}