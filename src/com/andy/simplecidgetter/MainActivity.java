package com.andy.simplecidgetter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity
{
	Map<String, String> dic = new HashMap<String, String>();

	TextView tvCid;
	TextView tvAll;
	TextView tvCidName;

	LinearLayout ll;

	ScrollView svAll;

	String cid = "";
	String cidName = "";
	String all = "";

	Status status = Status.Simple;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvCid = (TextView)findViewById(R.id.tvCid);
		tvAll = (TextView)findViewById(R.id.tvAll);
		tvCidName = (TextView)findViewById(R.id.tvCidName);
		svAll = (ScrollView)findViewById(R.id.svAll);
		ll = (LinearLayout)findViewById(R.id.MainContainer);

		try
		{
			all = getProperties();
		}
		catch (IOException e)
		{

		}

		tvCid.setText(cid);
		tvAll.setText(all);

		fillHashMap();

		if (dic.containsKey(cid))
		{
			cidName = dic.get(cid);
			tvCidName.setText(cidName);
		}
		else
		{
			setStatus(Status.Simple);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
		case R.id.simple:
			setStatus(Status.Simple);
			return true;
		case R.id.advanced:
			setStatus(Status.Advanced);
			return true;
		case R.id.share:
			if (status == Status.NoHtc)
			{
				AlertDialog.Builder ad = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT)
				.setMessage("No info to share.");
				
				ad.show();
				
				return true;
			}
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			if (status == Status.Advanced)
				sendIntent.putExtra(Intent.EXTRA_TEXT, all);
			else if (status == Status.Simple)
				sendIntent.putExtra(Intent.EXTRA_TEXT, cid + "\r\n" + cidName);
			else if (status == Status.NoOfficialCid)
				sendIntent.putExtra(Intent.EXTRA_TEXT, cid);
			sendIntent.setType("text/plain");
			startActivity(sendIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private String getProperties() throws IOException
	{
		Scanner reader = new Scanner(Runtime.getRuntime().exec("getprop")
				.getInputStream());

		StringBuilder sb = new StringBuilder();
		String seperator = System.getProperty("line.separator");

		while (reader.hasNext())
		{
			String s1 = reader.nextLine();

			if (s1.contains("ro.cid"))
			{
				sb.insert(0, s1 + seperator);
				cid = s1.replaceFirst(Pattern.quote("[ro.cid]: ["), "")
						.replace(']', '\0').trim();
			}
			else
			{
				sb.append(s1);
				sb.append(seperator);
			}
		}

		reader.close();

		return sb.toString();
	}

	private void fillHashMap()
	{
		dic.put("11111111", "SuperCID");
		dic.put("HTC__622", "Asia-HK-CHT");
		dic.put("CWS__001", "ATT");
		dic.put("BM_001", "BM");
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
		dic.put("GOOGL001", "GOOGLE");
		dic.put("H3G__F05", "H3G-DAN");
		dic.put("H3G__402", "H3G-Italy");
		dic.put("H3G__003", "H3G-ROI");
		dic.put("H3G__G04", "H3G-SWE");
		dic.put("H3G__001", "H3G-UK");
		dic.put("HTC__037", "HTC-Asia-SEA");
		dic.put("HTC__044", "HTC-Asia-SEA-WWE");
		dic.put("HTC__023", "HTC-Australia");
		dic.put("HTC__E41", "HTC-BE");
		dic.put("HTC__C24", "HTC-Czech");
		dic.put("HTC__F08", "HTC-Denmark");
		dic.put("HTC__E11", "HTC-Dutch");
		dic.put("HTC__032", "HTC-EastEurope");
		dic.put("HTC__N34", "HTC-ELL");
		dic.put("HTC__203", "HTC-FRA");
		dic.put("HTC__247", "HTC-FRA-Bouygues");
		dic.put("HTC__J15", "HTC-GCC");
		dic.put("HTC__102", "HTC-GER");
		dic.put("HTC__038", "HTC-India");
		dic.put("HTC__405", "HTC-ITA");
		dic.put("HTC__Y13", "HTC-Nor");
		dic.put("HTC__H10", "HTC-Norway");
		dic.put("HTC__B25", "HTC-Poland");
		dic.put("HTC__506", "HTC-PTG");
		dic.put("HTC__A07", "HTC-Russia");
		dic.put("HTC__304", "HTC-SPA");
		dic.put("HTC__G09", "HTC-Sweden");
		dic.put("HTC__M27", "HTC-Turkey");
		dic.put("HTC__001", "HTC-WWE");
		dic.put("HUTCH001", "Hutch-Australia");
		dic.put("O2___102", "O2-DE");
		dic.put("O2___001", "O2-UK");
		dic.put("HTCCN701", "Open-Channel");
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
		dic.put("HTC__621", "TWM-TW");
		dic.put("VIRGI001", "VIRGIN-UK");
		dic.put("HTC__016", "VODA-Africa-South");
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
	}

	private void assignStatusSimple()
	{
		ll.setVisibility(View.VISIBLE);
		svAll.setVisibility(View.GONE);
	}

	private void assignStatusAll()
	{
		ll.setVisibility(View.GONE);
		svAll.setVisibility(View.VISIBLE);
	}

	private void assignStatusNoHtc()
	{
		tvCid.setVisibility(View.GONE);
		tvCidName.setText("No HTC device.");
	}

	private void assignStatusNoOfficialCid()
	{
		tvCidName.setTextSize(14f);
		tvCidName.setText("No official CID in use.");
	}

	public void onClick(View v)
	{
		assignStatusAll();
	}

	public void setStatus(Status status)
	{
		switch (status)
		{
		case Simple:
			assignStatusSimple();
			if (cid == "")
			{
				setStatus(Status.NoHtc);
				return;
			}
			else if (cidName == "")
			{
				setStatus(Status.NoOfficialCid);
				return;
			}
			break;
		case Advanced:
			assignStatusAll();
			break;
		case NoOfficialCid:
			assignStatusNoOfficialCid();
			break;
		case NoHtc:
			assignStatusNoHtc();
			break;
		}

		this.status = status;
	}

	public enum Status
	{
		Simple, Advanced, NoOfficialCid, NoHtc
	}
}
