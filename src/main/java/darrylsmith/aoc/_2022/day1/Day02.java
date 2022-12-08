package darrylsmith.aoc._2022.day1;

import darrylsmith.aoc.io.FileDataReader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Day02 {

    enum RPS {
        X(1, 3, 0, 6), // ROCK
        Y(2, 6, 3, 0), // PAPER
        Z(3, 0, 6, 3); // SCISSORS

        private final int value;
        private final int rock;
        private final int paper;
        private final int scissors;

        RPS(int value, int rock, int paper, int scissors) {
            this.value = value;
            this.rock = rock;
            this.paper = paper;
            this.scissors = scissors;
        }

    }

//    X lose, Y draw, Z win

    enum XRPS {
        A(3, 4, 8), // ROCK
        B(1, 5, 9), // PAPER
        C(2, 6, 7); // SCISSORS

        private final int lose;
        private final int draw;
        private final int win;

        XRPS(int lose, int draw, int win) {
            this.lose = lose;
            this.draw = draw;
            this.win = win;
        }

    }

    public static void main(String[] args) {
        try {
            Day02 day02 = new Day02();
            Supplier<Stream<List<String>>> streamSupplier = day02.retrieveData("/day2.txt");

            System.out.println("day02.part1 = " + day02.part1(streamSupplier.get()));
            System.out.println("day02.part2 = " + day02.part2(streamSupplier.get()));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Integer calculatePointsPart1(List<String> plays) {
        RPS rps = RPS.valueOf(plays.get(1));
        return switch (plays.get(0)) {
            case ("A") -> rps.rock + rps.value;
            case ("B") -> rps.paper + rps.value;
            case ("C") -> rps.scissors + rps.value;
            default -> 0;
        };
    }

    private Integer calculatePointsPart2(List<String> plays) {
        XRPS xrps = XRPS.valueOf(plays.get(0));
        return switch (plays.get(1)) {
            case ("X") -> xrps.lose;
            case ("Y") -> xrps.draw;
            case ("Z") -> xrps.win;
            default -> 0;
        };
    }

    public int part1(Stream<List<String>> processedData) {
        return processedData.map(this::calculatePointsPart1).mapToInt(Integer::intValue).sum();
    }

    private int part2(Stream<List<String>> processedData) {
        return processedData.map(this::calculatePointsPart2).mapToInt(Integer::intValue).sum();
    }

    private Supplier<Stream<List<String>>> retrieveData(String filename) throws IOException, URISyntaxException {
        String rawData = FileDataReader.readFileInput(filename);
        return () ->
                FileDataReader.splitIntoPairs(rawData);
    }

}
