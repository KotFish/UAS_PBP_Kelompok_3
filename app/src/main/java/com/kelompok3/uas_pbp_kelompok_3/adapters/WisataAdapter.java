package com.kelompok3.uas_pbp_kelompok_3.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.kelompok3.uas_pbp_kelompok_3.AddEditRentalActivity;
import com.kelompok3.uas_pbp_kelompok_3.MainActivity;
import com.kelompok3.uas_pbp_kelompok_3.R;
import com.kelompok3.uas_pbp_kelompok_3.models.Wisata;

import java.util.ArrayList;
import java.util.List;

public class WisataAdapter extends RecyclerView.Adapter<WisataAdapter.ViewHolder> implements Filterable {
    private List<Wisata> wisataList, filteredWisataList;
    private Context context;

    public WisataAdapter(List<Wisata> wisataList, Context context) {
        this.wisataList = wisataList;
        filteredWisataList = new ArrayList<>(wisataList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_rental, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Wisata wisata = filteredWisataList.get(position);
        holder.tvNama_wisata.setText(wisata.getNama_wisata());
        holder.tvLokasi.setText(wisata.getLokasi());
        holder.tvUrl_gambar.setText(wisata.getUrl_gambar());
        holder.tvHarga.setText(String.valueOf(wisata.getHarga()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder =
                        new MaterialAlertDialogBuilder(context);
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus data Wisata ini?")
                        .setNegativeButton("Batal", null)
                        .setPositiveButton("Hapus", new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int
                                            i) {
                                        if (context instanceof MainActivity)
                                            ((MainActivity)
                                                    context).deleteWisata(wisata.getId());
                                    }
                                })
                        .show();
            }
        });
        holder.cvWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddEditRentalActivity.class);
                i.putExtra("id", wisata.getId());
                if (context instanceof MainActivity)
                    ((MainActivity) context).startActivityForResult(i,
                            MainActivity.LAUNCH_ADD_ACTIVITY);
            }
        });
    }
    @Override
    public int getItemCount() {
        return filteredWisataList.size();
    }
    public void setRentalList(List<Wisata> wisataList) {
        this.wisataList = wisataList;
        filteredWisataList = new ArrayList<>(wisataList);
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<Wisata> filtered = new ArrayList<>();
                if (charSequenceString.isEmpty()) {
                    filtered.addAll(wisataList);
                } else {
                    for (Wisata wisata : wisataList) {
                        if (wisata.getNama_wisata().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(wisata);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                filteredWisataList.clear();
                filteredWisataList.addAll((List<Wisata>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama_wisata, tvLokasi, tvUrl_gambar, tvHarga;
        ImageButton btnDelete;
        CardView cvWisata;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama_wisata = itemView.findViewById(R.id.tv_namaWisata);
            tvLokasi = itemView.findViewById(R.id.tv_lokasiWisata);
            tvUrl_gambar = itemView.findViewById(R.id.iv_gambarWisata);
            tvHarga = itemView.findViewById(R.id.tv_hargaWisata);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            cvWisata = itemView.findViewById(R.id.cv_wisata);
        }
    }
}
