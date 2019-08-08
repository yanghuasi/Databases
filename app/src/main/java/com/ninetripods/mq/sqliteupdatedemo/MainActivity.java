package com.ninetripods.mq.sqliteupdatedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ninetripods.mq.sqliteupdatedemo.sqlitedemo.Student;
import com.ninetripods.mq.sqliteupdatedemo.sqlite.DBDao;


public class MainActivity extends AppCompatActivity {

    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        student = new Student("小明", 20, "三年二班", "man","100");
        DBDao.getInstance().insert(student);
//        Log.e("TTT", "db size is " + DBDao、.getInstance().query().size());
    }
}
