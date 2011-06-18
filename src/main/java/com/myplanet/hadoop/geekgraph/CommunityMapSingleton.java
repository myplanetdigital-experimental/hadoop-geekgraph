/**
 * 
 */
package com.myplanet.hadoop.geekgraph;

import java.util.HashMap;

/**
 * @author cleaver
 *
 */
public class CommunityMapSingleton {
	
    private static CommunityMapSingleton instance = new CommunityMapSingleton();
    private static HashMap<Long, String> map;
    
    // Private constructor prevents instantiation from other classes
    private CommunityMapSingleton() {
    	map = new HashMap<Long, String>();
    	map.put(new Long(14561327), "rails");
    	map.put(new Long(14761655), "rails");
    	map.put(new Long(9462972), "rails");
    	map.put(new Long(10230812), "rails");

    	map.put(new Long(814641), "drupal");
    	map.put(new Long(5243), "drupal");
    	map.put(new Long(2789291), "drupal");

    	map.put(new Long(281670654), "nyancat");

    	map.put(new Long(183749519), "startups");
    	map.put(new Long(22747624), "startups");
    	map.put(new Long(1000591), "startups");
    	map.put(new Long(7517052), "startups");
    	map.put(new Long(30115414), "startups");
    	map.put(new Long(7541302), "startups");
    	map.put(new Long(5520332), "startups");

    	map.put(new Long(14309129), "php");
    	map.put(new Long(928961), "php");
    	map.put(new Long(6507862), "php");
    	map.put(new Long(16030747), "php");

    	map.put(new Long(752673), "jquery");
    	map.put(new Long(22173676), "jquery");
    	map.put(new Long(16502211), "jquery");

    	map.put(new Long(18824526), "django");
    	map.put(new Long(11385212), "django");
    	map.put(new Long(12497), "django");
    	map.put(new Long(1392281), "django");
    }
 
    public static CommunityMapSingleton getInstance() {
        return instance;
    }
    
    public static String findCommunity(Long id) {
    	return map.get(id);
    }
}
