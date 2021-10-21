package com.android.store2door.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.store2door.R;
import com.android.store2door.api.Callbacks.getProduct.PriceProduct;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PriceListListAdapter extends RecyclerView.Adapter<PriceListListAdapter.ItemViewHolder> {


    private List<PriceProduct> responsePriceProductList;
    private Context mContext;

    public PriceListListAdapter(List<PriceProduct> mResponsePriceProductList, Context context) {

        this.responsePriceProductList = mResponsePriceProductList;
        this.mContext = context;

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from
                (parent.getContext()).inflate(R.layout.price_item_layout, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder viewholder, final int position) {
        if (mContext == null) {
            return;
        }
        PriceProduct profileModel = responsePriceProductList.get(position);

        viewholder.tvProductUnit.setText("(" + profileModel.getQuantity() + profileModel.getUnit() + ")");
        viewholder.etQtyProduct.setText(profileModel.getNewquantity());
        viewholder.etPriceProduct.setText(profileModel.getPrice());

        if (profileModel.getIsAdded().equalsIgnoreCase("1")) {
            viewholder.llMainLayoutPrice.setEnabled(false);
            viewholder.viewViewLayoutDisable.setVisibility(View.VISIBLE);
            viewholder.llPriceLayout.setVisibility(View.INVISIBLE);
            viewholder.tvAlreadyAdded.setVisibility(View.VISIBLE);
            viewholder.cbCheckBoxProduct.setEnabled(false);
            viewholder.etPriceProduct.setEnabled(false);
        } else {
            viewholder.cbCheckBoxProduct.setEnabled(true);
            viewholder.etPriceProduct.setEnabled(true);
            viewholder.llMainLayoutPrice.setEnabled(true);
            viewholder.viewViewLayoutDisable.setVisibility(View.GONE);
            viewholder.llPriceLayout.setVisibility(View.VISIBLE);
            viewholder.tvAlreadyAdded.setVisibility(View.INVISIBLE);
        }

        viewholder.cbCheckBoxProduct.setChecked(profileModel.isSelected());


        viewholder.cbCheckBoxProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                responsePriceProductList.get(position).setSelected(!viewholder.cbCheckBoxProduct.isSelected());

            }
        });

        viewholder.etQtyProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (!TextUtils.isEmpty(charSequence))
                responsePriceProductList.get(position).setNewquantity(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        viewholder.etPriceProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (!TextUtils.isEmpty(charSequence))
                    responsePriceProductList.get(position).setPrice(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        viewholder.cbCheckBoxProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                responsePriceProductList.get(position).setSelected(b);

            }
        });
    }


    @Override
    public int getItemCount() {
        return responsePriceProductList.size();
    }

    //
    public List<PriceProduct> updateList() {

        return responsePriceProductList;
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        public EditText etQtyProduct;
        public EditText etPriceProduct;
        CheckBox cbCheckBoxProduct;
        TextView tvProductQty;
        TextView tvProductUnit;
        TextView tvProductPrice;
        LinearLayout llPriceLayout;
        LinearLayout llMainLayoutPrice;
        TextView tvAlreadyAdded;
        View viewViewLayoutDisable;

        ItemViewHolder(View itemView) {
            super(itemView);

            cbCheckBoxProduct = itemView.findViewById(R.id.cbCheckBoxProduct);
            viewViewLayoutDisable = itemView.findViewById(R.id.viewViewLayoutDisable);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductUnit = itemView.findViewById(R.id.tvProductUnit);
            tvProductQty = itemView.findViewById(R.id.tvProductQty);
            etQtyProduct = itemView.findViewById(R.id.etQtyProduct);
            etPriceProduct = itemView.findViewById(R.id.etPriceProduct);
            tvAlreadyAdded = itemView.findViewById(R.id.tvAlreadyAdded);
            llPriceLayout = itemView.findViewById(R.id.llPriceLayout);
            llMainLayoutPrice = itemView.findViewById(R.id.llMainLayoutPrice);
        }
    }

}