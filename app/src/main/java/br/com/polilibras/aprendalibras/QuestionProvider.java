package br.com.polilibras.aprendalibras;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.com.polilibras.aprendalibras.db.DatabaseContract.QuestionTable;
import br.com.polilibras.aprendalibras.db.DatabaseHelper;

/**
 * Created by marce on 15/02/2016.
 */
public class QuestionProvider {

    private static QuestionProvider instance;

    private Context mCtx;
    private DatabaseHelper mDbHelper;

    private Cursor mCursor;

    private List<Integer> mQuestionsOrder;
    private int mQuestionIdx;

    // Define a projection that specifies which columns from the database
    // you will actually use after this query.
    private static final String[] projection = {
            QuestionTable._ID,
            QuestionTable.COLUMN_NAME_TYPE,
            QuestionTable.COLUMN_NAME_QUESTION_TXT,
            QuestionTable.COLUMN_NAME_QUESTION_RES,
            QuestionTable.COLUMN_NAME_OPTION0,
            QuestionTable.COLUMN_NAME_OPTION1,
            QuestionTable.COLUMN_NAME_OPTION2,
            QuestionTable.COLUMN_NAME_OPTION3
    };

    private QuestionProvider(Context context) {
        mCtx = context;
        mDbHelper = new DatabaseHelper(context);
        mQuestionsOrder = new ArrayList<>();
    }

    public static QuestionProvider getInstance(Context context) {
        if (instance == null) {
            instance = new QuestionProvider(context);
        }
        return instance;
    }


    public Question getNextQuestion() {

        if (mCursor == null) {
            mCursor = selectAllQuestions();
        }

        mCursor.moveToPosition(mQuestionsOrder.get(mQuestionIdx));

        if (!isLastQuestion()){
            mQuestionIdx++;
        } else {
            mQuestionIdx = 0;
        }

        Question q = new Question();
        q.setId(mCursor.getLong(mCursor.getColumnIndex(QuestionTable._ID)));
        q.setType(QuestionType.valueOf(mCursor.getInt(mCursor.getColumnIndexOrThrow(QuestionTable.COLUMN_NAME_TYPE))));
        q.setQuestionText(mCursor.getString(mCursor.getColumnIndexOrThrow(QuestionTable.COLUMN_NAME_QUESTION_TXT)));
        q.setQuestionRes(mCursor.getString(mCursor.getColumnIndexOrThrow(QuestionTable.COLUMN_NAME_QUESTION_RES)));
        String option0 = mCursor.getString(mCursor.getColumnIndexOrThrow(QuestionTable.COLUMN_NAME_OPTION0));
        String option1 = mCursor.getString(mCursor.getColumnIndexOrThrow(QuestionTable.COLUMN_NAME_OPTION1));
        String option2 = mCursor.getString(mCursor.getColumnIndexOrThrow(QuestionTable.COLUMN_NAME_OPTION2));
        String option3 = mCursor.getString(mCursor.getColumnIndexOrThrow(QuestionTable.COLUMN_NAME_OPTION3));
        q.setOptions(Arrays.asList(option0, option1, option2, option3));

        return q;
    }

    public boolean isLastQuestion() {
        return (mQuestionIdx >= mQuestionsOrder.size() - 1);
    }

    private Cursor selectAllQuestions() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor c = db.query(
                QuestionTable.TABLE_NAME,  // The table to query
                projection,                // The columns to return
                null,                      // The columns for the WHERE clause
                null,                      // The values for the WHERE clause
                null,                      // don't group the rows
                null,                      // don't filter by row groups
                QuestionTable._ID + " ASC" // The sort order
        );

        // Cria ordem aleatória para as questões
        mQuestionsOrder.clear();
        mQuestionIdx = 0;
        for (int i = 0; i < c.getCount(); i++) {
            mQuestionsOrder.add(i);
        }
        Collections.shuffle(mQuestionsOrder);

        return c;
    }
}
