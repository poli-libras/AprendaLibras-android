package polilibras.com.br.aprendalibras.db;

import android.provider.BaseColumns;

/**
 * Created by marce on 16/02/2016.
 */
public final class DatabaseContract {

    public DatabaseContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class QuestionTable implements BaseColumns {
        public static final String TABLE_NAME = "question";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_QUESTION_TXT = "question_txt";
        public static final String COLUMN_NAME_QUESTION_RES = "question_res";
        public static final String COLUMN_NAME_OPTION0 = "option0";
        public static final String COLUMN_NAME_OPTION1 = "option1";
        public static final String COLUMN_NAME_OPTION2 = "option2";
        public static final String COLUMN_NAME_OPTION3 = "option3";
        public static final String COLUMN_NAME_ANSWER = "answer";
    }
}
