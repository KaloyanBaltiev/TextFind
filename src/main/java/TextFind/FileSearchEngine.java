package TextFind;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class FileSearchEngine {
    public static void main(String[] args) {

        // get user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to a directory and text to search:");
        String input = scanner.nextLine();

        // parse input
        FileSearch fileSearch = parseInput(input);

        // search the path for files matching text inside;
        fileSearch.findAllFiles();
        fileSearch.searchFilesForText();

        // get all matching files
        List<File> files = fileSearch.getFilesMatched();

        // sort by size
        files.sort(Comparator.comparing(File::length));

        // print
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    private static FileSearch parseInput(String input) {

        // split path and text to search
        String[] splitInput = input.split("\"");
        String path = "";
        String text = "";

        if (splitInput.length < 2) {
            return new FileSearch(path, text);
        } else {
            path = splitInput[0].trim();
            text = splitInput[1].trim();
            return new FileSearch(path, text);
        }
    }
}
