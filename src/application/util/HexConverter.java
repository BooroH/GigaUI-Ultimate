package application.util;

public class HexConverter {
	// 0~100 → #000000 ~ #FFFFFF
	public static String intToHex(int r, int g, int b) {
		// 0일 때 0, 100일 때 255
		int r_hex = (int) Math.round((r / 100.0) * 255);
		int g_hex = (int) Math.round((g / 100.0) * 255);
		int b_hex = (int) Math.round((b / 100.0) * 255);
		return String.format("%02x%02x%02x", r_hex, g_hex, b_hex); // 소문자 HEX
	}

	// "000000" ~ "ffffff" → 0 ~ 100
	public static int hexToInt(String hexColor) {
		// '#' 제거 후 앞 2자리 (R)만 읽음 (grayscale이라 R=G=B)
		String rHex = hexColor.substring(0, 2);
		int gray = Integer.parseInt(rHex, 16);

		// 0~255 → 0~100 스케일로 변환
		return (int) Math.round((gray / 255.0) * 100);
	}
}
