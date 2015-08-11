package kl.app.syamsul.com.kulinerlombok.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by syamsul on 09/08/15.
 */
public class ActionDialog extends DialogFragment {

    private int layout;
    private int title = 0;
    private int positiveButton;
    private int negativeButton;
    private ActionDialogListener mListener;

    public static final String ACTION_DIALOG_LAYOUT = "layout";
    public static final String ACTION_DIALOG_TITLE = "title";
    public static final String ACTION_DIALOG_POSITIVE_BUTTON = "positive";
    public static final String ACTION_DIALOG_NEGATIVE_BUTTON = "negative";

    public static ActionDialog newInstance(int layout, int title, int positiveButton, int negativeButton){

        Bundle args = new Bundle();
        args.putInt(ACTION_DIALOG_LAYOUT, layout);
        args.putInt(ACTION_DIALOG_TITLE, title);
        args.putInt(ACTION_DIALOG_POSITIVE_BUTTON, positiveButton);
        args.putInt(ACTION_DIALOG_NEGATIVE_BUTTON, negativeButton);

        ActionDialog a = new ActionDialog();
        a.setArguments(args);
        return a;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args != null){
            layout = args.getInt(ACTION_DIALOG_LAYOUT);
            title = args.getInt(ACTION_DIALOG_TITLE);
            positiveButton = args.getInt(ACTION_DIALOG_POSITIVE_BUTTON);
            negativeButton = args.getInt(ACTION_DIALOG_NEGATIVE_BUTTON);
        }

        Fragment fragment = getTargetFragment();
        try{
            mListener = (ActionDialogListener) fragment;
        } catch (ClassCastException e){
            throw new ClassCastException(fragment.getClass() + " must implement ActionDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        LayoutInflater inflater = getActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = inflater.inflate(layout, null);
        if(title != 0){
            builder.setTitle(title);
        }


        builder.setPositiveButton(positiveButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onDialogConfirm();
            }
        });

        builder.setNegativeButton(negativeButton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onDialogCancel();
            }
        });

        builder.setView(view);

        return builder.create();
    }

    public interface ActionDialogListener{
        void onDialogConfirm();
        void onDialogCancel();
    }
}
