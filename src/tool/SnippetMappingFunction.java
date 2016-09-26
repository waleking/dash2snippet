package tool;

public class SnippetMappingFunction {
	/**
	 * 2	0000000000000000000002 
	 * 32	0000000000000000000032 
	 * 332	0000000000000000000332
	 * @param id
	 * @return
	 */
	public static String turnIntToString22(int id) {
		String strId = Integer.toString(id);
		int len = 22 - strId.length();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			sb.append("0");
		}
		return sb.toString() + strId;
	}

	public static void main(String[] args) {
		System.out.println(turnIntToString22(32));
		System.out.println(turnIntToString22(332));
		System.out.println(turnIntToString22(3332));
	}
}
