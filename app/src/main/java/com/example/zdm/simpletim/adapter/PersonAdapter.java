package com.example.zdm.simpletim.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zdm.simpletim.R;
import com.example.zdm.simpletim.model.Person;

import java.util.List;

/**
 * User: ZDM
 * DateTime: 2019/10/14
 * Description: 联系人ListView列表适配器
 */
public class PersonAdapter extends BaseAdapter {

    private Context context;//为了实现跳转而写的

    private List<Person> list;//联系人列表
    private LayoutInflater inflater;

    public PersonAdapter(Context context, List<Person> list) {
        inflater = LayoutInflater.from(context);
        this.context=context;//为了实现跳转而写的
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder; //ViewHolder是自定义内部内类
        if(null==convertView){
            holder= new ViewHolder();
            convertView=inflater.inflate(R.layout.person_list_item,null);  //单个联系人
            holder.tv_name= (TextView) convertView.findViewById(R.id.tv_name);  //姓名
            holder.tv_word= (TextView) convertView.findViewById(R.id.tv_word);  //名字字母
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        String word = list.get(position).getHeaderChar();//第一个字母
        holder.tv_word.setText(word);
        holder.tv_name.setText(list.get(position).getName());


        //跳转到聊天页面
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                //intent.setClass(context,ChatMainActivity.class);
                Bundle bundle = new Bundle();//传递过去的用户参数
                bundle.putString("name", (list.get(position).getName()));
                intent.putExtras(bundle);
                context.startActivity(intent);

                //
                //Intent intent=new Intent();
                //intent.setClass(context,ChatMainActivity.class);
                //context.startActivity(intent);
                ////提示当前点击的是哪个类
                //Snackbar.make(v, "position:"+position, Snackbar.LENGTH_LONG)  .setAction("Action", null).show();
            }
        });

        //将相同字母开头的合并在一起
        if(position==0){
            //第一个是一定显示的
            holder.tv_word.setVisibility(View.VISIBLE);
        }else {
            //后一个与前一个对比,判断首字母是否相同，相同则隐藏
            String headword=list.get(position-1).getHeaderChar();
            if(word.equals(headword)){
                holder.tv_word.setVisibility(View.GONE);
            }else {
                holder.tv_word.setVisibility(View.VISIBLE);
            }
        }

        return convertView;
    }

    // 自定义类
    private class ViewHolder {
        private TextView tv_word;
        private TextView tv_name;
    }
}
