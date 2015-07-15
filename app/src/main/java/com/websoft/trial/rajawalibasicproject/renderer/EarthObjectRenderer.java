package com.websoft.trial.rajawalibasicproject.renderer;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;

import com.websoft.trial.rajawalibasicproject.R;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.cameras.Camera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.materials.Material;
import org.rajawali3d.materials.textures.ATexture.TextureException;
import org.rajawali3d.materials.textures.Texture;
import org.rajawali3d.primitives.Sphere;
import org.rajawali3d.renderer.RajawaliRenderer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by vaibhav.jani on 4/21/2015.
 */

public class EarthObjectRenderer extends RajawaliRenderer {

    public DirectionalLight light;

    public Object3D sphereObject;

    public Context context;

    public Camera camera;

    public EarthObjectRenderer(Context context) {

        super(context);
        this.context = context;
        setFrameRate(60);
    }

    public void initScene() {

        light = new DirectionalLight(1f, 0.2f, -1.0f); // set the direction
        light.setColor(1.0f, 1.0f, 1.0f);
        light.setPower(2);

        try {
            Material material = new Material();
            material.addTexture(new Texture("earthColors", R.drawable.earthtruecolor_nasa_big));
            material.setColorInfluence(0);
            sphereObject = new Sphere(1, 120, 120);
            sphereObject.setMaterial(material);
            getCurrentScene().addLight(light);
            getCurrentScene().addChild(sphereObject);

        } catch (TextureException e) {
            e.printStackTrace();
        }

        getCurrentCamera().setLookAt(0, 0, 0);
        getCurrentCamera().setZ(4);


        ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.frame_layout));
        arcball.setPosition(2, 2, 4);
        getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);
    }

    @Override
    public void onRenderFrame(GL10 gl) {

        super.onRenderFrame(gl);

        //sphereObject.rotate(Vector3.Axis.Y, 1.0);
    }

    @Override
    public void onOffsetsChanged(float v, float v2, float v3, float v4, int i, int i2) {

    }

    @Override
    public void onTouchEvent(MotionEvent motionEvent) {

    }
}