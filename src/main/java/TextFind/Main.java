package TextFind;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        // get user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter path to a directory and text to search:");
        String input = scanner.nextLine();

        // parse input
        TextFinder textFinder = parseInput(input);

        // search the path for files matching text inside;
        textFinder.findAllFiles();
        textFinder.searchFilesForText();

        // get all matching files
        List<File> files = textFinder.getFilesMatched();

        // sort by size
        files.sort(Comparator.comparing(File::getTotalSpace));

        // print
        for (File file : files) {
            System.out.println(file.getName());
        }
    }

    private static TextFinder parseInput(String input) {

        // split path and text to search
        String[] splitInput = input.split("\"");
        String path = "";
        String text = "";

        if (splitInput.length < 2) {
            return new TextFinder(path, text);
        } else {
            path = splitInput[0].trim();
            text = splitInput[1].trim();
            return new TextFinder(path, text);
        }
    }
}
