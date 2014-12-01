package com.andy.simplecidgetter;

import java.io.IOException;
import java.util.Scanner;

public final class PropertyHelper
{
	private PropertyHelper()
	{
		// Hide constructor
	}
	
	public static String getAll()
	{
		try
		{
			Scanner reader = new Scanner(Runtime.getRuntime().exec("getprop")
					.getInputStream());

			StringBuilder sb = new StringBuilder();
			String seperator = System.getProperty("line.separator");

			while (reader.hasNext())
			{
				String s1 = reader.nextLine();

				sb.append(s1);
				sb.append(seperator);
			}

			reader.close();

			return sb.toString();
		}
		catch (IOException ex)
		{
			return "";
		}
	}

	public static String getPropertyValue(String propertyName)
			throws IOException
	{
		try
		{
			Scanner reader = new Scanner(Runtime.getRuntime()
					.exec("getprop " + propertyName).getInputStream());

			while (reader.hasNext())
			{
				String result = reader.nextLine();
				reader.close();
				return result;
			}

			reader.close();
			return "";
		}
		catch (IOException ex)
		{
			return "";
		}
	}

	public static String getCid()
	{
		try
		{
			// The cid is stored usally stored in ro.cid
			String primaryCid = getPropertyValue("ro.cid");
			if (primaryCid != "")
				return primaryCid;

			// ro.cid does not exist for each device.
			// I guess it's for GPE devices, but this isn't for sure yet.
			String fallbackCid = getPropertyValue("ro.boot.cid");
			if (fallbackCid != "")
				return fallbackCid;

			// When cid does not exist, return empty string.
			return "";
		}
		catch (IOException ex)
		{
			return "";
		}
	}
}
