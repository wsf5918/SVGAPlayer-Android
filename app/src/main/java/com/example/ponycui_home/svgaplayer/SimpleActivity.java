package com.example.ponycui_home.svgaplayer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import com.opensource.svgaplayer.SVGACallback;
import com.opensource.svgaplayer.SVGADrawable;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import com.opensource.svgaplayer.SVGAVideoEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiminghui on 2017/3/30.
 * 将 svga 文件打包到 assets 文件夹中，然后使用 layout.xml 加载动画。
 */

public class SimpleActivity extends Activity implements View.OnClickListener {


    private SVGAImageView svgaImageView;
    private TextView tvName;

    Button btnNext;
    List<String> datas=new ArrayList<>();

    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        svgaImageView = (SVGAImageView) findViewById(R.id.svgaImageView);
        tvName = (TextView) findViewById(R.id.tvName);
        findViewById(R.id.btnNext).setOnClickListener(this);

        datas.add("750x80.svga");
        datas.add("alarm.svga");
        datas.add("EmptyState.svga");
        datas.add("halloween.svga");
        datas.add("HamburgerArrow.svga");
        datas.add("kingset.svga");
        datas.add("PinJump.svga");
        datas.add("posche.svga");
        datas.add("rose.svga");
        datas.add("rose_1.5.0.svga");
        datas.add("rose_2.0.0.svga");
        datas.add("TwitterHeart.svga");
        datas.add("Walkthrough.svga");

        btnNext=(Button)findViewById(R.id.btnNext);
        btnNext.setText("下一个演示");



        svgaImageView.setCallback(new SVGACallback() {
            @Override
            public void onPause() {

            }

            @Override
            public void onFinished() {
                svgaImageView.setVisibility(View.GONE);
            }

            @Override
            public void onRepeat() {

            }

            @Override
            public void onStep(int frame, double percentage) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNext:
                //TODO implement
                tvName.setText(datas.get(i%13));
                SVGAParser parser = new SVGAParser(SimpleActivity.this);
                parser.parse(datas.get(i % 13), new SVGAParser.ParseCompletion() {
                    @Override
                    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
                        svgaImageView.setVisibility(View.VISIBLE);
                        SVGADrawable drawable = new SVGADrawable(videoItem);
                        svgaImageView.setImageDrawable(drawable);
                        svgaImageView.startAnimation();
                    }

                    @Override
                    public void onError() {

                    }
                });
               i=i+1;
                break;
        }
    }

}
