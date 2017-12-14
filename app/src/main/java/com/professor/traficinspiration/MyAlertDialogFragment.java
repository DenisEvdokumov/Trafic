package com.professor.traficinspiration;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MyAlertDialogFragment extends DialogFragment {

    public static void createAndShowErrorDialog(String errorString) {

//        if (ApplicationContext.getContext() == null) {
//            return;
//        }

        // temporal variant
        // it will crush on version lesser than 17
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (ApplicationContext.getContext().isDestroyed()) {
                Toast.makeText(ApplicationContext.getContext(), errorString, Toast.LENGTH_LONG).show();
                return;
            }
        }


        Bundle args = new Bundle();
        args.putString("title", "Error");
        args.putString("message", errorString);

        MyAlertDialogFragment frag = new MyAlertDialogFragment();
        frag.setArguments(args);

//        CheckinSuccessDialog dialog = new CheckinSuccessDialog();
//dialog.show(getSupportFragmentManager(), null);
        FragmentTransaction ft = ApplicationContext.getContext().getFragmentManager().beginTransaction();
        ft.add(frag, null);
        ft.commitAllowingStateLoss();

//        frag.show(ApplicationContext.getContext().getFragmentManager(), "dialog");

//        Toast.makeText(ApplicationContext.getContext(), "dialog created", Toast.LENGTH_LONG).show();

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String  title = getArguments().getString("title");
        String  message = getArguments().getString("message");

        return new AlertDialog.Builder(getActivity())
//                .setIcon(R.drawable.alert_dialog_icon)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new FatalErrorOnClickListener())
                .create();
    }

    class FatalErrorOnClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            ApplicationContext.getContext().finish();
        }
    }

    class ErrorOnClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {

        }
    }

}