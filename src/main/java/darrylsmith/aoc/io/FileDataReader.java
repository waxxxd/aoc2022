package darrylsmith.aoc.io;

import darrylsmith.aoc._2022.day1.Day01;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FileDataReader {

    public static final String NEWLINE = "\r\n";
    public static final String BLANKLINE = "\r\n\r\n";

    public static String readFileInput(String filename) throws IOException, URISyntaxException {
        URI uri = Objects.requireNonNull(Day01.class.getResource(filename)).toURI();
        return Files.readString(Paths.get(uri), Charset.defaultCharset());
    }

    public static Stream<IntStream> groupByNewlineSeparator(String inputData) {
        return Arrays.stream(inputData.split(BLANKLINE)).
                map(i -> Arrays.stream(i.split(NEWLINE)).
                        mapToInt(Integer::parseInt));
    }

    public static Stream<List<String>> splitIntoPairs(String inputData) {
        return Arrays
                .stream(inputData.split(NEWLINE))
                .map(i -> Arrays.stream(i.split(" ")).toList());
    }
}
