package com.android.store2door.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.store2door.R;
import com.android.store2door.api.Callbacks.getProduct.DatumProduct;
import com.android.store2door.api.Callbacks.getProduct.PriceProduct;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.CommonFunction;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class ProductPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Types
    private static final int ITEM = 0;
    private static final int LOADING = 1;


    private List<DatumProduct> mProductListModel;
    private Activity mContext;

    private boolean isLoadingAdded = false;

    public ProductPageAdapter(Activity context) {
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
                View viewItem = inflater.inflate(R.layout.product_view_layout, parent, false);
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
                DatumProduct profileModel = mProductListModel.get(position);
                viewholder.tvProductTitle.setText(profileModel.getName());
                viewholder.tvProductDescription.setText(profileModel.getDescription());

                Picasso.get().load(Constant.BASEURL_image + profileModel.getImage())
                        .placeholder(R.drawable.splash_bg)
                        .error(R.drawable.splash_bg).into(viewholder.ivProductImage);


                boolean isAddedValue = true;
                for (int i = 0; i < profileModel.getPrice().size(); i++) {
                    if (profileModel.getPrice().get(i).getIsAdded().equalsIgnoreCase("0")) {
                        isAddedValue = false;
                    }
                }

                if (isAddedValue) {
                    viewholder.llMainLayoutProduct.setEnabled(false);
                    viewholder.viewViewLayoutDisable.setVisibility(View.VISIBLE);
                    viewholder.tvAlreadyAdded.setVisibility(View.VISIBLE);
                } else {
                    viewholder.llMainLayoutProduct.setEnabled(true);
                    viewholder.viewViewLayoutDisable.setVisibility(View.GONE);
                    viewholder.tvAlreadyAdded.setVisibility(View.GONE);
                }


                viewholder.cbCheckBoxProduct.setChecked(profileModel.isSelected());


                viewholder.cbCheckBoxProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mProductListModel.get(position).setSelected(!viewholder.cbCheckBoxProduct.isSelected());
                        if (viewholder.cbCheckBoxProduct.isChecked()) {
                            ChoiceAlert(position, profileModel.getName(), profileModel.getPrice());

                        }
                    }
                });
//                viewholder.cbCheckBoxProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                        mProductListModel.get(position).setSelected(b);
//                        if (b) {
//                            ChoiceAlert(position, profileModel.getName(), profileModel.getPrice());
//                        }
//                    }
//                });


                ((ProductVH) holder).llTopLayoutProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ChoiceAlert(position, profileModel.getName(), profileModel.getPrice());
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
    public List<DatumProduct> updateList() {

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

    public void add(DatumProduct r) {
        mProductListModel.add(r);
        notifyItemInserted(mProductListModel.size() - 1);
    }

    public void addAll(List<DatumProduct> moveResults) {
        for (DatumProduct result : moveResults) {
            add(result);
        }
    }

    public void remove(DatumProduct r) {
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
        add(new DatumProduct());
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        int position = mProductListModel.size() - 1;
        DatumProduct result = getItem(position);

        if (result != null) {
            mProductListModel.remove(position);
            notifyItemRemoved(position);
        }
    }

    public DatumProduct getItem(int position) {
        return mProductListModel.get(position);
    }

    public List<DatumProduct> getUpdateItem() {
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

        LinearLayout llMainLayoutProduct;
        RelativeLayout llTopLayoutProduct;
        CheckBox cbCheckBoxProduct;
        View viewViewLayoutDisable;
        TextView tvAlreadyAdded;

        public ProductVH(@NonNull View itemView) {
            super(itemView);

            llTopLayoutProduct = itemView.findViewById(R.id.llTopLayoutProduct);
            cbCheckBoxProduct = itemView.findViewById(R.id.cbCheckBoxProduct);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            llMainLayoutProduct = itemView.findViewById(R.id.llMainLayoutProduct);
            tvAlreadyAdded = itemView.findViewById(R.id.tvAlreadyAdded);
            viewViewLayoutDisable = itemView.findViewById(R.id.viewViewLayoutDisable);


        }
    }


    private static class LoadingVH extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;

        LoadingVH(View itemView) {
            super(itemView);

            mProgressBar = itemView.findViewById(R.id.progressBar);

        }
    }

    private void ChoiceAlert(int position, String productName, List<PriceProduct> priceProductList) {
        Dialog dialog = new Dialog(mContext, R.style.CustomAlertDialogStyle);


        if (dialog.getWindow() != null) {
            dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
        dialog.setContentView(R.layout.price_select_popup);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }


        TextView tvProductTitle = dialog.findViewById(R.id.tvProductTitle);
        tvProductTitle.setText(productName);
        RecyclerView rvPriceList = dialog.findViewById(R.id.rvPriceList);

        TextView btDoneButton = dialog.findViewById(R.id.btDoneButton);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(final DialogInterface arg0) {
                // do something
                btDoneButton.callOnClick();
            }
        });


        if (rvPriceList.getLayoutManager() == null) {
            rvPriceList.setLayoutManager(new LinearLayoutManager(mContext));
            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
            rvPriceList.addItemDecoration(itemDecoration);
        }

        PriceListListAdapter multiChoiceListAdapter = new PriceListListAdapter(priceProductList, mContext);
        rvPriceList.setAdapter(multiChoiceListAdapter);

        btDoneButton.setOnClickListener(v -> {
//            selectSupplierModel.setItemModel(multiChoiceListAdapter.updateModel());
//            viewVisible(selectSupplierModel, false);


            boolean isValid = false;
            List<PriceProduct> updatePriceList = multiChoiceListAdapter.updateList();
            for (int i = 0; i < updatePriceList.size(); i++) {
                if (TextUtils.isEmpty(updatePriceList.get(i).getPrice()) || updatePriceList.get(i).getPrice().equalsIgnoreCase("0")) {
                    isValid = false;
                    CommonFunction.showToastSingle(mContext, "Please enter valid price");

                } else if (TextUtils.isEmpty(updatePriceList.get(i).getNewquantity())
                        || updatePriceList.get(i).getNewquantity().equalsIgnoreCase("0")) {
                    isValid = false;
                    CommonFunction.showToastSingle(mContext, "Please enter valid quantity");

                } else {
                    isValid = true;

                }
            }
            if (isValid) {
                boolean isSelectedValue = false;
                for (int i = 0; i < priceProductList.size(); i++) {
                    if (priceProductList.get(i).isSelected()) {
                        isSelectedValue = true;
                    }
                }

                if (isSelectedValue) {
                    mProductListModel.get(position).setSelected(true);

                } else {
                    mProductListModel.get(position).setSelected(false);

                }

                notifyDataSetChanged();
                mProductListModel.get(position).setPrice(updatePriceList);
                mProductListModel.set(position, mProductListModel.get(position));

                dialog.dismiss();
            }


        });

        dialog.show();
    }


}
