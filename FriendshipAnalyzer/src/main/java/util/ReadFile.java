package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReadFile {
    public static String readFileContent(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        FileReader fr;
        try {
            fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready()) {
                stringBuilder.append(br.readLine());
                stringBuilder.append("\n");
            }
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString();
    }

    public static List<String> getAllPeople(String script) {
        String[] split = script.split("\n");
        return Arrays.stream(split).map(s -> s.substring(0, s.indexOf(":"))).collect(Collectors.toList());
    }
}
