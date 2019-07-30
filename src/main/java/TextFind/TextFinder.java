package TextFind;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class TextFinder {
    static final String DEST_DIR_TO_UNZIP = "D:\\unZip\\";

    private String path;
    private String text;
    private List<File> filesFound;
    private List<File> filesMatched;
    private List<File> filesUnZiped;

    public TextFinder(String path, String text) {
        this.path = path;
        this.text = text;
        this.filesFound = new ArrayList<>();
        this.filesMatched = new ArrayList<>();
        this.filesUnZiped = new ArrayList<>();
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

    void findAllFiles(String unZipPath) {
        FileExplorer files = new FileExplorer();
        try {
            files.listFiles(unZipPath);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        filesUnZiped.addAll(files.getFilesList());
    }

    // open file and search for text
    void searchFilesForText() {

        for (File file : filesFound) {

            String extension = getFileExtension(file);
            if (extension.equals(".txt")){
                scan(file);
            } else if(extension.equals(".zip")){
                String filePath = file.getAbsolutePath();
                unzip(filePath, DEST_DIR_TO_UNZIP);
                findAllFiles(DEST_DIR_TO_UNZIP);
                for (File unzipFile : filesUnZiped){
                    scan(unzipFile);
                }
            }
        }
    }

    private static void unzip(String zipFilePath, String destDir) {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                //create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                //close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            //close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void scan(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String lineFromFile = scanner.nextLine();
                if (lineFromFile.contains(text)) {
                    // a match!
                    filesMatched.add(file);
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
    }

    private static String getFileExtension(File file) {
        String extension = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                extension = name.substring(name.lastIndexOf("."));
            }
        } catch (Exception e) {
            extension = "";
        }

        return extension;

    }

    public List<File> getFilesMatched() {
        return filesMatched;
    }
}
