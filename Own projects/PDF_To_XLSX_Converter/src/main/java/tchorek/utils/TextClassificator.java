package tchorek.utils;

import tchorek.utils.Dictionary.Questions;

import java.util.regex.Pattern;

public class TextClassificator {

    private Pattern questionPattern = Pattern.compile(Questions.QUESTION.getText());
    private Pattern answerAPattern = Pattern.compile(Questions.A.getText());
    private Pattern answerBPattern = Pattern.compile(Questions.B.getText());
    private Pattern answerCPattern = Pattern.compile(Questions.C.getText());

    public Questions classificatePdfContent(String text) {
        if (questionPattern.matcher(text).find()) return Questions.QUESTION;
        if (answerAPattern.matcher(text).find()) return Questions.A;
        if (answerBPattern.matcher(text).find()) return Questions.B;
        if (answerCPattern.matcher(text).find()) return Questions.C;
        return Questions.TEXT;
    }
}
