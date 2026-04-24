package application.util;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

public class FileManager {
	public static String fileToString(String dir) {
		Path p = Paths.get(dir);
		String s = new String("");
		try {
			s = Files.readString(p);

		} catch (IOException e) {
		}
		return s;
	}

	public static void copyFile(String sourceDir, String targetDir) {
		Path sourcePath = Paths.get(sourceDir);
		Path targetPath = Paths.get(targetDir);

		try {
			Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
		}
	}

	public static void stringToFile(String dir, String content) {
		try {
			Files.writeString(Paths.get(dir), content);
		} catch (IOException e) {
		}
	}

	public static void deleteDirectory(String dir){
		Path deletePath = Paths.get(dir);
		try {
		if (Files.exists(deletePath)) {
			Files.walk(deletePath).sorted(Comparator.reverseOrder()) // 하위부터 삭제
					.forEach(p -> {
						try {
							Files.delete(p);
						} catch (IOException e) {
						}
					});
		}
		}catch (IOException e) {
		}
	}

	public static void copyFolder(String sourceDir, String targetDir) {
		// 디폴트 UI 인스톨
		Path sourceFolder = Paths.get(sourceDir);
		Path targetFolder = Paths.get(targetDir);

		try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(sourceFolder)) {
			for (Path file : directoryStream) {
				Path targetFile = targetFolder.resolve(file.getFileName());
				if (Files.isDirectory(file)) {
					Files.walk(file).forEach(sourcePath -> {
						try {
							Path destinationPath = targetFolder.resolve(sourceFolder.relativize(sourcePath));
							if (Files.isDirectory(sourcePath)) {
								Files.createDirectories(destinationPath);
							} else {
								Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
							}
						} catch (IOException ex) {
						}
					});
				} else {
					Files.copy(file, targetFile, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		} catch (IOException e) {
		}
	}
}
