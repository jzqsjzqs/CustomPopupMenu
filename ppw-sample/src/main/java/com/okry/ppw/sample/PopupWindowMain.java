package com.okry.ppw.sample;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.okry.ppw.PointerPopupWindow;

/**
 * Created by mr on 14-8-5.
 */
public class PopupWindowMain extends Activity implements Button.OnClickListener{

    Button b1;
    Button b2;
    Button b3;
    Button b4;
    Button b5;
    Button b6;
    Button b7;

    LinearLayout defaultAlignContainer;
    LinearLayout centerAlignContainer;
    LinearLayout autoAlignContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
        setViewAndClick();
    }

    private void setViewAndClick() {
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);

        defaultAlignContainer = (LinearLayout) findViewById(R.id.default_align_container);
        for(int i = 0; i < defaultAlignContainer.getChildCount(); i++) {
            Button b = (Button) defaultAlignContainer.getChildAt(i);
            b.setOnClickListener(this);
        }

        centerAlignContainer = (LinearLayout) findViewById(R.id.center_align_container);
        for(int i = 0; i < centerAlignContainer.getChildCount(); i++) {
            Button b = (Button) centerAlignContainer.getChildAt(i);
            b.setOnClickListener(this);
        }

        autoAlignContainer = (LinearLayout) findViewById(R.id.auto_align_container);
        for(int i = 0; i < autoAlignContainer.getChildCount(); i++) {
            Button b = (Button) autoAlignContainer.getChildAt(i);
            b.setOnClickListener(this);
        }
    }

    /**
     * create a PointerPopupWindow as the original PopupWindow way
     * @return
     */
    private PointerPopupWindow create() {

        //warning: you must specify the window width explicitly(do not use WRAP_CONTENT or MATCH_PARENT)
        //PointerPopupWindow p = new PointerPopupWindow(this, getResources().getDimensionPixelSize(R.dimen.popup_width));
        PointerPopupWindow p = new PointerPopupWindow(this, getResources().getDimensionPixelSize(R.dimen.popup_width));

        //添加自定义的popup layout
        final LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.popup_content_layout, null);
        TextView leftmenu = (TextView) view.findViewById(R.id.play_online);
        leftmenu.setOnClickListener(this);
        TextView rightmenu = (TextView) view.findViewById(R.id.download_immediately);
        rightmenu.setOnClickListener(this);

        p.setContentView(view);
        p.setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));  //设置透明背景
        p.setPointerImageRes(R.drawable.ic_popup_pointer);
        return p;
    }

    @Override
    public void onClick(View v) {
        if(v == b1) {
            // simply use showAsPointer
            create().showAsPointer(v);
        } else if(v == b2) {
            // change pointer image
            PointerPopupWindow p = create();
            p.setPointerImageRes(R.drawable.ic_arrow);
            p.showAsPointer(v);
        } else if(v == b3) {
            // set offset directly
            PointerPopupWindow p = create();
            p.showAsPointer(v, 10);
        } else if(v == b4) {
            // set offset directly
            PointerPopupWindow p = create();
            p.showAsPointer(v, 20, 20);
        } else if(v == b5) {
            // set offset directly
            PointerPopupWindow p = create();
            p.showAsPointer(v, -20, -20);
        } else if(v == b6) {
            // set screen padding
            PointerPopupWindow p = create();
            p.setPointerImageRes(R.drawable.ic_arrow);
            p.setMarginScreen(20);
            p.showAsPointer(v);
        } else if(v == b7) {
            // set margin screen
            PointerPopupWindow p = create();
            p.setPointerImageRes(R.drawable.ic_arrow);
            p.setMarginScreen(50);
            p.showAsPointer(v);
        } else if(v.getParent() == defaultAlignContainer) {
            // set align mode
            PointerPopupWindow p = create();
            p.setAlignMode(PointerPopupWindow.AlignMode.DEFAULT);//which is default
            p.showAsPointer(v);
        } else if(v.getParent() == centerAlignContainer) {
            // set align mode
            PointerPopupWindow p = create();
            p.setAlignMode(PointerPopupWindow.AlignMode.CENTER_FIX);
            //levin：popupwindow y轴方向偏移量，其值大致等于所依附的view的高度
            p.showAsPointer(v, getResources().getDimensionPixelSize(R.dimen.popupwindow_yoffset));
        } else if(v.getParent() == autoAlignContainer) {
            // set align mode
            PointerPopupWindow p = create();
            p.setAlignMode(PointerPopupWindow.AlignMode.AUTO_OFFSET);
            p.showAsPointer(v);
        } else if( v.getId() == R.id.play_online ) {
            Toast.makeText(this, "Ha Ha, I can Player at Online!", Toast.LENGTH_SHORT).show();
        } else if( v.getId() == R.id.download_immediately ) {
            Toast.makeText(this, "Oh! Download immediately!", Toast.LENGTH_SHORT).show();
        }
    }

}
