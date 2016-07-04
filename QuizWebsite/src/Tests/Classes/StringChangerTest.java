package Tests.Classes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Main.StringChanger;

public class StringChangerTest {

	private static StringChanger stringChanger;
	private String before;
	
	@BeforeClass
	public static void init()
	{
		stringChanger = new StringChanger();
	}
	
	@Before
	public void emptyBefore()
	{
		before = "";
	}
	
	@Test
	public void testBasic() {
		before = "abc";
		assertEquals(before, stringChanger.changeString(before));
	}

	@Test
	public void testBasic2() {
		before = "";
		assertEquals(before, stringChanger.changeString(before));
	}
	
	@Test
	public void testBasic3() {
		before = "&";
		assertEquals("&amp;", stringChanger.changeString(before));
	}
	
	@Test
	public void test1() {
		before = "&azz&bb<aa>";
		assertEquals("&amp;azz&amp;bb&lt;aa&gt;", stringChanger.changeString(before));
	}
	
	@Test
	public void test2() {
		before = "&&<>";
		assertEquals("&amp;&amp;&lt;&gt;", stringChanger.changeString(before));
	}
	

	@Test
	public void test3() {
		before = "<<<";
		assertEquals("&lt;&lt;&lt;", stringChanger.changeString(before));
	}
	
	@Test
	public void test4() {
		before = "ab><&ba";
		assertEquals("ab&gt;&lt;&amp;ba", stringChanger.changeString(before));
	}
}
