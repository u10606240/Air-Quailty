package com.ttu.airquality;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JsonAdapter extends ArrayAdapter<Air> {
//public class JsonAdapter extends BaseAdapter{

    private List<Air> list;

    private Context context;

    private LayoutInflater inflater;


    /*public JsonAdapter(Context context,List<Air> list){
        this.list=list;
        this.context=context;
        inflater=LayoutInflater.from(context);
    }*/

    public JsonAdapter(Context context, ArrayList<Air> list){
        super(context, 0, list);
    }

    @Override
    //改寫getView()方法
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        //listItemView可能會是空的，例如App剛啟動時，沒有預先儲存的view可使用
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        //找到data，並在View上設定正確的data
        Air currentName = getItem(position);

        //找到ListItem.xml中的兩個TextView(物種學名和中文名)
        TextView tvSiteName = listItemView.findViewById(R.id.tvSiteName);
        tvSiteName.setText(currentName.getSiteName());

        TextView tvCounty = listItemView.findViewById(R.id.tvCounty);
        tvCounty.setText(currentName.getCounty());

        TextView tvLongitude = listItemView.findViewById(R.id.tvLongitude);
        tvLongitude.setText(currentName.getLongitude());

        TextView tvLatitude = listItemView.findViewById(R.id.tvLatitude);
        tvLatitude.setText(currentName.getLatitude());

        TextView tvAQI = listItemView.findViewById(R.id.tvAQI);
        tvAQI.setText(currentName.getAQI());

        TextView tvPM2_5 = listItemView.findViewById(R.id.tvPM2_5);
        tvPM2_5.setText(currentName.getPM2_5());

        TextView tvStatus = listItemView.findViewById(R.id.tvStatus);
        tvStatus.setText(currentName.getStatus());

        TextView tvPublishTime = listItemView.findViewById(R.id.tvPublishTime);
        tvPublishTime.setText(currentName.getPublishTime());

        TextView tvTips = listItemView.findViewById(R.id.tvTips);
        tvTips.setText(currentName.getTips());

        return listItemView;
    }
}