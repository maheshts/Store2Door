package com.android.store2door.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.store2door.R;
import com.android.store2door.api.Callbacks.orderRequest.StockItemOrderRequest;
import com.android.store2door.api.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReportStockListAdapter extends RecyclerView.Adapter<ReportStockListAdapter.ItemViewHolder> {


    private List<StockItemOrderRequest> responseStockItemOrderRequestList;
    private Context mContext;

    public ReportStockListAdapter(List<StockItemOrderRequest> mResponseStockItemOrderRequestList, Context context) {

        this.responseStockItemOrderRequestList = mResponseStockItemOrderRequestList;
        this.mContext = context;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from
                (parent.getContext()).inflate(R.layout.product_list_layout, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        if (mContext == null) {
            return;
        }
        StockItemOrderRequest stockItem = responseStockItemOrderRequestList.get(position);

        Picasso.get().load(Constant.BASEURL_image + stockItem.getProduct().getImage())
                .error(R.drawable.splash_bg).placeholder(R.drawable.splash_bg).into(holder.ivProductImage);

        holder.tvProductTitle.setText(String.valueOf(stockItem.getProduct().getName()));
        holder.tvProductPrice.setText(mContext.getResources().getString(R.string.rs) + stockItem.getShopUnitPrice());
        holder.tvProductQty.setText(mContext.getResources().getString(R.string.qty) + stockItem.getShopQuantity());
        holder.tvProductUnit.setText(stockItem.getUnitQuantity() + " " + stockItem.getUnitType());

    }


    @Override
    public int getItemCount() {
        return responseStockItemOrderRequestList.size();
    }


    static class ItemViewHolder extends RecyclerView.ViewHolder {


        ImageView ivProductImage;
        TextView tvProductPrice;
        TextView tvProductQty;
        TextView tvProductTitle;
        TextView tvProductUnit;

        ItemViewHolder(View itemView) {
            super(itemView);

            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductQty = itemView.findViewById(R.id.tvProductQty);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
            tvProductUnit = itemView.findViewById(R.id.tvProductUnit);
        }
    }

}