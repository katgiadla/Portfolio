package tchorek.utils;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileNameReader {
    private BufferedReader reader;
    String fileContent;

    public FileNameReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readFilename() throws IOException {
        fileContent = reader.readLine();
        return fileContent;
    }

    @SneakyThrows
    public void closeStream(){
        reader.close();
    }
}
