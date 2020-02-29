package converter.utils;

import org.springframework.util.LinkedMultiValueMap;

public class QuestionMapper {
    private LinkedMultiValueMap<String, String> formatedQuestions;
    private TextClassificator textClassificator;
    private StringBuilder textContainer;

    public QuestionMapper() {
        formatedQuestions = new LinkedMultiValueMap<>();
        textClassificator = new TextClassificator();
        textContainer = new StringBuilder();
    }

    public LinkedMultiValueMap<String, String> extractQuestions(String text) {
        formatedQuestions.clear();
        String[] pdfText = text.split("\n");
        pdfText[0] = pdfText[1] = "";
        String key = "";

        for (String textFragment : pdfText) {
            if (textFragment.isEmpty() || textFragment.length() <= 2) continue;
            switch (textClassificator.classificatePdfContent(textFragment)) {
                case QUESTION:
                    if (key.length() <= 2) {
                        key = textContainer.toString();
                        clearStringBuilderAddAnotherTextFragment(textFragment);
                        break;
                    }
                case B:
                case C:
                    addTextToCollection(key, textContainer.toString(), textFragment);
                    break;
                case A:
                    key = textContainer.toString();
                    clearStringBuilderAddAnotherTextFragment(textFragment);
                    break;
                default:
                    textContainer.append(textFragment);
            }
        }
        formatedQuestions.add(key, textContainer.toString());
        return formatedQuestions;
    }

    private void addTextToCollection(String question, String answers, String textFragment) {
        formatedQuestions.add(question, answers);
        clearStringBuilderAddAnotherTextFragment(textFragment);
    }

    private void clearStringBuilderAddAnotherTextFragment(String answerFragment) {
        textContainer.delete(0, textContainer.length());
        textContainer.append(answerFragment);
    }
}
