package com.android.store2door.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.store2door.R;
import com.android.store2door.api.Callbacks.productImportList.DatumImportList;
import com.android.store2door.api.Constant;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImportListAdapter extends RecyclerView.Adapter<ImportListAdapter.ItemViewHolder> {


    private List<DatumImportList> responseDatumList;
    private Context mContext;

    private OnClickListener mOnClickListener;

    public ImportListAdapter(List<DatumImportList> mResponseDatumList, Context context, OnClickListener onClickListener) {

        this.responseDatumList = mResponseDatumList;
        this.mContext = context;
        this.mOnClickListener = onClickListener;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from
                (parent.getContext()).inflate(R.layout.import_view_layout, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder viewholder, final int position) {
        if (mContext == null) {
            return;
        }
        DatumImportList profileModel = responseDatumList.get(position);
        viewholder.tvProductTitle.setText(profileModel.getProduct().getName());
        viewholder.tvProductDescription.setText(profileModel.getProduct().getDescription());
        viewholder.tvProductPrice.setText(mContext.getResources().getString(R.string.rs)+profileModel.getPrice());
        viewholder.tvProductUnit.setText("(" + profileModel.getUnit().getUnit() + ")");
        viewholder.tvProductQty.setText(profileModel.getQty());

        Picasso.get().load(Constant.BASEURL_image + profileModel.getProduct().getImage())
                .placeholder(R.drawable.splash_bg)
                .error(R.drawable.splash_bg).into(viewholder.ivProductImage);

        viewholder.rlMainLayout.setOnClickListener(view -> {
            if (mOnClickListener != null) {
                mOnClickListener.clickLayout(viewholder.getAdapterPosition());
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
        ImageView ivProductImage;
        TextView tvProductTitle;
        TextView tvProductDescription;
        TextView tvProductPrice;
        TextView tvProductUnit;
        TextView tvProductQty;
        TextView etPriceProduct;
        RelativeLayout rlMainLayout;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductUnit = itemView.findViewById(R.id.tvProductUnit);
            tvProductQty = itemView.findViewById(R.id.tvProductQty);
            etPriceProduct = itemView.findViewById(R.id.etPriceProduct);
            rlMainLayout = itemView.findViewById(R.id.rlMainLayout);

        }
    }


}