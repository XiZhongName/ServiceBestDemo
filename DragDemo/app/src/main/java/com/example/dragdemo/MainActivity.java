package com.example.dragdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    LinearLayout container;
    RelativeLayout topContainer;
    TextView title;
    static final String IMAGE_TAG = "已经拖到目标区域了";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.img);
        imageView.setTag(IMAGE_TAG);//为图片设置标签
        container = findViewById(R.id.container_);
        topContainer = findViewById(R.id.topContainer);
        title = findViewById(R.id.title_);

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item((String)v.getTag());//把图片标签放进剪切板
                ClipData data = new ClipData(IMAGE_TAG,new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},item);//给剪切板里放进内容
                v.startDragAndDrop(data,new View.DragShadowBuilder(v),null,0);//拖动效果
                return true;
            }
        });
        //设置把图片拖到topContainer上时的监听事件
        topContainer.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (action){
                    case DragEvent.ACTION_DRAG_STARTED:
                        return true;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED:
                        return true;
                    case DragEvent.ACTION_DROP://手指抬起时把图片设置在这个地方
                        imageView.setX(event.getX() - imageView.getWidth()/2);
                        imageView.setY(event.getY() - imageView.getHeight()/2);
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;
                }
                return false;
            }
        });
        container.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                int action = event.getAction();
                switch (action){
                    case DragEvent.ACTION_DRAG_STARTED://判断开始拖动时，如果剪切板里的内容是这个才能执行后面的内容
                        if (event.getClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)){
                            return true;
                        }
                        return false;
                    case DragEvent.ACTION_DRAG_ENTERED://图片拖动到这个容器设置背景颜色为黄色
                        container.setBackgroundColor(Color.YELLOW);
                        return true;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;
                    case DragEvent.ACTION_DRAG_EXITED://移出这个容器时颜色设置为蓝色，并把文字设置为空
                        container.setBackgroundColor(Color.BLUE);
                        title.setText("");
                        return true;
                    case DragEvent.ACTION_DROP://手指抬起时显示文字和坐标
                        ClipData.Item item = event.getClipData().getItemAt(0);
                        String dragData = item.getText().toString();
                        title.setText(dragData + "\n" + event.getX() + ":" + event.getY());
                        return true;
                    case DragEvent.ACTION_DRAG_ENDED:
                        return true;
                }
                return false;
            }
        });
    }
}























