package karan.societe.hiring.apparel.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class ConsoleInputReader {
    private Scanner scanner;

    public ConsoleInputReader() {
    }

    @PostConstruct
    public void init() {
        scanner = new Scanner(System.in);
    }

    public int readInt() {
        return Integer.parseInt(scanner.nextLine());
    }

    public <T> List<T> getLines(int n, Function<String, T> lineParser) {
        List<T> lines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lines.add(lineParser.apply(readLine()));
        }

        return lines;
    }

    public String readLine() {
        return scanner.nextLine();
    }
}
