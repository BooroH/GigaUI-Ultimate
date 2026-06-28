package application.util;

public class HexConverter {
	// 0~100 → #000000 ~ #FFFFFF
	public static String intToHex(int p) {
		// 0일 때 0, 100일 때 255
		int r_hex = (int) Math.round((p / 100.0) * 255) - 4;
		int g_hex = (int) Math.round((p / 100.0) * 255) - 2;
		int b_hex = (int) Math.round((p / 100.0) * 255);
		return String.format("%02x%02x%02x", r_hex, g_hex, b_hex); // 소문자 HEX
	}

	// "000000" ~ "ffffff" → 0 ~ 100
	public static int hexToInt(String hexColor) {
		String rHex = hexColor.substring(4, 6);
		int gray = Integer.parseInt(rHex, 16);

		// 0~255 → 0~100 스케일로 변환
		return (int) Math.round((gray / 255.0) * 100);
	}
}
