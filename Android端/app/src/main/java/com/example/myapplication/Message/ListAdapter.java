package com.example.myapplication.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    /* private Context mcontext;
     private LayoutInflater mlayoutInflater;
     public ListAdapter(Context context){
         this.mcontext = context;
         mlayoutInflater = LayoutInflater.from(context);
     }
     @Override
     public int getCount() {
         return 0;
     }

     @Override
     public Object getItem(int position) {
         return null;
     }

     @Override
     public long getItemId(int position) {
         return 0;
     }
     static class viewHolder{
         public TextView mtv1;
     }
     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
         if (convertView == null){

         }
         return null;*/
    private List<LeaveMessage> leaveMessage;
    private int resource;//绑定的条目界面
    private LayoutInflater inflater;//布局填充器
    private Context context;

    public ListAdapter(List<LeaveMessage> leaveMessages, /*int resource,*/Context context) {
        this.leaveMessage = leaveMessages;
//        this.resource= resource;
//        this.inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.inflater = LayoutInflater.from(context);
    }

  /*  public ListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }*/

    @Override

    public int getCount() {//获取数据数量
        return leaveMessage.size();
    }

    @Override
    public Object getItem(int position) {
        return leaveMessage.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.layout_list_item, null);
        TextView mtv1 = view.findViewById(R.id.litv_name);
        TextView mtv2 = view.findViewById(R.id.litv_message);
        TextView mtv3 = view.findViewById(R.id.litv_id);
        TextView mtv4 = view.findViewById(R.id.litv_remark);

//        if(convertView==null){
//            convertView=inflater.inflate(resource,null);//生成条目界面对象
//             }
//        TextView nameView= convertView.findViewById(R.id.litv_name);
//        TextView messageView= convertView.findViewById(R.id.litv_message);
//
//
//
        LeaveMessage leaveMessage1 = leaveMessage.get(position);

//数据绑定
        mtv1.setText(leaveMessage1.getName());
        mtv2.setText(leaveMessage1.getMessage());
        mtv3.setText(leaveMessage1.getId() + "");
        mtv4.setText(leaveMessage1.getRemark());
        return view;
    }
}
