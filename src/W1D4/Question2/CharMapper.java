package W1D4.Question2;

import java.util.HashMap;
import java.util.Map;

public class CharMapper {
	private Map<Character, Integer[]> charMaps = new HashMap<Character, Integer[]>();

	public CharMapper() {
	}

	public void addCharacter(String s) {
		if (charMaps.containsKey(s.charAt(0))) {
			Integer[] initVal = charMaps.get(s.charAt(0));
			initVal[0] += s.length();
			initVal[1]++;
			charMaps.replace(s.charAt(0), initVal);
		} else {
			Integer[] value = { s.length(), 1 };
			charMaps.put(s.charAt(0), value);
		}
	}

	public Map<Character, Integer[]> getCharMap() {
		return charMaps;
	}

	@Override
	public String toString() {
		String output = "";
		for (Map.Entry<Character, Integer[]> charMap : charMaps.entrySet()) {

			output += "< " + charMap.getKey() + " , [" + charMap.getValue()[0] + ", " + charMap.getValue()[1] + "] >\n";
		}
		return output;
	}

}
