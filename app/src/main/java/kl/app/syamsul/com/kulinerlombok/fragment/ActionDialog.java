package kl.app.syamsul.com.kulinerlombok.fragment;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kl.app.syamsul.com.kulinerlombok.R;

/**
 * Created by syamsul on 09/08/15.
 */
public class ActionDialog extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_dialog,container,false);

        return v;
    }
}
