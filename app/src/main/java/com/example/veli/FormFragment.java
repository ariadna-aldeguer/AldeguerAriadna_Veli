package com.example.veli;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.veli.DB.TravelsDBHelper;
import com.example.veli.Model.Travel;

/**
 * A fragment subclass containing a form to add information into the database.
 */
public class FormFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Create the instance of dbHelper
    private TravelsDBHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Empty constructor of class FormFragment
     */
    public FormFragment() {
    }

    /**
     * Constructor of class FormFragment
     */
    public FormFragment(TravelsDBHelper dbHelper, SQLiteDatabase db) {
        this.dbHelper = dbHelper;
        this.db = db;
    }

    /**
     * Create a new instance of FormFragment
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of the fragment.
     * @param savedInstanceState: instance of form fragment
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * Creates and returns the view for the form fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_form, container, false);

        EditText country = view.findViewById(R.id.txtInputCountry);
        EditText city = view.findViewById(R.id.txtInputCity);
        EditText airport = view.findViewById(R.id.txtInputAirport);
        Button button = view.findViewById(R.id.btnAdd);

        /**
         * When there is a click on the "Add travel" button, the travels is added into the database.
         * If succeeded, a confirmation toast will appear, otherwise an error message will be show.
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String co = country.getText().toString();
                String ci = city.getText().toString();
                String ai = airport.getText().toString();
                if (co.equals("") && ci.equals("") && ai.equals("")) {
                    Toast.makeText(getContext(), "You need to introduce at least one property", Toast.LENGTH_LONG).show();
                } else {
                    Travel travel = new Travel(co, ci, ai);
                    dbHelper.insertTravel(db, travel);
                    if (!co.equals("")){
                        Toast.makeText(getContext(), "The travel " + co + " has been saved successfully", Toast.LENGTH_LONG).show();
                    } else if (!ci.equals("")){
                        Toast.makeText(getContext(), "The travel " + ci + " has been saved successfully", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getContext(), "The travel " + ci + " has been saved successfully", Toast.LENGTH_LONG).show();
                    }
                }
                country.setText("");
                city.setText("");
                airport.setText("");
            }
        });

        return view;
    }
}