package converter.utils;

public class Dictionary {
    //    @Getter
    private static String pathResourcesFolder = "C:\\AGH\\Portfolio\\pdfreader\\src\\main\\resources\\";

    public static String getPathResourcesFolder() {
        return pathResourcesFolder;
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

    public enum Index {
        ZERO(0);

        Integer number;

        Index(Integer index) {
            number = index;
        }

        public Integer getNumber() {
            return number;
        }
    }
}
