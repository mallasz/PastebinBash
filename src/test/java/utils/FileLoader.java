package utils;

import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class FileLoader {

    private static ClassLoader getResourceLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static InputStream loadResourceAsStream(String path) {
        return getResourceLoader().getResourceAsStream(path);
    }

    public static String loadResourceAsString(String path) {
        try (InputStream stream = loadResourceAsStream(path)) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            Assertions.fail("Exception during resource file loading");
            return null;
        }
    }
}
