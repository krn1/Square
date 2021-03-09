package com.square.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.square.databinding.ViewEmployeeListItemBinding;
import com.square.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Employee> employees = new ArrayList<>();
    private EmployeeItemClickListener listener;

    EmployeeListAdapter(EmployeeItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final ViewEmployeeListItemBinding itemBinding =
                ViewEmployeeListItemBinding.inflate(layoutInflater, parent, false);
        return new ItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    // region Helper Methods

    void update(List<Employee> employees) {
        this.employees.clear();
        display(employees);
    }

    void display(List<Employee> employees) {
        this.employees.addAll(employees);
        notifyDataSetChanged();
    }


    // endregion

    // region item View holder

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ViewEmployeeListItemBinding binding;

        ItemViewHolder(@NonNull ViewEmployeeListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(int position) {
            Employee employee = employees.get(position);
            String imageUrl = employee.getPhotoUrlSmall();
            binding.image.setImageURI(imageUrl);
            binding.name.setText(employee.getFullName());
            binding.team.setText(employee.getTeam());

            View.OnClickListener clickListener = v -> {
                listener.onItemClick(employees.get(position));
            };
            itemView.setOnClickListener(clickListener);
        }
    }

    // endregion
}
