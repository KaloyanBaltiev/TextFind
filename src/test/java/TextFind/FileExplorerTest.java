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
        File file1 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\Bar.txt");
        File file2 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\BarZip.zip");
        File file3 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\BazFoo.txt");
        File file4 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\BazFooZip.zip");
        File file5 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\Foo.txt");
        File file6 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\FooZip.zip");
        File file7 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\New folder\\Bar1.txt");
        File file8 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\New folder\\BazFoo1.txt");
        File file9 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\New folder\\Foo1.txt");


        List<File> expectedList = List.of(file1, file2, file3, file4, file5, file6, file7, file8, file9);

        // WHEN
        FileExplorer explorer = new FileExplorer();
        explorer.listFiles("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\");

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
