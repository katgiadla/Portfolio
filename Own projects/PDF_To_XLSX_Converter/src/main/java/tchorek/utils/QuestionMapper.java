package tchorek.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;

@NoArgsConstructor
@AllArgsConstructor
public class QuestionMapper {
    private final LinkedMultiValueMap<String, String> formatedQuestions;
    private final TextClassificator textClassificator;
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
            System.out.println(textFragment);
            switch (textClassificator.classificatePdfContent(textFragment)) {
                case QUESTION:
                    if (key.length() <= 2) {
                        key = textContainer.toString();
                        clearStringBuilderAddAnotherTextFragment(textFragment);
                    } else addTextToCollection(key, textContainer.toString(), textFragment);
                    break;
                case A:
                    key = textContainer.toString();
                    clearStringBuilderAddAnotherTextFragment(textFragment);
                    break;
                case B:
                    addTextToCollection(key, textContainer.toString(), textFragment);
                    break;
                case C:
                    addTextToCollection(key, textContainer.toString(), textFragment);
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