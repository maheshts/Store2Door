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
import android.widget.TextView;

import com.android.store2door.ImportDetailActivity;
import com.android.store2door.OrderRequestDetailActivity;
import com.android.store2door.R;
import com.android.store2door.api.Callbacks.importProduct.ShopproductImportModel;
import com.android.store2door.api.Callbacks.orderRequest.StockItemOrderRequest;
import com.android.store2door.api.Constant;
import com.android.store2door.utils.CommonFunction;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class OrderRequestStockListAdapter extends RecyclerView.Adapter<OrderRequestStockListAdapter.ItemViewHolder> {


    private List<StockItemOrderRequest> responseStockItemOrderRequestList;
    private Context mContext;

    private OnClickListener mOnClickListener;

    boolean isChangeClick = false;

    public OrderRequestStockListAdapter(List<StockItemOrderRequest> mResponseStockItemOrderRequestList, Context context, OnClickListener onClickListener) {

        this.responseStockItemOrderRequestList = mResponseStockItemOrderRequestList;
        this.mContext = context;
        this.mOnClickListener = onClickListener;

    }

    public void updateList(List<StockItemOrderRequest> list) {
        this.responseStockItemOrderRequestList = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ItemViewHolder(LayoutInflater.from
                (parent.getContext()).inflate(R.layout.order_request_list_layout, parent, false));
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, final int position) {
        if (mContext == null) {
            return;
        }
        StockItemOrderRequest datum = responseStockItemOrderRequestList.get(position);

        holder.tvProductTitle.setText(datum.getProduct().getName());
        holder.tvProductDescription.setText(datum.getProduct().getDescription());
        holder.etProductQty.setText(String.valueOf(datum.getShopQuantity()));
        holder.etProductPrice.setText(String.valueOf(datum.getShopUnitPrice()));
        holder.tvProductUnit.setText("(" + datum.getUnitQuantity() + " " + datum.getUnitType() + ")");

        holder.etProductPrice.setSelection(holder.etProductPrice.getText().length());
        holder.etProductQty.setSelection(holder.etProductQty.getText().length());

//        holder.tvProductQty.setEnabled(isChangeClick);
//        holder.tvProductPrice.setEnabled(isChangeClick);
        holder.etProductPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence.toString())) {
                    if (Integer.parseInt(charSequence.toString()) == datum.getShopUnitPrice() &&
                            Integer.parseInt(holder.etProductQty.getText().toString()) == datum.getShopQuantity()) {
                        holder.tvChangeOrderList.setVisibility(View.INVISIBLE);
                    } else {
                        holder.tvChangeOrderList.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        holder.etProductQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence.toString())) {
                    if (Integer.parseInt(charSequence.toString()) == datum.getShopQuantity()
                            && Integer.parseInt(holder.etProductPrice.getText().toString()) == datum.getShopUnitPrice()) {
                        holder.tvChangeOrderList.setVisibility(View.INVISIBLE);
                    } else {
                        holder.tvChangeOrderList.setVisibility(View.VISIBLE);

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Picasso.get().load(Constant.BASEURL_image + datum.getProduct().getImage())
                .error(R.drawable.splash_bg).placeholder(R.drawable.splash_bg).into(holder.ivProductImage);


//        holder.cvMainLayout.setOnClickListener(view -> {
//
//            if (mOnClickListener != null) {
//                mOnClickListener.clickLayout(holder.getAdapterPosition());
//            }
//        });
        holder.tvChangeOrderList.setVisibility(View.INVISIBLE);
        holder.tvChangeOrderList.setOnClickListener(view -> {
//            if (mOnClickListener != null) {
//                mOnClickListener.clickChangeButton(holder.getAdapterPosition(),holder.tvProductPrice.getText().toString(),
//                        holder.tvProductQty.getText().toString());
//            }

            String priceValue = holder.etProductPrice.getText().toString();
            String qtyValue = holder.etProductQty.getText().toString();

            if (TextUtils.isEmpty(priceValue) || priceValue.equalsIgnoreCase("0")) {
                CommonFunction.showToastSingle(mContext, mContext.getResources().getString(R.string.valid_price));
            } else if (TextUtils.isEmpty(qtyValue) || qtyValue.equalsIgnoreCase("0")) {
                CommonFunction.showToastSingle(mContext, mContext.getResources().getString(R.string.valid_qty));
            } else {
                ((OrderRequestDetailActivity) mContext).ChangeDialog(datum.getOrderId(), datum.getProductId(),
                        datum.getPriceId(), priceValue, qtyValue);
            }

        });
    }


    @Override
    public int getItemCount() {
        return responseStockItemOrderRequestList.size();
    }


    public interface OnClickListener {
        void clickLayout(int position);

        void clickChangeButton(int position, String price, String quntity);
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {


        ImageView ivProductImage;
        public EditText etProductPrice;
        TextView tvProductDescription;
        public EditText etProductQty;
        TextView tvProductTitle;
        TextView tvChangeOrderList;
        TextView tvProductUnit;

        ItemViewHolder(View itemView) {
            super(itemView);

            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            etProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            etProductQty = itemView.findViewById(R.id.tvProductQty);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
            tvChangeOrderList = itemView.findViewById(R.id.tvChangeOrderList);
            tvProductUnit = itemView.findViewById(R.id.tvProductUnit);

            tvChangeOrderList.setVisibility(View.INVISIBLE);
        }
    }

}