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
	 * Test method for {@link com.myplanet.hadoop.geekgraph.CommunityMapSingleton#findCommunity(java.lang.Integer)}.
	 */
	@Test
	public void testFindCommunity() {
		String php = CommunityMapSingleton.findCommunity(new Integer(928961));
		assertEquals(php, "php");
		String drupal = CommunityMapSingleton.findCommunity(new Integer(814641));
		assertEquals(drupal, "drupal");
	}

}
