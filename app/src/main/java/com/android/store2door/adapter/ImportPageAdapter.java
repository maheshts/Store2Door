package com.android.store2door.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.store2door.ImportDetailActivity;
import com.android.store2door.R;
import com.android.store2door.api.Callbacks.importProduct.DatumImportModel;
import com.android.store2door.api.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ImportPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;


    private List<DatumImportModel> mProductListModel;
    private Activity mContext;

    private boolean isLoadingAdded = false;

    public ImportPageAdapter(Activity context) {
        this.mContext = context;
        mProductListModel = new ArrayList<>();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View viewItem = inflater.inflate(R.layout.import_view_layout, parent, false);
                viewHolder = new ProductVH(viewItem);
                break;
            case LOADING:
                View viewLoading = inflater.inflate(R.layout.progress_item_loading, parent, false);
                viewHolder = new LoadingVH(viewLoading);
                break;
        }
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)) {
            case ITEM:
                ProductVH viewholder = (ProductVH) holder;
                DatumImportModel profileModel = mProductListModel.get(position);
                viewholder.tvProductTitle.setText(profileModel.getName());
                viewholder.tvProductDescription.setText(profileModel.getDescription());

                Picasso.get().load(Constant.BASEURL_image + profileModel.getImage())
                        .placeholder(R.drawable.splash_bg)
                        .error(R.drawable.splash_bg).into(viewholder.ivProductImage);


                viewholder.rlMainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mContext.startActivityForResult(new Intent(mContext, ImportDetailActivity.class)
                        .putExtra(Constant.ItemModel,profileModel),Constant.ActivityResult_1);
                    }
                });
                break;

            case LOADING:
                LoadingVH loadingVH = (LoadingVH) holder;

                loadingVH.mProgressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    //
    public List<DatumImportModel> updateList() {

        return mProductListModel;
    }

    @Override
    public int getItemCount() {
        return mProductListModel == null ? 0 : mProductListModel.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == mProductListModel.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }


    /*
        Helpers - Pagination
   _________________________________________________________________________________________________
    */

    public void add(DatumImportModel r) {
        mProductListModel.add(r);
        notifyItemInserted(mProductListModel.size() - 1);
    }

    public void addAll(List<DatumImportModel> moveResults) {
        for (DatumImportModel result : moveResults) {
            add(result);
        }
    }

    public void remove(DatumImportModel r) {
        int position = mProductListModel.indexOf(r);
        if (position > -1) {
            mProductListModel.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        isLoadingAdded = false;
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }


    public void addLoadingFooter() {
        isLoadingAdded = true;
        add(new DatumImportModel());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mProductListModel.size() - 1;
        DatumImportModel result = getItem(position);

        if (result != null) {
            mProductListModel.remove(position);
            notifyItemRemoved(position);
        }
    }

    public DatumImportModel getItem(int position) {
        return mProductListModel.get(position);
    }

    public List<DatumImportModel> getUpdateItem() {
        return mProductListModel;
    }


    /*
    View Holders
    _________________________________________________________________________________________________

     /**
      * Main list's content ViewHolder
      */
    public class ProductVH extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductTitle;
        TextView tvProductDescription;
        TextView tvProductPrice;
        TextView tvProductUnit;
        TextView tvProductQty;
        TextView etPriceProduct;
        RelativeLayout rlMainLayout;


        public ProductVH(@NonNull View itemView) {
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


    private static class LoadingVH extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;

        LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.progressBar);

        }
    }


}
