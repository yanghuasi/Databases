package com.ninetripods.mq.sqliteupdatedemo.greendaodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.ninetripods.mq.sqliteupdatedemo.R;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Bean> beanList = new ArrayList<>();
    private Adapter Adapter;
    private User mUser;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);
        //初始化
        DBManager.init(this);
        listView = findViewById(R.id.listView);
        Adapter = new Adapter(beanList);
        listView.setAdapter(Adapter);

    }

    public void btnAdd(View view) {
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        user.setSex("女");
        DBManager.getmInstance().getSession().getUserDao().insert(user);
    }

    public void btnDelete(View view) {
        //删除全部
        DBManager.getmInstance().getSession().getUserDao().deleteAll();
        //DBManager.getmInstance().getSession().getUserDao().delete(user);//根据一个user对象删除

    }

    public void btnQuery(View view) {
        List<User> users = DBManager.getmInstance().getSession().getUserDao().loadAll();
        beanList.clear();
        if (users.size()>0){
            mUser=users.get(0);
        }

        for (int position = 0; position < users.size(); position++) {
            beanList.add(new Bean(users.get(position).getName(), users.get(position).getSex(), users.get(position).getAge()));
        }
        Adapter.notifyDataSetChanged();
    }

    public void btnAlter(View view) {
        //根据一个实体来修改年龄
        mUser.setAge(66);
        DBManager.getmInstance().getSession().getUserDao().update(mUser);
    }

    private class Adapter extends BaseAdapter {

        List<Bean> beanList;

        public Adapter(List<Bean> beanList) {
            this.beanList = beanList;
        }

        @Override
        public int getCount() {
            return beanList.size();
        }

        @Override
        public Object getItem(int i) {
            return beanList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            viewHoler viewHoler = null;
            if (view == null) {
                viewHoler = new viewHoler();
                view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_list, null);
                viewHoler.name = view.findViewById(R.id.name);
                viewHoler.sex = view.findViewById(R.id.sex);
                viewHoler.age = view.findViewById(R.id.age);
                view.setTag(viewHoler);
            } else {
                viewHoler = (viewHoler) view.getTag();
            }

            viewHoler.name.setText(beanList.get(position).getName());
            viewHoler.sex.setText(beanList.get(position).getSex());
            viewHoler.age.setText(beanList.get(position).getAge() + "");

            return view;
        }

        class viewHoler {
            private TextView name, sex, age;
        }
    }

    private class Bean {


        private String name;
        private int age;

        public String getSex() {
            return sex;
        }

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }

        private String sex;

        public Bean(String name, String sex, int age) {
            this.name = name;
            this.sex = sex;
            this.age = age;
        }


    }
}
