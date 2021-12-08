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
import com.kelompok3.uas_pbp_kelompok_3.models.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.ViewHolder> implements Filterable {
    private List<Rental> rentalList, filteredRentalList;
    private Context context;
    public RentalAdapter(List<Rental> rentalList, Context context) {
        this.rentalList = rentalList;
        filteredRentalList = new ArrayList<>(rentalList);
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
        Rental rental = filteredRentalList.get(position);
        holder.tvNamaKendaraan.setText(rental.getNama_kendaraan());
        holder.tvJenisKendaraan.setText(rental.getJenis_kendaraan());
        holder.tvStatus.setText(String.valueOf(rental.getStatus())); //bool?
        holder.tvBiayaKendaraan.setText(rental.getBiaya_penyewaan());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder materialAlertDialogBuilder =
                        new MaterialAlertDialogBuilder(context);
                materialAlertDialogBuilder.setTitle("Konfirmasi")
                        .setMessage("Apakah anda yakin ingin menghapus data kendaraan ini?")
                                        .setNegativeButton("Batal", null)
                                        .setPositiveButton("Hapus", new
                                                DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int
                                                            i) {
                                                        if (context instanceof MainActivity)
                                                            ((RentalActivity)
                                                                    context).deleteRental(Rental.getId());
                                                    }
                                                })
                                        .show();
            }
        });
        holder.cvRental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddEditRentalActivity.class);
                i.putExtra("id", rental.getId());
                if (context instanceof MainActivity)
                    ((MainActivity) context).startActivityForResult(i,
                            MainActivity.LAUNCH_ADD_ACTIVITY);
            }
        });
    }
    @Override
    public int getItemCount() {
        return filteredRentalList.size();
    }
    public void setRentalList(List<Rental> rentalList) {
        this.rentalList = rentalList;
        filteredRentalList = new ArrayList<>(rentalList);
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charSequenceString = charSequence.toString();
                List<Rental> filtered = new ArrayList<>();
                if (charSequenceString.isEmpty()) {
                    filtered.addAll(rentalList);
                } else {
                    for (Rental rental : rentalList) {
                        if (rental.getNama_kendaraan().toLowerCase()
                                .contains(charSequenceString.toLowerCase()))
                            filtered.add(rental);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filtered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults
                    filterResults) {
                filteredRentalList.clear();
                filteredRentalList.addAll((List<Rental>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaKendaraan, tvStatus, tvJenisKendaraan, tvBiayaKendaraan;
        ImageButton btnDelete;
        CardView cvRental;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaKendaraan = itemView.findViewById(R.id.tv_namaKendaraan);
            tvJenisKendaraan = itemView.findViewById(R.id.tv_jenisKendaraan);
            tvStatus = itemView.findViewById(R.id.tv_status);
            tvBiayaKendaraan = itemView.findViewById(R.id.tv_biayaSewa);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            cvRental = itemView.findViewById(R.id.cv_rental);
        }
    }
}
