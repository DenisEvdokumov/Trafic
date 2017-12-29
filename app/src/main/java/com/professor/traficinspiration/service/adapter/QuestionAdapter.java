package com.professor.traficinspiration.service.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.professor.traficinspiration.R;
import com.professor.traficinspiration.model.Order;
import com.professor.traficinspiration.model.Question;
import com.professor.traficinspiration.model.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {

    private Context context;
    private List<Question> values;


    public QuestionAdapter(@NonNull Context context, List<Question> values) {
        super(context, R.layout.question_list_item_pagination, values);

        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.question_list_item_pagination, parent, false);
        }

//        android.R.layout.simple_list_item_1 - встроенный элемент для отображения элемента списка
        TextView questionText = (TextView) row.findViewById(R.id.question_text);
        TextView answerText = (TextView) row.findViewById(R.id.answer_text);

        Question item = values.get(position);

        questionText.setText(item.getQuestion());
        answerText.setText(item.getAnswer());


        return row;
    }
}
