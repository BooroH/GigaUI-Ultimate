package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class Fonts {

	public static void readFonts(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Fonts.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;

		// 일반 폰트 사이즈
		p = Pattern.compile("<Font[^>]*name=\"NORMAL\"[^>]*size=\"(\\d+)\"");
		m = p.matcher(content);

		if (m.find()) {
			settings.setFontSize(Integer.parseInt(m.group(1)) - 13);
		}

		// 쿨타임 폰트 사이즈
		p = Pattern.compile("<Font[^>]*name=\"SMALL\"[^>]*size=\"(\\d+)\"");
		m = p.matcher(content);

		if (m.find()) {
			settings.setCDFontSize(Integer.parseInt(m.group(1)));
		}

		// 쿨타임 폰트 두께
		p = Pattern.compile("<Font[^>]*name=\"SMALL\"[^>]*face=\"([^\"]+)\"");
		m = p.matcher(content);

		if (m.find()) {
			settings.setCDFontWeight(m.group(1));
		}

		// 3d 노말 폰트 사이즈
		p = Pattern.compile("<Font[^>]*name=\"3D_TEXT\"[^>]*size=\"(\\d+)\"");
		m = p.matcher(content);

		if (m.find()) {
			settings.setOverheadFontSize(Integer.parseInt(m.group(1)));
		}

		// 3d 노말 폰트 두께
		p = Pattern.compile("<Font[^>]*name=\"3D_TEXT\"[^>]*face=\"([^\"]+)\"");
		m = p.matcher(content);

		if (m.find()) {
			settings.setOverheadFontWeight(m.group(1));
		}

	}

	public static void writeFonts(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Fonts.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);
		
		boolean is_OTF_medium_included = false;

		// 쿨타임폰트
		content = content.replaceAll("var_cd_font_size", String.valueOf(settings.getCDFontSize()));
		content = content.replaceAll("var_cd_font_weight", settings.getCDFontWeight());
		
		// 3d폰트
		content = content.replaceAll("var_3d_normal_size", String.valueOf(settings.getOverheadFontSize()));
		content = content.replaceAll("var_3d_normal_font", settings.getOverheadFontWeight());
		
		// 3d스몰
		content = content.replaceAll("var_3d_small_size", String.valueOf(settings.getOverheadFontSize() - 5));
		if (settings.getOverheadFontWeight().equals("GG Text Regular")) {
			content = content.replaceAll("var_3d_small_font", "GG Text Regular");
		} else if (settings.getOverheadFontWeight().equals("GG OTF Medium")) {
			content = content.replaceAll("var_3d_small_font", "GG Text Regular");
			is_OTF_medium_included = true;
		} else if (settings.getOverheadFontWeight().equals("GG Bold")) {
			content = content.replaceAll("var_3d_small_font", "GG OTF Medium");
			is_OTF_medium_included = true;
		}
		
		if(is_OTF_medium_included) {
			content = content.replaceAll("var_import_font", "<FontMemResource name=\"Gui/Customized/Fonts/GG-OTF-Medium.otf\"/>");
		}
		else {
			content = content.replaceAll("var_import_font", "");
		}

		// 전체폰트사이즈
		// 노멀사이즈
		content = content.replaceAll("var_normal_size", String.valueOf(settings.getFontSize() + 13));
		// 라지사이즈
		content = content.replaceAll("var_large_size", String.valueOf(settings.getFontSize() + 15));
		// 라지볼드사이즈
		content = content.replaceAll("var_large_bold_size", String.valueOf(settings.getFontSize() + 15));
		// 휴지사이즈
		content = content.replaceAll("var_huge_size", String.valueOf(settings.getFontSize() + 17));
		// 하이보리안 스몰
		content = content.replaceAll("var_hyb_small_size", String.valueOf(settings.getFontSize() + 14));
		// 하이보리안 스몰
		content = content.replaceAll("var_hyb_large_size", String.valueOf(settings.getFontSize() + 18));
		// 스케일드
		content = content.replaceAll("var_scaled_size", String.valueOf(settings.getFontSize() + 21));
		// 빅그룹
		content = content.replaceAll("var_medium_size", String.valueOf(settings.getFontSize() + 14));

		FileManager.stringToFile(targetDir, content);

	}
}
