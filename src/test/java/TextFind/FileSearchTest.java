package TextFind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearchTest {

    @Test
    void test_1() {

        // GIVEN
        String path = "D:\\Kala\\";
        String word ="Kala";

        File file1 = new File("D:\\unZip\\Baz.txt");
        File file2 = new File("D:\\Kala\\New folder\\New folder\\New Bitmap Image.bmp");
        File file3 = new File("D:\\Kala\\New folder\\New folder\\New Text Document.txt");
        File file4 = new File("D:\\Kala\\New folder\\New Text Document (2).txt");
        File file5 = new File("D:\\Kala\\New folder\\New Text Document.txt");

        List<File> expectedList = new ArrayList<>();
        expectedList.addAll(List.of(file1, file2, file3, file4, file5));

        // WHEN
        FileSearch search = new FileSearch(path, word);
        search.findAllFiles();
        search.searchFilesForText();
        List <File> actual = search.getFilesMatched();

        // THEN
        Assertions.assertEquals(expectedList, actual);

    }
}
