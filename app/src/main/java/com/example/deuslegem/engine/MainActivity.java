package com.example.deuslegem.engine;

import android.app.Activity;
import android.os.Bundle;
import com.example.deuslegem.layer.SplashLayer;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MainActivity extends Activity {
    private CCDirector ccDirector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_ACTION_BAR);
        CCGLSurfaceView surfaceView = new CCGLSurfaceView(this);
        setContentView(surfaceView);
        ccDirector = CCDirector.sharedDirector();
        ccDirector.attachInView(surfaceView);
        ccDirector.setDisplayFPS(true);
        ccDirector.setScreenSize(512, 256);
        ccDirector.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
        CCScene scene = CCScene.node();
        SplashLayer splashLayer = new SplashLayer();
        scene.addChild(splashLayer);
        ccDirector.runWithScene(scene);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ccDirector.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ccDirector.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ccDirector.end();
    }
}
