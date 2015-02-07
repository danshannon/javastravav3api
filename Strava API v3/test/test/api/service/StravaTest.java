package test.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.jfairy.Fairy;
import org.junit.Test;

import stravajava.api.v3.service.Strava;
import stravajava.util.Paging;

/**
 * @author dshannon
 *
 */
public class StravaTest {
	private Random random = new Random(System.currentTimeMillis());

	@Test
	public void testConvertToStravaPaging_small() {
		List<Paging> paging = Strava.convertToStravaPaging(new Paging(7,11));
		assertEquals("Should return one paging instruction",1,paging.size());
		assertEquals("Paging instruction should return page number 7",7,paging.get(0).getPage());
		assertEquals("Paging instruction should return page size 11",11,paging.get(0).getPageSize());
		assertEquals("Paging instruction should ignore last 0",0,paging.get(0).getIgnoreLastN());
	}
	
	@Test
	public void testConvertToStravaPaging_large() {
		List<Paging> paging = Strava.convertToStravaPaging(new Paging(2,201));
		assertEquals("Should return 2 paging instructions",2,paging.size());
		Paging first = paging.get(0);
		Paging second = paging.get(1);
		assertEquals("First paging instruction should be page size " + Strava.MAX_PAGE_SIZE,Strava.MAX_PAGE_SIZE,first.getPageSize());
		assertEquals("First paging instruction should be for page 2",2,first.getPage());
		assertEquals("First paging instruction should ignore last 0 records",0,first.getIgnoreLastN());
		assertEquals("Second paging instruction should be page size " + Strava.MAX_PAGE_SIZE,Strava.MAX_PAGE_SIZE,second.getPageSize());
		assertEquals("Second paging instruction should be for page 3",3,second.getPage());
		assertEquals("Second paging instruction should ignore last 198 records",198,second.getIgnoreLastN());
		
	}

	@Test
	public void testIgnoreFirstN_valid() {
		// Create a list
		List<String> list = getRandomList();
		
		// Remove a random number of elements from it
		int size = list.size();
		int firstN = random.nextInt(list.size() - 2) + 1; // i.e. between 1 and list.size() - 1
		List<String> result = Strava.ignoreFirstN(list, firstN);
		assertEquals(size - firstN,result.size());
	}
	
	/**
	 * @return
	 */
	private List<String> getRandomList() {
		List<String> result = new ArrayList<String>();
		int size = random.nextInt(998) + 2; // i.e. must be at least 2
		Fairy fairy = Fairy.create();
		for (int i = 1; i <= size; i++) {
			result.add(fairy.textProducer().sentence());
		}
		return result;
	}

	@Test
	public void testIgnoreFirstN_invalidNegative() {
		List<String> list = getRandomList();
		
		int firstN = -1;
		try {
			Strava.ignoreFirstN(list, firstN);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Can't remove a negative number of items from a list!");
	}
	
	@Test
	public void testIgnoreFirstN_invalidTooLarge() {
		List<String> list = getRandomList();
		list = Strava.ignoreFirstN(list, list.size() + 1);
		assertNotNull(list);
		assertEquals(0,list.size());
	}
	
	@Test
	public void testIgnoreFirstN_zero() {
		List<String> list = getRandomList();
		int size = list.size();
		
		list = Strava.ignoreFirstN(list, 0);
		assertEquals(size,list.size());
	}
	
	@Test
	public void testIgnoreLastN_valid() {
		// Create a list
		List<String> list = getRandomList();
		
		// Remove a random number of elements from it
		int size = list.size();
		int lastN = random.nextInt(list.size() - 2) + 1; // i.e. between 1 and list.size() - 1
		List<String> result = Strava.ignoreLastN(list, lastN);
		assertEquals(size - lastN,result.size());
	}
	
	@Test
	public void testIgnoreLastN_zero() {
		List<String> list = getRandomList();
		int size = list.size();
		
		list = Strava.ignoreLastN(list, 0);
		assertEquals(size,list.size());
	}
	
	@Test
	public void testIgnoreLastN_invalidNegative() {
		List<String> list = getRandomList();
		
		int lastN = -1;
		try {
			Strava.ignoreLastN(list, lastN);
		} catch (IllegalArgumentException e) {
			// Expected
			return;
		}
		fail("Can't remove a negative number of items from a list!");
	}
	
	@Test
	public void testIgnoreLastN_invalidTooLarge() {
		List<String> list = getRandomList();
		list = Strava.ignoreFirstN(list, list.size() + 1);
		assertNotNull(list);
		assertEquals(0,list.size());
	}
}
