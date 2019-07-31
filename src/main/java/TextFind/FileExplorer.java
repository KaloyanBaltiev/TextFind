package TextFind;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class FileExplorer {

    private List<File> filesList;

    FileExplorer() {
        this.filesList = new ArrayList<>();
    }

    // recursive call to find all files in a file path
    void listFiles(String path) throws IllegalArgumentException {

        File folder = new File(path);

        File[] files = folder.listFiles();

        if (files == null) {
            throw new IllegalArgumentException("No Folder Or File");
        }

        for (File file : files) {
            if (file.isFile()) {
                filesList.add(file);
            } else if (file.isDirectory()) {
                listFiles(file.getAbsolutePath()); // recursive call
            }
        }
    }

    List<File> getFilesList() {
        return filesList;
    }
}
