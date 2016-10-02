package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import coding.CVSParser;

public class CVSParserTest {

	@Test
	public void testDifferntLine() {
		CVSParser parser = new CVSParser();
		List<List<String>> result = parser.parse("a,b,c\nd,e,f");
		List<List<String>> expected = Arrays.asList(Arrays.asList("a", "b", "c"), Arrays.asList("d", "e", "f"));
		assertEquals("different line in different list",result, expected);
	}
	
	@Test
	public void testQuote(){
		CVSParser parser = new CVSParser();
		List<List<String>> result = parser.parse("a,b,\"c\nd,e\",f");
		List<List<String>> expected = Arrays.asList(Arrays.asList("a", "b", "\"c\nd,e\"", "f"));
		assertEquals("string in quote in same list",result, expected);
	}
}
