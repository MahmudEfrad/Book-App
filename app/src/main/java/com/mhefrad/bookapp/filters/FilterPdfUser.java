package com.mhefrad.bookapp.filters;

import android.annotation.SuppressLint;
import android.widget.Filter;

import com.mhefrad.bookapp.adapter.AdapterPdfUser;
import com.mhefrad.bookapp.models.ModelPdf;

import java.util.ArrayList;

public class FilterPdfUser extends Filter {

    //arraylist in which we want to search
    ArrayList<ModelPdf> filterList;
    //adapter in which filter need to be implement
    AdapterPdfUser adapterPdfUser;

    public FilterPdfUser(ArrayList<ModelPdf> filterList, AdapterPdfUser adapterPdfUser) {
        this.filterList = filterList;
        this.adapterPdfUser = adapterPdfUser;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        //value to be searched should not be null/empty
        if (constraint != null || constraint.length() > 0) {
            //not null nor empty
            //change to uppercase or lower case  to avoid case sensitivity
            constraint = constraint.toString().toUpperCase();
            ArrayList<ModelPdf> filteredModels = new ArrayList<>();

            for (int i=0; i<filterList.size(); i++){
                //validate
                if (filterList.get(i).getTitle().toUpperCase().contains(constraint)) {
                    //search matches, add to list
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filterList;
        } else {
            //empty or null, make original list/result
            results.count = filterList.size();
            results.values = filterList;
        }

        return results;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        //apply filter changes
        adapterPdfUser.pdfArrayList = (ArrayList<ModelPdf>) results.values;

        //notify changes
        adapterPdfUser.notifyDataSetChanged();

    }
}
