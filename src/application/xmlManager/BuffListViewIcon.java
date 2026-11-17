package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;
import application.util.HexConverter;

public class BuffListViewIcon {

	public static void readBuffListViewIcon(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/BuffGUI/BuffListViewIcon.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;
		
		//스택 컬러
		p = Pattern.compile("<TextView\\s+name=\"StackSizeView\"[^>]*default_color=\"#([0-9a-fA-F]{6})\"");

		m = p.matcher(content);

		if (m.find()) {
			String color = m.group(1);
			settings.setStackColor(color);
		}
		
		//타이머 컬러
		p = Pattern.compile("<TextView\\s+name=\"RemainingTimeView\"[^>]*default_color=\"#([0-9a-fA-F]{6})\"");
		m = p.matcher(content);

		if (m.find()) {
			String color = m.group(1);
			settings.setBuffTimerColor(color);
		}

		//스택 두께
		p = Pattern.compile("<TextView\\s+name=\"StackSizeView\"[^>]*font=\"([^\"]+)\"");
		m = p.matcher(content);
		
		String stackWeight = "Medium";

		if (m.find()) {
			String font = m.group(1);
			
			if(font.equals("NORMAL_BOLD"))
				stackWeight = "Bold";
		}
		
		settings.setStackWeight(stackWeight);
		
		//타이머 두께
		p = Pattern.compile("<TextView\\s+name=\"RemainingTimeView\"[^>]*font=\"([^\"]+)\"");
		m = p.matcher(content);
		
		boolean isTimerBold = false;

		if (m.find()) {
			String font = m.group(1);
			
			if(font.equals("NORMAL_BOLD"))
				isTimerBold = true;
		}
		
		settings.setBuffTimerBold(isTimerBold);

		// 스택 레이아웃
		if (content.contains("StackSizeContainer")) {
			settings.setBuff_stack_loc(1);
		} else {
			settings.setBuff_stack_loc(0);
		}
		
		//타이머 레이아웃
		if (content.contains("RemainingTimeContainer")) {
			settings.setTimerLayout("Below");
		} else {
			settings.setTimerLayout("On");
		}
		
		//플레이트 알파
		p = Pattern.compile("view_local_alpha=\"([0-9]*\\.?[0-9]+)\"");

		m = p.matcher(content);

		if (m.find()) {
			double alpha = Double.parseDouble(m.group(1));
			settings.setStackPlateAlpha(alpha);
		}

	}

	public static void writeBuffListViewIcon(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/Views/BuffGUI/BuffListViewIcon.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);

		String fullCode;

		//스택 폰트두께
		String stackWeight;
		if (settings.getStackWeight().equals("Bold")) {
			stackWeight = " font=\"NORMAL_BOLD\"";
		} else {
			stackWeight = "";
		}
		
		//타이머 폰트두께
		String timerWeight;
		if (settings.isBuffTimerBold()) {
			timerWeight = " font=\"NORMAL_BOLD\"";
		} else {
			timerWeight = "";
		}
		
		//스택컬러
		String stackColor = settings.getStackColor();
		
		//타이머컬러
		String timerColor = settings.getBuffTimerColor();
		
		//타이머레이아웃
		String timerLayout = settings.getTimerLayout();

		//스택레이아웃 0=on 1=above
		int stackLoc = settings.getBuff_stack_loc();

		//플레이트알파
		double plateAlpha = settings.getStackPlateAlpha();
		String plateAlpha_write = String.valueOf(plateAlpha);
		
		//스택 아웃라인 여부
		String stackOutline;
		if(plateAlpha<0.1 || stackLoc==0) {
			stackOutline = " feature_flags=\"TVF_RENDER_SHADOW|TVF_RENDER_HALO\"";
		}
		else {
			stackOutline = "";
		}
		
		//타이머 아웃라인 여부
		String timerOutline;
		if(plateAlpha<0.1 || timerLayout.equals("On")) {
			timerOutline = " feature_flags=\"TVF_RENDER_SHADOW|TVF_RENDER_HALO\"";
		}
		else {
			timerOutline = "";
		}
		
