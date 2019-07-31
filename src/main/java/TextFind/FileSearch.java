package TextFind;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FileSearch {

    private static final String DEST_DIR_TO_UNZIP = "D:\\unZip\\";

    private String path;
    private String text;
    private List<File> filesFound;
    private List<File> filesMatched;
    private List<File> filesUnZipped;

    FileSearch(String path, String text) {
        this.path = path;
        this.text = text;
        this.filesFound = new ArrayList<>();
        this.filesMatched = new ArrayList<>();
        this.filesUnZipped = new ArrayList<>();
    }

    private static String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf(".") + 1);
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

    // find all files in the directory
    void findAllFiles() {
        FileExplorer files = new FileExplorer();
        try {
            files.listFiles(path);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        filesFound.addAll(files.getFilesList());
    }

    List<File> getFilesMatched() {
        return filesMatched;
    }

    // find all files in
    private void findAllFiles(String unZipPath) {
        FileExplorer files = new FileExplorer();
        try {
            files.listFiles(unZipPath);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        filesUnZipped.addAll(files.getFilesList());
    }

    // search for text in files
    void searchFilesForText() {

        for (File file : filesFound) {

            String extension = getFileExtension(file);
            if (extension.equals("txt")) {
                scanTxtFile(file);
            } else if (extension.equals("zip")) {
                scanZipFile(file);
            }
        }
    }

    // scan zipped files
    private void scanZipFile(File file) {
        String filePath = file.getAbsolutePath();
        UnZipper.unzip(filePath, DEST_DIR_TO_UNZIP);
        findAllFiles(DEST_DIR_TO_UNZIP);
        for (File unzipFile : filesUnZipped) {
            scanTxtFile(unzipFile);
        }
    }

    private void scanTxtFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String lineFromFile = scanner.nextLine();
                if (lineFromFile.contains(text)) {
                    // a match!
                    filesMatched.add(file);
                    scanner.close();
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }

    void unload() {
        for(File file : filesUnZipped)
            file.delete();
    }
}
