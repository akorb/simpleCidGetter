package com.andy.simplecidgetter;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class CidFragment extends Fragment implements IShareable {

    static CidFragment instance;

    public static CidFragment getInstance() {
        if (instance == null)
            instance = new CidFragment();

        return instance;
    }

    Map<String, String> dic = new HashMap<String, String>();

    TextView tvCid;
    TextView tvCidName;
    TextView tvReport;

    String cid = "";
    String cidName = "";

    Status status = Status.OfficialCid;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillHashMap();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        tvCid = view.findViewById(R.id.tvCid);
        tvCidName = view.findViewById(R.id.tvCidName);
        tvReport = view.findViewById(R.id.tvReport);
        tvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cid.length() != 8) {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(view.getContext());
                    dlgAlert.setMessage("A valid CID has 8 letters.\nYours has " + cid.length() + ".");
                    dlgAlert.setPositiveButton("OK", null);
                    dlgAlert.create().show();
                    return;
                }

                if (isOnline()) {
                    new ReportCid().execute(cid);

                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(view.getContext());
                    dlgAlert.setMessage("Thanks for your report.");
                    dlgAlert.setPositiveButton("No problem", null);
                    dlgAlert.show();
                } else {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(view.getContext());
                    dlgAlert.setTitle("No internet connection");
                    dlgAlert.setMessage("Try again while you are online.");
                    dlgAlert.setPositiveButton("Okay", null);
                    dlgAlert.show();
                }
            }
        });

        cid = PropertyHelper.getCid();
        // DEBUG
        // cid = "11111111";
        tvCid.setText(cid);


        if (cid.isEmpty()) {
            setStatus(Status.NoHtc);
        } else if (dic.containsKey(cid)) {
            setStatus(Status.OfficialCid);
        } else {
            setStatus(Status.UnofficialCid);
        }

        return view;
    }

    public void Share(View view)
    {
        if (status == CidFragment.Status.NoHtc)
        {
            AlertDialog.Builder ad = new AlertDialog.Builder(view.getContext())
                    .setMessage("No info to share.");

            ad.show();
            return;
        }
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        if (status == CidFragment.Status.OfficialCid)
            sendIntent.putExtra(Intent.EXTRA_TEXT, cid + "\n" + cidName);
        else if (status == CidFragment.Status.UnofficialCid)
            sendIntent.putExtra(Intent.EXTRA_TEXT, cid);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void fillHashMap() {
        dic.put("11111111", "SuperCID");
        dic.put("HTC__622", "Asia-HK-CHT");
        dic.put("CWS__001", "ATT");
        dic.put("BM___001", "BM");
        dic.put("BOUYG201", "Bouygues-Telecom");
        dic.put("BSTAR502", "Brightstar-PTB");
        dic.put("BSTAR301", "Brightstar-SPA");
        dic.put("CHT__601", "Chunghwa-Taiwan");
        dic.put("HTCCN702", "CT");
        dic.put("HTCCN703", "CU");
        dic.put("DOCOM801", "DCM");
        dic.put("DOPOD701", "DOPOD");
        dic.put("T-MOB009", "Era");
        dic.put("FASTW401", "Fastweb-IT");
        dic.put("GOOGL001", "GOOGLE (GPE)");
        dic.put("H3G__F05", "H3G-DAN");
        dic.put("H3G__402", "H3G-Italy");
        dic.put("H3G__003", "H3G-ROI");
        dic.put("H3G__G04", "H3G-SWE");
        dic.put("H3G__001", "H3G-UK");
        dic.put("H3G__106", "H3G-AT");
        dic.put("HTC__002", "Europe");
        dic.put("HTC__017", "Cincinnati Bell");
        dic.put("HTC__037", "Asia-SEA");
        dic.put("HTC__044", "Asia-SEA-WWE");
        dic.put("HTC__031", "South europe");
        dic.put("HTC__023", "Australia");
        dic.put("HTC__E41", "BE");
        dic.put("HTC__C24", "Czech");
        dic.put("HTC__F08", "Denmark");
        dic.put("HTC__E11", "Dutch");
        dic.put("HTC__032", "EastEurope");
        dic.put("HTC__034", "Europe");
        dic.put("HTC__N34", "ELL");
        dic.put("HTC__203", "FRA");
        dic.put("HTC__247", "FRA-Bouygues");
        dic.put("HTC__J15", "Arabic");
        dic.put("HTC__102", "GER");
        dic.put("HTC__038", "India");
        dic.put("HTC__405", "ITA");
        dic.put("HTC__Y13", "Nor");
        dic.put("HTC__H10", "Norway");
        dic.put("HTC__B25", "Poland");
        dic.put("HTC__506", "PTG");
        dic.put("HTC__A07", "Russia");
        dic.put("HTC__304", "SPA");
        dic.put("HTC__332", "Latin America");
        dic.put("HTC__G09", "Sweden");
        dic.put("HTC__M27", "Turkey");
        dic.put("HTC__001", "WWE");
        dic.put("HTC__039", "Australia");
        dic.put("HTC__059", "Asian-Europe");
        dic.put("HTC__K18", "Israel");
        dic.put("HTC__058", "Myanmar");
        dic.put("HUTCH001", "Hutch-Australia");
        dic.put("O2___102", "O2-DE");
        dic.put("O2___001", "O2-UK");
        dic.put("HTCCN701", "Open-Channel");
        dic.put("HTCCN704", "China");
        dic.put("OPTUS001", "Optus-Australia");
        dic.put("ORANG113", "ORANGE-AT");
        dic.put("ORANG012", "ORANGE-BE");
        dic.put("ORANG203", "ORANGE-CH-FRA");
        dic.put("ORANG104", "ORANGE-CH-GER");
        dic.put("ORANG309", "ORANGE-ES");
        dic.put("ORANG202", "ORANGE-French");
        dic.put("ORANGB10", "ORANGE-PL");
        dic.put("ORANG008", "ORANGE-PO");
        dic.put("ORANG006", "ORANGE-SK");
        dic.put("ORANG001", "ORANGE-UK");
        dic.put("ROGER001", "Rogers");
        dic.put("SMCVD001", "SMC-Voda-HK");
        dic.put("TELEF301", "TELEF-Spain");
        dic.put("TELST001", "Telstra");
        dic.put("TELUS001", "TELUS");
        dic.put("TIM__401", "TIM-Italy");
        dic.put("T-MOB102", "TMA");
        dic.put("T-MOB004", "TMCZ");
        dic.put("T-MOB101", "TMD");
        dic.put("T-MOB007", "TMH");
        dic.put("T-MOB006", "TMHR");
        dic.put("T-MOBL11", "TMMK");
        dic.put("T-MOB003", "TMNL");
        dic.put("T-MOB008", "TMSK");
        dic.put("T-MOB005", "TMUK");
        dic.put("T-MOB010", "TMUS");
        dic.put("HTC__A48", "Ukraine");
        dic.put("HTC__621", "TWM-TW");
        dic.put("VIRGI001", "VIRGIN-UK");
        dic.put("SPCS_002", "Virgin Mobile");
        dic.put("HTC__016", "S.Africa");
        dic.put("VODAP021", "VODA-Australia");
        dic.put("VODAP102", "VODA-Germany");
        dic.put("VODAP006", "VODA-Greece");
        dic.put("VODAP019", "VODA-Ireland");
        dic.put("VODAP405", "VODA-Italy");
        dic.put("VODAP120", "VODA-Mobilkom");
        dic.put("VODAPE17", "VODA-Netherland");
        dic.put("VODAP022", "VODA-New-Zealand");
        dic.put("VODAPD18", "VODA-Portugal");
        dic.put("VODAP024", "VODA-Proximus");
        dic.put("VODAP026", "VODA-SA");
        dic.put("VODAP203", "VODA-SFR");
        dic.put("VODAP304", "VODA-Spain");
        dic.put("VODAP110", "VODA-Swisscom-DE");
        dic.put("VODAP212", "VODA-Swisscom-FR");
        dic.put("VODAP416", "VODA-Swisscom-IT");
        dic.put("VODAP015", "VODA-Swisscom-WWE");
        dic.put("VODAPM27", "VODA-TR");
        dic.put("VODAP001", "VODA-UK");
        dic.put("VZW__001", "Verizon");
        dic.put("VZW__003", "Verizon");
        dic.put("SPCS_001", "Sprint");
        dic.put("SPCS_004", "HarmanKardon");
        dic.put("BS_US001", "Developer Edition");
        dic.put("BS_US002", "Developer Edition");
        dic.put("UTSI_005", "Bluegrass Cellular");
        dic.put("ACG__001", "nTelos");
        dic.put("METRO001", "MetroPCS");
        dic.put("ATT__001", "AT&T");
        dic.put("VIDEO001", "CA_Videotron");
        dic.put("LRA__001", "Bluegrass Cellular");
    }

    private void assignStatusOfficialCid() {
        tvCid.setVisibility(View.VISIBLE);
        cidName = dic.get(cid);
        tvCid.setText(cid);
        tvCidName.setText(cidName);
    }

    private void assignStatusNoHtc() {
        tvCid.setVisibility(View.GONE);
        tvCidName.setText("No HTC device");
    }

    private void assignStatusUnofficialCid() {
        tvReport.setVisibility(View.VISIBLE);

        tvCidName.setTextSize(20f);
        tvCidName.setText("Unknown");
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getView().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public void setStatus(Status status) {
        switch (status) {
            case OfficialCid:
                assignStatusOfficialCid();
                break;
            case UnofficialCid:
                assignStatusUnofficialCid();
                break;
            case NoHtc:
                assignStatusNoHtc();
                break;
        }

        this.status = status;
    }

    public enum Status {
        OfficialCid, UnofficialCid, NoHtc
    }
}
