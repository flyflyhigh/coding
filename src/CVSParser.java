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
				list.add(str.substring(start, end));
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
				list.add(str.substring(start, end));
				if(ch == '\n'){
					result.add(list);
					list = new ArrayList<>();
				}
				start = ++end;
				break;
			case '"':
				inQuote = !inQuote;
			default:
				end++;
				break;
			}
		}
		return result;
	}
}
