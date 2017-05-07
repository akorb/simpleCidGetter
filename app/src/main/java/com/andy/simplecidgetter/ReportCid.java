package com.andy.simplecidgetter;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

import javax.net.ssl.HttpsURLConnection;

public class ReportCid extends AsyncTask<String, Integer, Object>
{

    @Override
    protected Object doInBackground(String... args)
    {
        URL url = null;
        try
        {
            url = new URL("https://andykorb.puppis.uberspace.de/SCIDReport.php");
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        byte[] outputArray = ("cid=" + args[0]).getBytes();

        HttpsURLConnection urlConnection = null;
        try
        {
            urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        try {
            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write(outputArray);
            out.close();

            urlConnection.getInputStream();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally {
            urlConnection.disconnect();
        }
        return null;
    }

}
