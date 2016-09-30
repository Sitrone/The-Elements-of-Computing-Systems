import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Code {
	private Code(){}
	
	private static final Map<String, String> myDest = destMap();
	
	private static Map<String, String> destMap(){
		Map<String, String> result = new HashMap<String, String>();
		result.put("null", "000");
		result.put("M",    "001");
		result.put("D",    "010");
		result.put("MD",   "011");
		result.put("A",    "100");
		result.put("AM",   "101");
		result.put("AD",   "110");
		result.put("ADM",  "111");
		return Collections.unmodifiableMap(result);
	}
	
	
	/**
	 * 获取对应的dest代码
	 * @param target
	 * @return
	 */
	public static String dest(String target){
		String resultBits = null;

		if(myDest.containsKey(target)){
			resultBits = myDest.get(target);
		}
		return resultBits;
	}
	
	// comp 获取
	private static final Map<String, String> myComp = compMap();
	
	private static Map<String, String> compMap(){
		Map<String, String> result = new HashMap<String, String>();
		result.put("0",   "0101010");
		result.put("1",   "0111111");
		result.put("-1",  "0111010");
		result.put("D",   "0001100");
		result.put("A",   "0110000");
		result.put("!D",  "0001101");
		result.put("!A",  "0110001");
		result.put("-D",  "0001111");
		result.put("-A",  "0110011");
		result.put("D+1", "0011111");
		result.put("A+1", "0110111");
		result.put("D-1", "0001110");
		result.put("A-1", "0110010");
		result.put("D+A", "0000010");
		result.put("D-A", "0010011");
		result.put("A-D", "0000111");
		result.put("D&A", "0000000");
		result.put("D|A", "0010101");
		
		result.put("M",   "1110000");
		result.put("!M",  "1110001");
		result.put("-M",  "1110011");
		result.put("M+1", "1110111");
		result.put("M-1", "1110010");
		result.put("D+M", "1000010");
		result.put("D-M", "1010011");
		result.put("M-D", "1000111");
		result.put("D&M", "1000000");
		result.put("D|M", "1010101");
		return Collections.unmodifiableMap(result);
	}
	
	/**
	 * 
	 * @param target
	 * @return
	 */
	public static String comp(String target){
		String resultBits = null;

		if(myComp.containsKey(target)){
			resultBits = myComp.get(target);
		}
		return resultBits;
	}
	
	// jump获取
	private static final Map<String, String> myJump = jumpMap();
	
	private static Map<String, String> jumpMap(){
		Map<String, String> result = new HashMap<String, String>();
		result.put("null",  "000");
		result.put("JGT",   "001");
		result.put("JEQ",   "010");
		result.put("JGE",   "011");
		result.put("JLT",   "100");
		result.put("JNE",   "101");
		result.put("JLE",   "110");
		result.put("JMP",  "111");
		return Collections.unmodifiableMap(result);
	}
	
	public static String jump(String target){
		String resultBits = null;

		if(myJump.containsKey(target)){
			resultBits = myJump.get(target);
		}
		return resultBits;
	}
}
