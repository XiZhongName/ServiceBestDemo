package com.example.wanzhuang.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wanzhuang.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    Banner banner;
    ArrayList<Integer> imgs = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,  null );
        banner = view.findViewById(R.id.banner);
        initBanner();
        return view;
    }

    private void initBanner() {
        imgs.add(R.mipmap.fengjing2);
        imgs.add(R.mipmap.fengjing1);
        imgs.add(R.mipmap.fengjing3);
        title.add("奥利奥");
        title.add("趣多多");
        title.add("好多鱼");
        banner.setImages(imgs);
        banner.setBannerTitles(title);
        banner.setImageLoader(new ImageLoadBanner());
        banner.setDelayTime(2000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
       /* banner.setBannerAnimation(Transformer.Accordion);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);*/
        banner.start();
    }

    class ImageLoadBanner extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(Integer.parseInt(path.toString()));
        }
    }
}