package com.anit.remider.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.anit.remider.R;
import com.anit.remider.Utils;
import com.anit.remider.model.ModelTask;

import java.util.Calendar;

/**
 * Created by 79900 on 07.07.2016.
 */
public class AddingTaskDialogFragment extends DialogFragment {


    private AddinTaskListener addinTaskListener;

    public interface AddinTaskListener {

        void onTaskAdded(ModelTask newTask);

        void onTaskAddingCencel();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {

            addinTaskListener = (AddinTaskListener) getActivity();

        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + "must implement AddinTaskListener");
        }

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.dialog_title);

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View container = inflater.inflate(R.layout.dialog_task, null);

        final TextInputLayout tilTitle = (TextInputLayout) container.findViewById(R.id.tilDialogTaskTitle);
        final EditText etTitle = tilTitle.getEditText();

        TextInputLayout tilDate = (TextInputLayout) container.findViewById(R.id.tilDialogTaskDate);
        final EditText etDate = tilDate.getEditText();

        TextInputLayout tilTime = (TextInputLayout) container.findViewById(R.id.tilDialogTaskTime);
        final EditText etTime = tilTime.getEditText();


        tilTitle.setHint(getResources().getString(R.string.task_title));
        tilDate.setHint(getResources().getString(R.string.task_data));
        tilTime.setHint(getResources().getString(R.string.task_time));

        builder.setView(container);


        final ModelTask task = new ModelTask();
        final Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 1);


        assert etDate != null;
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etDate.length() == 0) {
                    etDate.setText(" ");
                }
                ;

                DialogFragment dataPickerFragment = new DatePickerFragment() {

                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfmonth) {

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfmonth);
                        etDate.setText(Utils.getDate(calendar.getTimeInMillis()));

                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        etDate.setText(null);
                    }
                };
                dataPickerFragment.show(getFragmentManager(), "DatePickerFragment");


            }
        });


        assert etTime != null;
        etTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etTime.length() == 0) {
                    etTime.setText(" ");
                }
                ;

                DialogFragment timePickerFragment = new TimePickerDialogFragment() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minuteOfDay) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minuteOfDay);
                        calendar.set(Calendar.SECOND, 0);
                        etTime.setText(Utils.getTime(calendar.getTimeInMillis()));

                    }

                    @Override
                    public void onCancel(DialogInterface dialog) {
                        etTime.setText(null);
                    }
                };
                timePickerFragment.show(getFragmentManager(), "TimePickerFragment");


            }
        });


        builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                addinTaskListener.onTaskAdded(task);
                dialog.dismiss();
            }
        });


        builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                task.setTitle(etTitle.getText().toString());
                if(etDate.length() !=0 || etTime.length() != 0){
                    task.setDate(calendar.getTimeInMillis());
                }


                addinTaskListener.onTaskAddingCencel();
                dialog.cancel();
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                final Button positiveButton = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                if (etTitle.length() == 0) {
                    positiveButton.setEnabled(false);
                    tilTitle.setError(getResources().getString(R.string.dialog_error_empty_title));
                }


                etTitle.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        if (charSequence.length() == 0) {
                            positiveButton.setEnabled(true);
                            tilTitle.setError(getResources().getString(R.string.dialog_error_empty_title));
                        } else {
                            positiveButton.setEnabled(true);
                            tilTitle.setErrorEnabled(false);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

            }
        });


        return alertDialog;
    }
}
