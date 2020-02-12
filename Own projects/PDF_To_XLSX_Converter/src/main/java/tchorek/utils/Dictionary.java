package tchorek.utils;

import lombok.Getter;

@Getter
public class Dictionary {
    private static String pathResourcesFolder = "C:\\AGH\\Portfolio\\pdfreader\\src\\main\\resources\\";
    private static String sourceFileName = "PytaniaABC";
    private static String targetFileName = "Excel";
    private String currentFileName = "";

    public static String getPath() {
        return pathResourcesFolder;
    }

    public static String getSourceFileName() {
        return sourceFileName;
    }

    public static String getTargetFileName() {
        return targetFileName;
    }

    public String getCurrentFileName() {
        return currentFileName;
    }

    public void setCurrentFileName(String currentFileName) {
        this.currentFileName = currentFileName;
    }

    public enum Questions {
        A("^.{0,3}a\\)"),
        B("^.{0,3}b\\)"),
        C("^.{0,3}c\\)"),
        QUESTION("^[0-9]{1,3}\\.[^0-9]{1}"),
        TEXT("text");

        String text;

        Questions(String input) {
            text = input;
        }

        public String getText() {
            return text;
        }
    }
}
