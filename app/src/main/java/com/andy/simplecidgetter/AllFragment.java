package com.andy.simplecidgetter;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AllFragment extends Fragment implements IShareable {

    static AllFragment instance;

    public static AllFragment getInstance() {
        if (instance == null)
            instance = new AllFragment();

        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_advanced, container, false);
        TextView tvAll = view.findViewById(R.id.tvAll);
        tvAll.setText(PropertyHelper.getAll());

        return view;
    }

    public void Share(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setType("text/plain");
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, PropertyHelper.getAll());

        startActivity(sendIntent);
    }
}
