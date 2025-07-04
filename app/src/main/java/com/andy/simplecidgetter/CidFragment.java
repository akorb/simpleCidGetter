package com.andy.simplecidgetter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CidFragment extends Fragment {
    private TextView tvCid;
    private TextView tvCidName;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cid, container, false);
        tvCid = view.findViewById(R.id.tvCid);
        tvCidName = view.findViewById(R.id.tvCidName);

        setStatus(Cid.getState());

        return view;
    }

    private void assignStatusOfficialCid() {
        tvCid.setVisibility(View.VISIBLE);
        tvCid.setText(Cid.getCid());
        tvCidName.setText(Cid.getCidName());
    }

    private void assignStatusNoHtc() {
        tvCid.setVisibility(View.GONE);
        tvCidName.setText(R.string.no_htc);
    }

    private void assignStatusUnofficialCid() {
        tvCid.setText(Cid.getCid());
        tvCidName.setTextSize(20f);
        tvCidName.setText(R.string.unknown);
    }

    private void setStatus(Cid.State state) {
        switch (state) {
            case KnownCid:
                assignStatusOfficialCid();
                break;
            case UnknownCid:
                assignStatusUnofficialCid();
                break;
            case NoHtc:
                assignStatusNoHtc();
                break;
        }
    }
}
