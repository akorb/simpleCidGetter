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

    private final Cid cidState;

    CidFragment(Cid cidState) {
        this.cidState = cidState;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cid, container, false);
        tvCid = view.findViewById(R.id.tvCid);
        tvCidName = view.findViewById(R.id.tvCidName);

        setStatus(cidState.getState());

        return view;
    }



    private void assignStatusOfficialCid() {
        tvCid.setVisibility(View.VISIBLE);
        tvCid.setText(cidState.getCid());
        tvCidName.setText(cidState.getCidName());
    }

    private void assignStatusNoHtc() {
        tvCid.setVisibility(View.GONE);
        tvCidName.setText(R.string.no_htc);
    }

    private void assignStatusUnofficialCid() {
        tvCid.setText(cidState.getCid());
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
