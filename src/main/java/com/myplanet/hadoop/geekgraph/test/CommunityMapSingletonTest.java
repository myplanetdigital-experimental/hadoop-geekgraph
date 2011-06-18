/**
 * 
 */
package com.myplanet.hadoop.geekgraph.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.myplanet.hadoop.geekgraph.CommunityMapSingleton;

/**
 * @author cleaver
 *
 */
public class CommunityMapSingletonTest {

	/**
	 * Test method for {@link com.myplanet.hadoop.geekgraph.CommunityMapSingleton#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		CommunityMapSingleton s = CommunityMapSingleton.getInstance();
		assertNotNull(s);
	}

	/**
	 * Test method for {@link com.myplanet.hadoop.geekgraph.CommunityMapSingleton#findCommunity(Long)}.
	 */
	@Test
	public void testFindCommunity() {
		String php = CommunityMapSingleton.findCommunity(new Long((long)928961));
		assertEquals(php, "php");
		String drupal = CommunityMapSingleton.findCommunity(new Long((long)814641));
		assertEquals(drupal, "drupal");
		String rails = CommunityMapSingleton.findCommunity(new Long((long)14561327));
		assertEquals(rails, "rails");
	}

}
