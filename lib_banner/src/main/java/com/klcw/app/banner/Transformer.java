package com.klcw.app.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import com.klcw.app.banner.transformer.AccordionTransformer;
import com.klcw.app.banner.transformer.BackgroundToForegroundTransformer;
import com.klcw.app.banner.transformer.CubeInTransformer;
import com.klcw.app.banner.transformer.CubeOutTransformer;
import com.klcw.app.banner.transformer.DefaultTransformer;
import com.klcw.app.banner.transformer.DepthPageTransformer;
import com.klcw.app.banner.transformer.FlipHorizontalTransformer;
import com.klcw.app.banner.transformer.FlipVerticalTransformer;
import com.klcw.app.banner.transformer.ForegroundToBackgroundTransformer;
import com.klcw.app.banner.transformer.RotateDownTransformer;
import com.klcw.app.banner.transformer.RotateUpTransformer;
import com.klcw.app.banner.transformer.ScaleInOutTransformer;
import com.klcw.app.banner.transformer.StackTransformer;
import com.klcw.app.banner.transformer.TabletTransformer;
import com.klcw.app.banner.transformer.ZoomInTransformer;
import com.klcw.app.banner.transformer.ZoomOutSlideTransformer;
import com.klcw.app.banner.transformer.ZoomOutTranformer;

public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
