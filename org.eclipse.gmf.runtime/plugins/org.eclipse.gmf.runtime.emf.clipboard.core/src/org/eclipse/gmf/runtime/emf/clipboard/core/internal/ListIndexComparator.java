package org.eclipse.gmf.runtime.emf.clipboard.core.internal;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * A comparator that sorts elements according to their relative positions in
 * their containing <code>EList</code>s. The comparison is optimized by
 * caching the element indices so that they need not be repeatedly
 * recomputed (which would render the O(NlogN) algorithm as O(N <sup>2
 * </sup>logN)).
 * 
 * @author Christian W. Damus (cdamus)
 */
public class ListIndexComparator
	implements Comparator {

	private List list;

	private Map map;

	ListIndexComparator(List list) {
		this.list = list;

		// use an identity hash map because we are sorting based on the
		//   order of object references, which are identities
		map = new java.util.IdentityHashMap();
	}

	public int compare(Object obj_1, Object obj_2) {
		Integer int_1 = (Integer) map.get(obj_1);
		Integer int_2 = (Integer) map.get(obj_2);
		if (int_1 == null) {
			int_1 = new Integer(list.indexOf(obj_1));
			map.put(obj_1, int_1);
		}
		if (int_2 == null) {
			int_2 = new Integer(list.indexOf(obj_2));
			map.put(obj_2, int_2);
		}

		return int_1.intValue() - int_2.intValue();
	}
}