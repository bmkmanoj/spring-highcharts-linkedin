package com.adp.service;

import com.adp.beans.DataBean;
import com.adp.beans.SeriesBean;
import com.adp.linkedin.LinkedIn;
import com.adp.linkedin.schema.Connections;
import com.adp.linkedin.schema.Person;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ChartService {
	String licToken = "75nc2v9ibjl41b";
	String licSecret = "nY9OW84F5rAD7AKM";
	String liToken = "ed30ff5a-0c9b-4284-97a0-dc82d35a5270";
	String liSecret = "2de594c4-d514-48c1-8a31-83f349cce87f";

	Connections connections;
	
	public DataBean getLineChartData1() {
		List<SeriesBean> list = new ArrayList<SeriesBean>();

		LinkedIn api = new LinkedIn(licToken, licSecret, liToken, liSecret);

		Person person = api.getProfileForCurrentUser();
		connections = api.getConnectionsForCurrentUser();

		List<Person> lst = (List<Person>) connections.getPersonList();

		int i = 0;

		List<String> cityArr = new ArrayList<String>();

		while (lst != null && i < lst.size() && lst.get(i) != null
				&& null != lst.get(i).getLocation()) {

			String name = lst.get(i).getLocation().getName();
			if (name.indexOf(" Area,") != -1) {
				String test = name.split("Area,")[0];
				cityArr.add(test);
			} else if (name.indexOf(", ") != 1) {
				String test = name.split(", ")[0];
				cityArr.add(test);
			} else {
				cityArr.add(name);
			}
			i++;
		}

		HashMap<String, Integer> locationMap = new HashMap<String, Integer>();
		ValueComparator bvc = new ValueComparator(locationMap);
		TreeMap<String, Integer> sortedLocationMap = new TreeMap<String, Integer>(
				bvc);

		int j = 0;
		while (j < cityArr.size()) {
			String key = cityArr.get(j);
			Integer val = locationMap.get(key);
			if (val != null) {
				locationMap.put(key, new Integer(val + 1));
			} else {
				locationMap.put(key, 1);
			}
			j++;
		}

		sortedLocationMap.putAll(locationMap);

		String[] cityNames = new String[10];
		int[] cityCounts = new int[10];

		int k = 0;

		for (Map.Entry<String, Integer> entry : sortedLocationMap.entrySet()) {
			cityNames[k] = entry.getKey();
			cityCounts[k] = entry.getValue();
			k++;
			if (k == 10)
				break;
		}

		list.add(new SeriesBean("Location", "#3366cc", cityCounts));
		return new DataBean("chart1-container", "Top Cities",
				"Cities Count", "City Names", Arrays.asList(cityNames), list);
	}

	public DataBean getLineChartData2() {
		List<SeriesBean> list = new ArrayList<SeriesBean>();
		
		String[] categories = new String[] { "2 Jan '13", "4 Feb '13",
				"5 Mar '13", "10 Apr '13", "8 May '13", "30 Jun '13",
				"3 Jul '13", "8 Aug '13", "5 Sep '13", "17 Oct '13",
				"23 Nov '13", "5 Dec '13" };
		return new DataBean("chart2-container", "Top Companies",
				"Companies Count", "Company Names", Arrays.asList(categories), list);
	}

	public DataBean getLineChartData3() {
		List<SeriesBean> list = new ArrayList<SeriesBean>();
		

		String[] categories = new String[] { "4 Jan '13", "14 Feb '13",
				"15 Mar '13", "11 Apr '13", "19 May '13", "23 Jun '13",
				"3 Jul '13", "8 Aug '13", "5 Sep '13", "17 Oct '13",
				"23 Nov '13", "5 Dec '13" };
		return new DataBean("chart3-container", "Top Groups",
				"Groups Count", "Group Names", Arrays.asList(categories), list);
	}

	private static class ValueComparator implements Comparator<String> {

		Map<String, Integer> base;

		public ValueComparator(Map<String, Integer> base) {
			this.base = base;
		}

		// Note: this comparator imposes orderings that are inconsistent with
		// equals.
		public int compare(String a, String b) {
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			} // returning 0 would merge keys
		}
	}
}
