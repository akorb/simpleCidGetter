package com.andy.simplecidgetter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static com.andy.simplecidgetter.PropertyHelper.getPropertyValue;

class Cid {
    private Cid() {
        // hide constructor
    }

    private static final String cid;
    private static String cidName;

    private static final State state;

    private static Map<String, String> createCidHashMap() {
        /*
        More than the actual expected items (CIDs)
        because we want enough space that no collisions happen.

        256 because Java uses only power of two values.
        Giving 200 or something similar would give the same initial capacity.
        */
        Map<String, String> dic = new HashMap<>(256);
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
        dic.put("HTC__060", "India");
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
        dic.put("BS_US001", "US Unlocked");
        dic.put("BS_US002", "US Unlocked");
        dic.put("UTSI_005", "Bluegrass Cellular");
        dic.put("ACG__001", "nTelos");
        dic.put("METRO001", "MetroPCS");
        dic.put("ATT__001", "AT&T");
        dic.put("VIDEO001", "CA_Videotron");
        dic.put("LRA__001", "Bluegrass Cellular");
        dic.put("KDDI_801", "KDDI Corporation");
        dic.put("SPCS_651", "Visma Spcs AB");
        return dic;
    }

    static String getCid() {
        return cid;
    }

    static String getCidName() {
        return cidName;
    }

    static State getState() {
        return state;
    }

    static String getText(boolean all) {
        if (all) {
            return PropertyHelper.getAll();
        }

        String text = getCid();
        if (state == Cid.State.KnownCid)
            text += "\n" + getCidName();
        return text;
    }

    static {
        cid = getCidFromSystem();
        // DEBUG
        //cid = "11111111";

        if (cid.isEmpty()) {
            state = State.NoHtc;
        } else {
            Map<String, String> cidDic = createCidHashMap();
            cidName = cidDic.get(cid);
            if (cidName != null) {
                state = State.KnownCid;
            } else {
                state = State.UnknownCid;
            }
            cidDic.clear();
        }
    }

    private static String getCidFromSystem() {
        // The cid is stored usually stored in ro.cid
        String primaryCid = getPropertyValue("ro.cid");
        if (!primaryCid.isEmpty())
            return primaryCid.toUpperCase(Locale.getDefault());
        // ro.cid does not exist for each device.
        // I guess it's for GPE devices, but this isn't for sure yet.
        String fallbackCid = getPropertyValue("ro.boot.cid");
        if (!fallbackCid.isEmpty())
            return fallbackCid.toUpperCase(Locale.getDefault());
        // When cid does not exist, return empty string.
        return "";
    }

    enum State {
        KnownCid, UnknownCid, NoHtc
    }
}
