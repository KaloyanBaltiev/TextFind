package TextFind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;

class FileSearchTest {

    @Test
    void test_1() {

        // GIVEN
        String path = "C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\";
        String word ="Foo";

        File file1 = new File("D:\\unZip\\BarZip\\FooInBar.txt");
        File file2 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\BazFoo.txt");
        File file3 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\Foo.txt");
        File file4 = new File("D:\\unZip\\FooZip.txt");
        File file5 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\New folder\\BazFoo1.txt");
        File file6 = new File("C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\New folder\\Foo1.txt");

        List<File> expectedList = List.of(file1, file2, file3, file4, file5, file6);

        // WHEN
        FileSearch search = new FileSearch(path, word);
        search.findAllFiles();
        search.searchFilesForText();
        List <File> actual = search.getFilesMatched();

        // THEN
        Assertions.assertEquals(expectedList, actual);
        search.unload();
    }
}
