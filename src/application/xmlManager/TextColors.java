package application.xmlManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.setting.Settings;
import application.util.FileManager;

public class TextColors {
	public static void readTextColors(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/TextColors.xml";

		String fullDir = customizedDir + defaultDir;
		String content = FileManager.fileToString(fullDir);

		Matcher m;
		Pattern p;
		
		if (content.contains("<!-- Giga solution -->")) {
			settings.setDmgTextGigaSolution(true);
		}
		else {
			settings.setDmgTextGigaSolution(false);
		}


	}

	public static void writeTextColors(String customizedDir) {
		Settings settings = Settings.getInstance();
		String defaultDir = "/TextColors.xml";

		String sourceDir = "Data/format" + defaultDir;
		String targetDir = customizedDir + defaultDir;

		FileManager.copyFile(sourceDir, targetDir);
		String content = FileManager.fileToString(targetDir);
		
		String dmgText;
		
		if(settings.isDmgTextGigaSolution()) {
			dmgText = "<!-- Giga solution -->\r\n"
					+ "	<!-- heal -->\r\n"
					+ "	<HTMLFont name=\"self_healed\" color=\"0x5B933D\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"135\" waitonscreen=\"0.90\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"other_healed\" color=\"0x5B9364\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"135\" waitonscreen=\"0.90\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"self_healed_critical\" color=\"0x5B933D\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"1.95\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"other_healed_critical\" color=\"0x5B9364\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"1.95\" direction=\"0\"/>\r\n"
					+ "\r\n"
					+ "	<!-- others attack you normal -->\r\n"
					+ "	<HTMLFont name=\"self_attacked\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"250\" waitonscreen=\"0.55\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_unshielded\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"250\" waitonscreen=\"0.55\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_combo\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"250\" waitonscreen=\"0.55\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_spell\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"245\" waitonscreen=\"0.58\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_environment\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"235\" waitonscreen=\"0.60\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<!-- others attack you crit -->\r\n"
					+ "	<HTMLFont name=\"self_attacked_critical\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"2.15\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_spell_critical\" color=\"0xFFFFFF\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"2.15\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_combo_critical\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"2.30\" direction=\"0\"/>\r\n"
					+ "\r\n"
					+ "	<!-- you attack others normal -->\r\n"
					+ "	<HTMLFont name=\"other_attacked\" color=\"0x8a8a89\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"210\" waitonscreen=\"0.78\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_unshielded\" color=\"0xd7d7d7\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"210\" waitonscreen=\"0.78\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_combo\" color=\"0xFF8040\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"210\" waitonscreen=\"0.78\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_spell\" color=\"0xFFFFFF\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"205\" waitonscreen=\"0.82\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<!-- you attack others crit -->\r\n"
					+ "	<HTMLFont name=\"other_attacked_critical\" color=\"0xd7d7d7\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"2.95\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_spell_critical\" color=\"0xFFFFFF\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"2.90\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_combo_critical\" color=\"0xFF8040\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"3.25\" direction=\"0\"/>\r\n"
					+ "\r\n"
					+ "	<!-- combo name -->\r\n"
					+ "	<HTMLFont name=\"self_combo_name\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"175\" waitonscreen=\"0.75\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_combo_name\" color=\"0xFF8040\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"165\" waitonscreen=\"0.95\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<!-- dodge -->\r\n"
					+ "	<HTMLFont name=\"self_dodged\" color=\"0xFFFFFF\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"210\" waitonscreen=\"0.55\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_dodged\" color=\"0x999999\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"175\" waitonscreen=\"0.75\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<!-- resources normal -->\r\n"
					+ "	<HTMLFont name=\"stamina_gained\" color=\"0x6da0ff\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"130\" waitonscreen=\"0.90\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"stamina_lost\" color=\"0x6da0ff\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"180\" waitonscreen=\"0.65\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"mana_gained\" color=\"0x2222FF\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"130\" waitonscreen=\"0.90\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"mana_lost\" color=\"0x2222FF\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"180\" waitonscreen=\"0.65\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<!-- resources crit -->\r\n"
					+ "	<HTMLFont name=\"stamina_gained_critical\" color=\"0x6da0ff\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"1.80\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"mana_gained_critical\" color=\"0x2222FF\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"1.80\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"stamina_loss_critical\" color=\"0x6da0ff\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"1.80\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"mana_loss_critical\" color=\"0x2222FF\" font-size=\"small\" font-style=\"bold\" font-family='' waitonscreen=\"1.80\" direction=\"0\"/>\r\n"
					+ "\r\n"
					+ "	<!-- misc -->\r\n"
					+ "	<HTMLFont name=\"xp_gained\" color=\"0x9999ff\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"80\" waitonscreen=\"1.50\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"murder_points_gained\" color=\"0xa65300\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"130\" waitonscreen=\"0.95\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"murder_points_gained_murderer\" color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed=\"130\" waitonscreen=\"0.95\" direction=\"-1\"/>";
		}
		else {
			dmgText = "<!-- Colors damage texts -->\r\n"
					+ "	<HTMLFont name=\"self_healed\"			color=\"0x5B933D\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"other_healed\"			color=\"0x5B9364\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"self_healed_critical\"		color=\"0x5B933D\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"2.0\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"other_healed_critical\"		color=\"0x5B9364\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"2.0\" direction=\"-1\"/>\r\n"
					+ "\r\n"
					+ "	<HTMLFont name=\"self_attacked\"			color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked\"			color=\"0x8a8a89\" font-size=\"small\" font-style=\"bold\" font-family=''  speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_unshielded\"		color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_unshielded\"		color=\"0xd7d7d7\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_critical\"		color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_critical\"		color=\"0xd7d7d7\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"0\"/>\r\n"
					+ "\r\n"
					+ "	<HTMLFont name=\"self_attacked_spell\"		color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_spell\"		color=\"0xFFFFFF\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_spell_critical\"		color=\"0xFFFFFF\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_spell_critical\"	color=\"0xFFFFFF\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"0\"/>\r\n"
					+ "\r\n"
					+ "	<HTMLFont name=\"self_attacked_combo\"		color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_combo\"		color=\"0xFF8040\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_combo_critical\"	color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_combo_critical\"	color=\"0xFF8040\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"0\"/>\r\n"
					+ "	<HTMLFont name=\"self_combo_name\"		color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_combo_name\"		color=\"0xFF8040\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<HTMLFont name=\"self_dodged\"			color=\"0xFFFFFF\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_dodged\"			color=\"0x999999\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"self_attacked_environment\"		color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"other_attacked_environment\"	color=\"0xBBBBBB\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<HTMLFont name=\"stamina_gained\"			color=\"0x6da0ff\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"stamina_lost\"			color=\"0x6da0ff\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"mana_gained\"			color=\"0x2222FF\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"mana_lost\"			color=\"0x2222FF\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<HTMLFont name=\"stamina_gained_critical\"		color=\"0x6da0ff\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"mana_gained_critical\"		color=\"0x2222FF\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"stamina_loss_critical\"		color=\"0x6da0ff\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"1\"/>\r\n"
					+ "	<HTMLFont name=\"mana_loss_critical\"			color=\"0x2222FF\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"1\"/>\r\n"
					+ "\r\n"
					+ "	<HTMLFont name=\"xp_gained\"			color=\"0x9999ff\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"50\" waitonscreen=\"3.0\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"murder_points_gained\"		color=\"0xa65300\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"-1\"/>\r\n"
					+ "	<HTMLFont name=\"murder_points_gained_murderer\"	color=\"0xb41d1d\" font-size=\"small\" font-style=\"bold\" font-family='' speed= \"100\" waitonscreen=\"2.0\" direction=\"-1\"/>";
		}
		
		content = content.replaceAll("var_dmgText", dmgText);

		FileManager.stringToFile(targetDir, content);

	}
}
