package com.bueffeltier.ui.webapp;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.javatuples.Pair;

public class ViewDataService
{
	private static ViewDataService instance;

	private ViewDataService()
	{

	}

	public static ViewDataService getInstance()
	{
		if (instance == null)
		{
			instance = new ViewDataService();
		}
		return instance;
	}

	public void
	    addReceivedData(HttpServletRequest request, String key, String value)
	{
		// TODO sveng 11.06.2023: empfänger seite zur überprüfung?

		Pair<String, String> dataPair = Pair.with(key, value);

		addToReceivedViewDataRequestAttribute(request, dataPair);
	}

	public void
	    addForwardingData(HttpServletRequest request, String key, String value)
	{
		// TODO sveng 11.06.2023: empfänger seite zur überprüfung?

		Pair<String, String> dataPair = Pair.with(key, value);

		addToForwardingViewDataRequestAttribute(request, dataPair);
	}

	private void addToReceivedViewDataRequestAttribute(
	    HttpServletRequest request,
	    Pair<String, String> dataPair
	)
	{
		List<Pair<String, String>> dataPairs = new ArrayList<>(
		    getReceivedViewDataPairs(request)
		);

		dataPairs.add(dataPair);

		request.setAttribute("receivedViewData", dataPairs);
	}

	private void addToForwardingViewDataRequestAttribute(
	    HttpServletRequest request,
	    Pair<String, String> dataPair
	)
	{
		List<Pair<String, String>> dataPairs = new ArrayList<>(
		    getForwardedViewDataPairs(request)
		);

		dataPairs.add(dataPair);

		request.setAttribute("forwardedViewData", dataPairs);
	}

	public List<Pair<String, String>>
	    getReceivedViewDataPairs(HttpServletRequest request)
	{
		@SuppressWarnings("unchecked")
		List<Pair<String, String>> dataPairs = (List<Pair<String, String>>) request
		    .getAttribute("receivedViewData");

		if (dataPairs != null)
		{
			return dataPairs;

		} else
		{
			return new ArrayList<Pair<String, String>>();
		}
	}

	public List<Pair<String, String>>
	    getForwardedViewDataPairs(HttpServletRequest request)
	{
		@SuppressWarnings("unchecked")
		List<Pair<String, String>> dataPairs = (List<Pair<String, String>>) request
		    .getAttribute("forwardedViewData");

		if (dataPairs != null)
		{
			return dataPairs;

		} else
		{
			return new ArrayList<Pair<String, String>>();
		}
	}

	public boolean hasReceivedData(HttpServletRequest request)
	{
		boolean hasData;

		List<Pair<String, String>> dataList = getReceivedViewDataPairs(request);

		if (dataList != null && dataList.size() > 0)
		{
			hasData = true;

		} else
		{
			hasData = false;
		}

		return hasData;
	}

	public boolean hasForwardingData(HttpServletRequest request)
	{
		boolean hasData;

		List<Pair<String, String>> dataList = getForwardedViewDataPairs(
		    request
		);

		if (dataList != null && dataList.size() > 0)
		{
			hasData = true;

		} else
		{
			hasData = false;
		}

		return hasData;
	}

	public String getValueOpt(HttpServletRequest request, String key)
	{
		String value = null;

		if (this.hasForwardingData(request))
		{
			value = getForwardedDataValueOpt(request, key);

		} else
		{
			value = getReceivedDataValueOpt(request, key);
		}

		return value;
	}

	private String
	    getReceivedDataValueOpt(HttpServletRequest request, String key)
	{
		String value = null;

		List<Pair<String, String>> pairList = getReceivedViewDataPairs(request);

		for (Pair<String, String> pair : pairList)
		{
			if (pair.getValue0().equals(key))
			{
				value = pair.getValue1();
			}
		}

		return value;
	}

	private String
	    getForwardedDataValueOpt(HttpServletRequest request, String key)
	{
		String value = null;

		List<Pair<String, String>> pairList = getForwardedViewDataPairs(
		    request
		);

		for (Pair<String, String> pair : pairList)
		{
			if (pair.getValue0().equals(key))
			{
				value = pair.getValue1();
			}
		}

		return value;
	}

	public void clearReceivedData(HttpServletRequest request)
	{
		request.removeAttribute("receivedViewData");
	}

	public void clearForwardViewData(HttpServletRequest request)
	{
		request.removeAttribute("forwardedViewData");
	}
}
