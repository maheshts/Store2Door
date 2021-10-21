package com.android.store2door.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.store2door.ImportDetailActivity;
import com.android.store2door.R;
import com.android.store2door.api.Callbacks.importProduct.ShopproductImportModel;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.CommonFunction;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImportPriceListAdapter extends RecyclerView.Adapter<ImportPriceListAdapter.ItemViewHolder> {


    private List<ShopproductImportModel> responseDatumList;
    private Context mContext;

    private OnClickListener mOnClickListener;

    public ImportPriceListAdapter(List<ShopproductImportModel> mResponseDatumList, Context context) {

        this.responseDatumList = mResponseDatumList;
        this.mContext = context;
//        this.mOnClickListener = onClickListener;

    }

    public void updateList(List<ShopproductImportModel> list) {
        this.responseDatumList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from
                (parent.getContext()).inflate(R.layout.update_import_layout, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder viewholder, final int position) {
        if (mContext == null) {
            return;
        }
        ShopproductImportModel datum = responseDatumList.get(position);
        viewholder.etProductPrice.setText(datum.getPrice());
        viewholder.tvProductUnit.setText("(" + datum.getUnit().getQuantity() +" "+datum.getUnit().getUnit() + ")");
        viewholder.etQtyProduct.setText(datum.getQty());

        viewholder.etQtyProduct.setSelection(viewholder.etQtyProduct.getText().length());
        viewholder.etProductPrice.setSelection(viewholder.etProductPrice.getText().length());


        viewholder.tvUpdateImport.setVisibility(View.INVISIBLE);

        viewholder.tvDeleteImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((ImportDetailActivity) mContext).DeliveryDialog(false, datum.getId(), "0", "0");

            }
        });

        viewholder.etProductPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equalsIgnoreCase(datum.getPrice())) {
                    viewholder.tvUpdateImport.setVisibility(View.INVISIBLE);
                } else {
                    viewholder.tvUpdateImport.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        viewholder.etQtyProduct.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equalsIgnoreCase(datum.getQty())) {
                    viewholder.tvUpdateImport.setVisibility(View.INVISIBLE);
                } else {
                    viewholder.tvUpdateImport.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        viewholder.tvUpdateImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String priceValue = viewholder.etProductPrice.getText().toString();
                String qtyValue = viewholder.etQtyProduct.getText().toString();
                if (TextUtils.isEmpty(priceValue) || priceValue.equalsIgnoreCase("0")) {
                    CommonFunction.showToastSingle(mContext, mContext.getResources().getString(R.string.valid_price));
                } else if (TextUtils.isEmpty(qtyValue) || qtyValue.equalsIgnoreCase("0")) {
                    CommonFunction.showToastSingle(mContext, mContext.getResources().getString(R.string.valid_qty));
                } else {

//                    System.out.println(priceValue + " sfsfdfsf " + qtyValue);
                    ((ImportDetailActivity) mContext).DeliveryDialog(true, datum.getId(), priceValue, qtyValue);
                }
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
        EditText etProductPrice;
        EditText etQtyProduct;
        TextView tvProductUnit;
        ImageView tvDeleteImport;
        ImageView tvUpdateImport;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDeleteImport = itemView.findViewById(R.id.tvDeleteImport);
            tvUpdateImport = itemView.findViewById(R.id.tvUpdateImport);
            etProductPrice = itemView.findViewById(R.id.etProductPrice);

            tvProductUnit = itemView.findViewById(R.id.tvProductUnit);
            etQtyProduct = itemView.findViewById(R.id.etQtyProduct);

            tvUpdateImport.setVisibility(View.INVISIBLE);
        }
    }


}