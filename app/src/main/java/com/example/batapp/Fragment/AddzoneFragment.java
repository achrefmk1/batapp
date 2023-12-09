package com.example.batapp.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.batapp.R;
import com.example.batapp.Model.ZoneModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class AddzoneFragment extends Fragment {

    private EditText username,adresse,date;
    private Spinner state;
    private Button save;
    private Calendar mDateCalendar;
    private String selecteditem;


    @Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // Inflate the layout for this fragment

    return inflater.inflate(R.layout.fragment_addzone, container, false);

}

@Override
public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
   super.onViewCreated(view, savedInstanceState);

   username = view.findViewById(R.id.username_zone);
   adresse = view.findViewById(R.id.adresse_zone);
   state = view.findViewById(R.id.state_zone);
   date = view.findViewById(R.id.date_zone);
   save = view.findViewById(R.id.save_zone);

    initCalender();

    date.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            showCalendar();
        }
    });

    state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView adapter, View view, int position, long id) {
            selecteditem = adapter.getItemAtPosition(position).toString();
            Toast.makeText(getActivity(), "You selected : " + selecteditem, Toast.LENGTH_SHORT).show();
        }

            @Override
        public void onNothingSelected(AdapterView adapter) {
            Toast.makeText(getActivity(), "You should indicate the State", Toast.LENGTH_SHORT).show();
        }
    });

    save.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String statu_value = state.getSelectedItem().toString();
            String user_value =username.getEditableText().toString();
            String adress_value =adresse.getEditableText().toString();
            String date_value = date.getEditableText().toString();

            String id_value = FirebaseAuth.getInstance().getUid();

            ZoneModel zone = new ZoneModel(id_value,user_value,adress_value,statu_value,date_value);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("/ZONE/"+id_value);

            reference.setValue(zone).addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getActivity(), "Saving Success", Toast.LENGTH_SHORT).show();
                    getFragmentManager().
                            beginTransaction().
                            replace(R.id.fragment_menu_container,new HomeFragment(),"")
                            .commit();
                }
            }).addOnFailureListener(getActivity(), new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), "Adding Zone Failed", Toast.LENGTH_SHORT).show();
                }
            });







        }
    });


}

private SimpleDateFormat getDateFormat(){
    return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
}

    private void showCalendar() {
        DatePickerDialog.OnDateSetListener mdatePickerDialogListner = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mDateCalendar.set(Calendar.YEAR, year);
                mDateCalendar.set(Calendar.MONTH, month);
                mDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                date.setText(getDateFormat().format(mDateCalendar.getTime()));

            }
        };
        mDateCalendar = Calendar.getInstance();
        DatePickerDialog mDateCalendarDialog = new DatePickerDialog(getActivity(),
                R.style.DatePickerTheme,
                mdatePickerDialogListner,
                mDateCalendar.get(Calendar.YEAR),
                mDateCalendar.get(Calendar.MONTH),
                mDateCalendar.get(Calendar.DAY_OF_MONTH));

        mDateCalendarDialog.getDatePicker().setMaxDate(mDateCalendar.getTimeInMillis());
        mDateCalendarDialog.setCancelable(false);
        mDateCalendarDialog.show();
    }

    private void initCalender(){
        String currentDate = getDateFormat().format(new Date());
        date.setText(currentDate);
}




}

