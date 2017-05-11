package com.bxw.sms_sdk;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: UrlAscllSort 
 * @Description: URL参数ascll排序 
 * @author tangzhilong 
 * @date 2017年5月10日 下午7:26:17 
 *
 */
public class UrlAscllSort {
	
	private static final String URL_ENCODE = "utf-8";
	
	/**
	 * 
	 * @Title: formatUrlMap 
	 * @Description: 排序 
	 * @param paraMap
	 * @param urlEncode
	 * @param keyToLower
	 * @return
	 * String
	 * @throws
	 */
	public static String formatUrlMap(Map<String, String> paraMap, boolean keyToLower) {
		String buff = "";
		Map<String, String> tmpMap = paraMap;
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(tmpMap.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds, new Comparator<Map.Entry<String, String>>() {

				public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
					return (o1.getKey()).toString().compareTo(o2.getKey());
				}
			});
			// 构造URL 键值对的格式
			StringBuilder buf = new StringBuilder();
			for (Map.Entry<String, String> item : infoIds) {
				if (item.getKey() !=null && item.getKey().trim() !="") {
					String key = item.getKey();
					String val = item.getValue();
					val = URLEncoder.encode(val, URL_ENCODE);
					if (keyToLower) {
						buf.append(key.toLowerCase() + "=" + val);
					} else {
						buf.append(key + "=" + val);
					}
					buf.append("&");
				}

			}
			buff = buf.toString();
			if (buff.isEmpty() == false) {
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e) {
			return null;
		}
		return buff;
	}
}
