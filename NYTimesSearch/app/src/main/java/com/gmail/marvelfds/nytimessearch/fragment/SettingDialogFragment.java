package com.gmail.marvelfds.nytimessearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.gmail.marvelfds.nytimessearch.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Core i7 on 5/17/2016.
 */
public class SettingDialogFragment extends DialogFragment{
    public Spinner sSortOrder;
    public EditText etBeginDate;
    private  CheckBox cbSports;
    private  CheckBox cbFashionsAndStyles;
    private  CheckBox cbArts;
    private Button btnAdd;

     String sortOrder;
     String beginDate;
     String avanceSearch;

    public String getAvanceSearch() {
        return avanceSearch;
    }

    public void setAvanceSearch(String avanceSearch) {
        this.avanceSearch = avanceSearch;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.setting_layout, container);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Get field from view
        setupViews(view);
       // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        getDialog().getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        // Show soft keyboard automatically and request focus to field

    }

    private void setupViews(View view){
       // Get field from view
        etBeginDate = (EditText) view.findViewById(R.id.etBeginDate);
        sSortOrder = (Spinner) view.findViewById(R.id.sSortOrder);
        cbSports = (CheckBox) view.findViewById(R.id.cbSports);
        cbFashionsAndStyles = (CheckBox) view.findViewById(R.id.cbFashion);
        cbArts = (CheckBox) view.findViewById(R.id.cbArts);
        btnAdd =(Button)view.findViewById(R.id.btnSave);

        // call the datePicker
        this.etBeginDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.show(getActivity().getSupportFragmentManager(),"DatePickerFragment");
            }
        });
        // handle the save Advance search queries
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 // setup paramatters for advance search
                  setupStringForSearch();
                dismiss();

                }





        });



        // populate the spinner

        // Define an arrylist for spinner Items
        final List<String> sortItems = new ArrayList<String>();
        // populate item into the array list
        sortItems.add("oldest");
        sortItems.add("newest");

        // define an array adapter
        ArrayAdapter<String> aSpninner = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,sortItems);
        sSortOrder.setAdapter(aSpninner);
        sSortOrder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 sortOrder = sortItems.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    public static SettingDialogFragment newInstance(String title) {
        SettingDialogFragment frag = new SettingDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);

        return frag;

    }
    public void setupStringForSearch() {
        beginDate = etBeginDate.getText().toString();

        if (cbSports.isChecked()) {
            avanceSearch = cbSports.getText().toString();
            if (cbFashionsAndStyles.isChecked()) {
                avanceSearch = avanceSearch + " " + cbFashionsAndStyles.getText().toString();
            }
            if (cbArts.isChecked())
                avanceSearch = avanceSearch + " " + cbArts.getText().toString();
        } else {
            if (cbFashionsAndStyles.isChecked()) {
                avanceSearch = cbFashionsAndStyles.getText().toString();
                if (cbArts.isChecked()) {
                    avanceSearch = avanceSearch + " " + cbArts.getText().toString();

                }
            } else {
                if (cbArts.isChecked()) {
                    avanceSearch = cbArts.getText().toString();

                }
            }

        }

    }


}