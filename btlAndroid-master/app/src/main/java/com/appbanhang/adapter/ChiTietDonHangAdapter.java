package com.appbanhang.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.appbanhang.R;
import com.appbanhang.model.Chitietdonhang;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietDonHangAdapter extends RecyclerView.Adapter<ChiTietDonHangAdapter.ItemHolder> {
                    Context context;
                    ArrayList<Chitietdonhang> arrayCTDH;

                    public ChiTietDonHangAdapter(Context context, ArrayList<Chitietdonhang> arrayCTDH) {
                            this.context = context;
                            this.arrayCTDH = arrayCTDH;
                        }

                        @Override
                        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                            View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.dong_qlchitietdonhang, null );
                            ItemHolder itemHolder = new ItemHolder ( view );
                            return itemHolder;
                        }

                        @Override
                        public void onBindViewHolder(ItemHolder holder, int position) {
                            if(holder !=null) {
                                holder.madh.setText ("MaDH :"+arrayCTDH.get ( position ).getMadonhangctdh () );
                                holder.masp.setText ( "MaSP :"+arrayCTDH.get ( position ).getMasanphamctdh () );
                                holder.tensp.setText ("TenSP: "+ arrayCTDH.get ( position ).getTensanphamctdh () );
                                holder.gia.setText ( "Gia: "+arrayCTDH.get ( position ).getGiasanphamctdh () +"vnd");
                    //            DecimalFormat decimalFormat = new DecimalFormat ( "###,###,###" );
                    //            holder.gia.setText ( "Gia: " + decimalFormat.format ( arrayCTDH.get ( position ).getGiasanphamctdh () ) + "vnd" );
                                holder.soluong.setText ("SoLuong:"+ arrayCTDH.get ( position ).getSoluongsanphamctdh () );
        }
    }

    @Override
    public int getItemCount() {
        return arrayCTDH.size ();
    }

    public class ItemHolder extends RecyclerView.ViewHolder{
        public TextView madh;
        public TextView masp;
        public TextView tensp;
        public TextView gia;
        public TextView soluong;

        public ItemHolder(View itemView) {
            super ( itemView );
            madh = itemView.findViewById ( R.id.tv_madonhang );
            masp = itemView.findViewById ( R.id.tv_masanpham );
            tensp = itemView.findViewById ( R.id.tv_tenspx );
            gia = itemView.findViewById ( R.id.tv_giaspx );
            soluong = itemView.findViewById ( R.id.tv_soluong );
        }
    }
}
