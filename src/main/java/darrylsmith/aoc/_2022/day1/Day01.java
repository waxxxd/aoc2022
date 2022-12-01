package darrylsmith.aoc._2022.day1;

import darrylsmith.aoc.io.FileDataReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day01 {

    public static void main(String[] args) {
        try {
            Day01 day01 = new Day01();
            Supplier<Stream<Integer>> streamSupplier = day01.retrieveData("/day1.txt");

            System.out.println("day01.part1 = " + day01.part1(streamSupplier.get()));
            System.out.println("day01.part2 = " + day01.part2(streamSupplier.get()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer part1(Stream<Integer> processedData) {
        return processedData.max(Integer::compare).orElse(-1);
    }

    private int part2(Stream<Integer> processedData) {
        return processedData.sorted(Comparator.reverseOrder()).limit(3).mapToInt(Integer::intValue).sum();
    }

    private Supplier<Stream<Integer>> retrieveData(String filename) throws IOException, URISyntaxException {
        String rawData = FileDataReader.readFileInput(filename);
        return () ->
                FileDataReader.groupByNewlineSeparator(rawData)
                        .map(IntStream::sum);
    }

}
