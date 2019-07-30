package TextFind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileExplorerTest {

    @Test
    void test_1() {

        // GIVEN
        File file1 = new File("D:\\Kala\\Bala.txt");
        File file2 = new File("D:\\Kala\\Kala.txt");
        File file3 = new File("D:\\Kala\\New folder\\k.txt");
        File file4 = new File("D:\\Kala\\New folder\\New Text Document.txt");

        List<File> expectedList = List.of(file1, file2, file3, file4);

        // WHEN
        FileExplorer explorer = new FileExplorer();
        explorer.listFiles("/D:/Kala/");

        // THEN
        Assertions.assertEquals(expectedList, explorer.getFilesList());
    }

    @Test
    void test_2() {

        // GIVEN
        FileExplorer explorer = new FileExplorer();


        try {
            // WHEN
            explorer.listFiles("/D:/New folder2/");
            Assertions.fail("Method call did not throw an exception.");
        } catch (IllegalArgumentException e) {

            // THEN
            assertEquals("No Folder Or File", e.getMessage());
        }
    }
}
