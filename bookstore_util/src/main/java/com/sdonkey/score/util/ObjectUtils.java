
package com.sdonkey.score.util;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ObjectUtils {
	
	/**
	 * 判断对象为空
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public static boolean isEmpty(Object object) {
		// if (object instanceof String) {
		if (object == null || "".equals(object)) {
			return true;
		}
		// }
		// if (object instanceof Integer) {
		// if (object == null || "".equals(object)) {
		// return true;
		// }
		// }
		// if (object instanceof Double) {
		// if (object == null || "".equals(object)) {
		// return true;
		// }
		// }
		// if (object instanceof Float) {
		// if (object == null || "".equals(object)) {
		// return true;
		// }
		// }
		return false;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static boolean isEmpty(Collection collection) {
		if (collection instanceof List) {
			if (collection == null || collection.size() == 0) {
				return true;
			}
		}
		if (collection instanceof Set) {
			if (collection == null || collection.size() == 0) {
				return true;
			}
		}
		if (collection instanceof Map) {
			if (collection == null || collection.size() == 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断对象不为空
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public static boolean isNotEmpty(Object object) {
		if (object != null) {
			if (object instanceof String) {
				if (!"".equals(object)) {
					return true;
				}else{
					return false;
				}
			}
			if (object instanceof Long) {
				if (object != new Long(0) && !"".equals(object)) {
					return true;
				}else{
					return false;
				}
			}
			if (object instanceof Integer) {
				if (object != new Integer(0) && !"".equals(object)) {
					return true;
				}else{
					return false;
				}
			}
			if (object instanceof Double) {
				if (object != new Double(0.0) && !"".equals(object)) {
					return true;
				}else{
					return false;
				}
			}
			if (object instanceof Float) {
				if (object != new Float(0.0) && !"".equals(object)) {
					return true;
				}else{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public static boolean isNotEmpty(Collection collection) {
		// if (collection instanceof List) {
		if (collection != null && collection.size() > 0) {
			return true;
		}
		// }
		// if (collection instanceof Set) {
		// if (collection != null && collection.size() > 0) {
		// return true;
		// }
		// }
		// if (collection instanceof Map) {
		// if (collection != null && collection.size() > 0) {
		// return true;
		// }
		// }
		return false;
	}

	/**
	 * 判断array为空
	 * 
	 * @param integer
	 * @return
	 * @throws Exception
	 */
	public static boolean isNotEmpty(Object[] array) {
		if (array != null && array.length > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 判断Date为空
	 * 
	 * @param dou
	 * @return
	 * @throws Exception
	 */
	public static boolean isNotEmpty(Date date) {
		if (date != null && !"".equals(date)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断对象不为空
	 * 
	 * @param string
	 * @return
	 * @throws Exception
	 */
	public static boolean isNotNull(Object object) {
		// if (object instanceof String) {
		if (object != null) {
			return true;
		}
		// }
		// if (object instanceof Integer) {
		// if (object != null) {
		// return true;
		// }
		// }
		// if (object instanceof Double) {
		// if (object != null) {
		// return true;
		// }
		// }
		// if (object instanceof Float) {
		// if (object != null) {
		// return true;
		// }
		// }
		return false;
	}

}
