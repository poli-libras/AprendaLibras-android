package polilibras.com.br.aprendalibras;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Question Type
 * @author marcelo.koga
 */
public enum QuestionType {

    TEXT_OPTIONS, IMAGE_OPTIONS;

    // Unmodifiable list of the enum values.
    private static final List<QuestionType> VALUES = Collections.unmodifiableList(Arrays.asList(QuestionType.values()));

    public static List<QuestionType> getValues() {
        return VALUES;
    }

    public static QuestionType valueOf(int id) {
        return VALUES.get(id);
    }

    public int getId() {
        return this.ordinal();
    }
}
