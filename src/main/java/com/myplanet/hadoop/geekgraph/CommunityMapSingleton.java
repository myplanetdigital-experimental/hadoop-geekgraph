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
    private static final CommunityMapSingleton INSTANCE = new CommunityMapSingleton();
    private static HashMap<Integer, String> map;
    
    // Private constructor prevents instantiation from other classes
    private CommunityMapSingleton() {
    	map.put(new Integer(14561327), "rails");
    	map.put(new Integer(14761655), "rails");
    	map.put(new Integer(9462972), "rails");
    	map.put(new Integer(10230812), "rails");

    	map.put(new Integer(814641), "drupal");
    	map.put(new Integer(5243), "drupal");
    	map.put(new Integer(2789291), "drupal");

    	map.put(new Integer(281670654), "nyancat");

    	map.put(new Integer(183749519), "startups");
    	map.put(new Integer(22747624), "startups");
    	map.put(new Integer(1000591), "startups");
    	map.put(new Integer(7517052), "startups");
    	map.put(new Integer(30115414), "startups");
    	map.put(new Integer(7541302), "startups");
    	map.put(new Integer(5520332), "startups");

    	map.put(new Integer(14309129), "php");
    	map.put(new Integer(928961), "php");
    	map.put(new Integer(6507862), "php");
    	map.put(new Integer(16030747), "php");

    	map.put(new Integer(752673), "jquery");
    	map.put(new Integer(22173676), "jquery");
    	map.put(new Integer(16502211), "jquery");

    	map.put(new Integer(18824526), "django");
    	map.put(new Integer(11385212), "django");
    	map.put(new Integer(12497), "django");
    	map.put(new Integer(1392281), "django");
    }
 
    public static CommunityMapSingleton getInstance() {
        return INSTANCE;
    }
    
    public static String findCommunity(Integer id) {
    	return map.get(id);
    }
}
