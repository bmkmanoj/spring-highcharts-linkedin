package com.adp.linkedin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.adp.linkedin.schema.Connections;
import com.adp.linkedin.schema.Person;
import com.adp.linkedin.utils.Urls;

public class Main {
	public static void main(String[] args) {
		System.out.println("<Started Main>");

		String licToken = "75nc2v9ibjl41b";
		String licSecret = "nY9OW84F5rAD7AKM";
		String liToken = "ed30ff5a-0c9b-4284-97a0-dc82d35a5270";
		String liSecret = "2de594c4-d514-48c1-8a31-83f349cce87f";

		LinkedIn api = new LinkedIn(licToken, licSecret, liToken, liSecret);

		Person person = api.getProfileForCurrentUser();
		System.out.println(person.getId() + "fname =" + person.getFirstName()
				+ person.getLastName());

		Connections connections = api.getConnectionsForCurrentUser();
		System.out.println(connections.total);

		List<Person> lst = (List<Person>) connections.getPersonList();

		System.out.println("lst.size()" + lst.size());
		int i = 0;

		List<String> cityArr = new ArrayList<String>();

		while (lst != null && i < lst.size() && lst.get(i) != null
				&& null != lst.get(i).getLocation()) {

			String name = lst.get(i).getLocation().getName();
			if (name.indexOf(" Area,") != -1) {
				String test = name.split("Area,")[0];
				cityArr.add(test);
				System.out.println("i: " + i + ",name: " + test);
			} else if (name.indexOf(", ") != 1) {
				// cityArr.add( name.split(", ")[0] ) ;
				String test = name.split(", ")[0];
				cityArr.add(test);
				System.out.println("i: " + i + ",name: " + test);

			} else {
				cityArr.add(name);
				System.out.println("i: " + i + ",name: " + name);
			}

			System.out.println(cityArr.get(i));
			i++;
		}

		// Arrays.sort(strArr);

		HashMap<String, Integer> locationMap = new HashMap<String, Integer>();
		ValueComparator bvc = new ValueComparator(locationMap);
		TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);

		int j = 0;
		while (j < cityArr.size()) {
			String key = cityArr.get(j);
			Integer val = locationMap.get(key);
			if (val != null) {
				locationMap.put(key, new Integer(val + 1));
			} else {
				locationMap.put(key, 1);
			}
			System.out.println(cityArr.get(j));
			j++;
		}

		for (Map.Entry<String, Integer> entry : locationMap.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
		}

		System.out.println("*****");
		sorted_map.putAll(locationMap);

		for (Map.Entry<String, Integer> entry : sorted_map.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
		}

		// System.out.println((List<Person>) connections.getPersonList());
		// If you want the JSON String
		String personJSON = api.getResource(Urls.PERSON_URL_SELF);
		// System.out.println(personJSON);
		// If you want the XML String
		String personXML = api.getResource(Urls.PERSON_URL_SELF, true);

		String str = api.getResource(Urls.PERSON_CONNECTIONS_URL_SELF);

		// System.out.println(str);
		// Or if you want to specify the url yourself, but let the library
		// handle the parsing:
		// Note: obviously this only works if you give it a url that points to a
		// person resource in this case:
		Person person2 = api
				.getProfileForUrl("https://api.linkedin.com/v1/people/~:(id,firstName,lastName)");

		Person person3 = api
				.getProfileForUrl("https://api.linkedin.com/v1/people/~:(id,first-name,last-name,headline,positions:(id,title,company:(id,name,type,size,industry))),connections");

		// System.out.println(person3.getLocation().getName());
		System.out.println("<Finished Main>");
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