		//폰트크기별 플레이트 크기 지정
		int fontSize = settings.getFontSize();
		String stackPlateImgFile;
		String timerPlateImgFile;
		String stackPlateSize;
		String timerPlateSize;
		int minBuffSize = Math.max(31, Math.min(settings.getLP_buff_size(), Math.min(settings.getRP_buff_size(), settings.getTop_buff_size())));
		
		if(fontSize==0 && (settings.getLP_buff_size()==31 || settings.getRP_buff_size()==31 || settings.getTop_buff_size()==31)) {
			stackPlateImgFile = "a.png";
			timerPlateImgFile = "b.png";
			stackPlateSize = "";
			timerPlateSize = "";
		}
		else {
			stackPlateImgFile = "a_4k.png";
			timerPlateImgFile = "b_4k.png";
			stackPlateSize = " max_size_limit=\"Point("+String.valueOf(minBuffSize)+","+String.valueOf(12+fontSize)+")\"";
			timerPlateSize = " max_size_limit=\"Point("+String.valueOf(minBuffSize)+","+String.valueOf(12+fontSize)+")\"";
		}
		
		String stack_layout_borders = "";
		String timer_layout_borders = "";
		
		if(fontSize==0) {
			stack_layout_borders = " layout_borders=\"Rect(0,-1,0,-1)\"";
			timer_layout_borders = " layout_borders=\"Rect(0,-1,0,-1)\"";
		}
		if(fontSize==1) {
			stack_layout_borders = " layout_borders=\"Rect(0,-2,0,-3)\"";
			timer_layout_borders = " layout_borders=\"Rect(0,-2,0,-3)\"";
		}
		else if(fontSize==2) {
			stack_layout_borders = " layout_borders=\"Rect(0,-1,0,-4)\"";
			timer_layout_borders = " layout_borders=\"Rect(0,-1,0,-4)\"";
		}
		else if(fontSize>2 && fontSize<7) {
			stack_layout_borders = " layout_borders=\"Rect(0,-2,0,-3)\"";
			timer_layout_borders = " layout_borders=\"Rect(0,-2,0,-3)\"";
		}
		else {
			stack_layout_borders = " layout_borders=\"Rect(0,-3,0,-3)\"";
			timer_layout_borders = " layout_borders=\"Rect(0,-3,0,-3)\"";
		}
		
		String stackSmallPlateImgFile;
		String stackSmallPlateSize;
		int extra = 2 * ((fontSize + 1) / 2);
		
		if(fontSize==0) {
			stackSmallPlateImgFile = "s_a.png";
			stackSmallPlateSize = "";
		}
		else {
			stackSmallPlateImgFile = "s_a_4k.png";
			stackSmallPlateSize = " max_size_limit=\"Point("+String.valueOf(15+extra)+","+String.valueOf(12+fontSize)+")\"";
		}
		
		

