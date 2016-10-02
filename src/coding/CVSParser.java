package coding;

import java.util.ArrayList;
import java.util.List;

public class CVSParser {
	/*
	 * case 1: 
	 * 	input: a,b,c\nd,e,f
	 * 	output:[[a,b,c],[d,e,f]]
	 *  case 2:
	 *  input: a,b,"c\nd,e",f
	 *  output: [a, b, "c\nd,e", f]
	 */
	public List<List<String>> parse(String str){
		List<List<String>> result = new ArrayList<>();
		if(str == null || str.length() == 0){
			return result;
		}
		List<String> list = new ArrayList<>();
		int start = 0, end = 0;
		boolean inQuote = false;
		while(end <= str.length()){
			if(end == str.length()){
				addSubstring(list, start, end, str);
				result.add(list);
				break;
			}
			char ch = str.charAt(end);
			switch(ch){
				case ',':
				case '\n':
					if(inQuote){
						end++;
						continue;
					}
					addSubstring(list, start, end, str);
					if(ch == '\n'){
						result.add(list);
						list = new ArrayList<>();
					}
					start = ++end;
					break;
				default:
					end++;
					if(ch == '"'){
						inQuote = !inQuote;
					}
					break;
			}
		}
		return result;
	}
	private void addSubstring(List<String> list, int start, int end, String str){
		if(start == end) return;
		list.add(str.substring(start, end));
	}
	
	public static void main(String[] args){
		CVSParser parser = new CVSParser();
		System.out.println(parser.parse("a,b,c\nd,e,f"));
		System.out.println(parser.parse("a,b,\"c\nd,e\",f"));
	}
}
