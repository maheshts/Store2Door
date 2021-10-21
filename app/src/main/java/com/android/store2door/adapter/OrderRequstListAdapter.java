package com.android.store2door.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.store2door.R;
import com.android.store2door.api.Callbacks.orderRequest.DatumOrderRequest;
import com.android.store2door.api.Callbacks.pendingOrder.Datum;
import com.android.store2door.api.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class OrderRequstListAdapter extends RecyclerView.Adapter<OrderRequstListAdapter.ItemViewHolder> {


    private List<DatumOrderRequest> responseDatumList;
    private Context mContext;

    private OnClickListener mOnClickListener;

    public OrderRequstListAdapter(List<DatumOrderRequest> mResponseDatumList, Context context, OnClickListener onClickListener) {

        this.responseDatumList = mResponseDatumList;
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
        DatumOrderRequest datum = responseDatumList.get(position);

        holder.tvProductTitle.setText(datum.getFranchise().getTitle());
        holder.tvProductDescription.setText(String.valueOf(datum.getNumberOfItem()));
        holder.tvProductPrice.setText(mContext.getResources().getString(R.string.rs) + datum.getTotal());

           holder.cvMainLayout.setOnClickListener(view -> {

            if (mOnClickListener != null){
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


    static class ItemViewHolder extends RecyclerView.ViewHolder {


        CardView cvMainLayout;
        ImageView ivProductImage;
        TextView tvProductPrice;
        TextView tvProductDescription;
        TextView tvProductTitle;

        ItemViewHolder(View itemView) {
            super(itemView);

            cvMainLayout = itemView.findViewById(R.id.cvMainLayout);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
        }
    }

}