		if (stackLoc == 1 && timerLayout.equals("Below")) { // above & below
			fullCode = "<View view_layout=\"vertical\">\r\n"
					+ "	<View name=\"StackSizeContainer\" view_layout=\"stacked\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\">\r\n"
					+ "		<ViewSelector name=\"StackSizeBgSelector\">\r\n"
					+ "			<BitmapView bitmap_gfx=\"../../Customized/gfx/buff/"+stackSmallPlateImgFile+"\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\" view_local_alpha=\""+plateAlpha_write+"\""+stackSmallPlateSize+"/>\r\n"
					+ "			<BitmapView bitmap_gfx=\"../../Customized/gfx/buff/"+stackPlateImgFile+"\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\" view_local_alpha=\""+plateAlpha_write+"\""+stackPlateSize+"/>\r\n"
					+ "		</ViewSelector>\r\n"
					+ "		<TextView name=\"StackSizeView\" default_color=\"#"+stackColor+"\""+stackWeight+stackOutline+stack_layout_borders+"/>\r\n"
					+ "	</View>\r\n"
					+ "	<ItemIconView name=\"IconView\" view_flags=\"F_DISABLE_COOLDOWN|F_ALWAYS_SHOW_ENABLED\"/>\r\n"
					+ "	<View name=\"RemainingTimeContainer\" view_layout=\"stacked\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\">\r\n"
					+ "		<BitmapView bitmap_gfx=\"../../Customized/gfx/buff/"+timerPlateImgFile+"\" view_local_alpha=\""+plateAlpha_write+"\""+timerPlateSize+"/>\r\n"
					+ "		<TextView name=\"RemainingTimeView\" default_color=\"#"+timerColor+"\""+timerWeight+timerOutline+timer_layout_borders+"/>\r\n"
					+ "	</View>\r\n"
					+ "</View>";
		}
		else if (stackLoc == 0 && timerLayout.equals("Below")) { // on & below
			fullCode = "<View view_layout=\"vertical\">\r\n"
					+ "	<View view_layout=\"stacked\">\r\n"
					+ "		<ItemIconView name=\"IconView\" view_flags=\"F_DISABLE_COOLDOWN|F_ALWAYS_SHOW_ENABLED\"/>\r\n"
					+ "		<TextView name=\"StackSizeView\" default_color=\"#"+stackColor+"\""+stackWeight+stackOutline+" v_local_alignment=\"TOP\" h_local_alignment=\"RIGHT\"/>\r\n"
					+ "	</View>\r\n"
					+ "	<View name=\"RemainingTimeContainer\" view_layout=\"stacked\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\">\r\n"
					+ "		<BitmapView bitmap_gfx=\"../../Customized/gfx/buff/"+timerPlateImgFile+"\" view_local_alpha=\""+plateAlpha_write+"\""+timerPlateSize+"/>\r\n"
					+ "		<TextView name=\"RemainingTimeView\" default_color=\"#"+timerColor+"\""+timerWeight+timerOutline+timer_layout_borders+"/>\r\n"
					+ "	</View>\r\n"
					+ "</View>";
		}
		else if (stackLoc == 1 && timerLayout.equals("On")) { // above & on
			fullCode = "<View view_layout=\"vertical\">\r\n" 
					+ "	<View name=\"StackSizeContainer\" view_layout=\"stacked\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\">\r\n"
					+ "		<ViewSelector name=\"StackSizeBgSelector\">\r\n"
					+ "			<BitmapView bitmap_gfx=\"../../Customized/gfx/buff/"+stackSmallPlateImgFile+"\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\" view_local_alpha=\""+plateAlpha_write+"\""+stackSmallPlateSize+"/>\r\n"
					+ "			<BitmapView bitmap_gfx=\"../../Customized/gfx/buff/"+stackPlateImgFile+"\" view_flags=\"WID_IGNORE_WHEN_HIDDEN\" view_local_alpha=\""+plateAlpha_write+"\""+stackPlateSize+"/>\r\n"
					+ "		</ViewSelector>\r\n"
					+ "		<TextView name=\"StackSizeView\" default_color=\"#"+stackColor+"\""+stackWeight+stackOutline+stack_layout_borders+"/>\r\n"
					+ "	</View>\r\n"
					+ "	<View view_layout=\"stacked\">\r\n"
					+ "		<ItemIconView name=\"IconView\" view_flags=\"F_DISABLE_COOLDOWN|F_ALWAYS_SHOW_ENABLED\"/>\r\n"
					+ "		<TextView name=\"RemainingTimeView\" default_color=\"#"+timerColor+"\""+timerWeight+timerOutline+" v_local_alignment=\"BOTTOM\"/>\r\n"
					+ "	</View>\r\n"
					+ "</View>";
		}
		else { // on & on
			fullCode = "<View view_layout=\"stacked\">\r\n"
					+ "	<ItemIconView name=\"IconView\" view_flags=\"F_DISABLE_COOLDOWN|F_ALWAYS_SHOW_ENABLED\"/>\r\n"
					+ "	<TextView name=\"StackSizeView\" default_color=\"#"+stackColor+"\""+stackWeight+stackOutline+" v_local_alignment=\"TOP\" h_local_alignment=\"RIGHT\"/>\r\n"
					+ "	<TextView name=\"RemainingTimeView\" default_color=\"#"+timerColor+"\""+timerWeight+timerOutline+" v_local_alignment=\"BOTTOM\"/>\r\n"
					+ "</View>";
		}

		content = content.replaceAll("var_code", fullCode);

		FileManager.stringToFile(targetDir, content);

	}
}
