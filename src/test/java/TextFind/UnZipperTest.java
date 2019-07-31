package TextFind;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class UnZipperTest {

    @Test
    void test_unzip_file_success() {
        // GIVEN
        String filePath ="C:\\Users\\kaloy\\IdeaProjects\\TextFind\\src\\test\\FileSystem\\BazFooZip.zip";
        String destination = "D:\\New folder\\";
        File expected = new File ("D:\\New folder\\BazFooZip.txt");

        // WHEN
        UnZipper.unzip(filePath, destination);

        // THEN
        Assertions.assertTrue(expected.exists());
    }

    @Test
    void test_does_not_open_empty_zip () {
        // GIVEN
        String filePath ="D:\\Kala\\Empty.zip";
        String destination = "D:\\New folder\\";
        File expected = new File ("D:\\New folder\\Empty.txt");

        // WHEN
        UnZipper.unzip(filePath, destination);

        // THEN
        Assertions.assertFalse(expected.exists());
    }

    @Test
    void test_does_not_open_ () {
        // GIVEN
        String filePath ="D:\\Kala\\DoesNotExists.zip";
        String destination = "D:\\New folder\\";
        File expected = new File ("D:\\New folder\\Empty.txt");

        // WHEN
        UnZipper.unzip(filePath, destination);

        // THEN
        Assertions.assertFalse(expected.exists());
    }

}
