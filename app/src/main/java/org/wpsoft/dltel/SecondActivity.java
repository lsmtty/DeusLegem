package org.wpsoft.dltel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.deuslegem.layer.PlayLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class SecondActivity extends AppCompatActivity {
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
        ccDirector.setScreenSize(480, 320);
        ccDirector.setDeviceOrientation(CCDirector.kCCDeviceOrientationLandscapeLeft);
        CCScene ccScene = CCScene.node();
        ccScene.addChild(new PlayLayer());
        ccDirector.runWithScene(ccScene);
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
