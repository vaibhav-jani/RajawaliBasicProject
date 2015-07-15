package com.websoft.trial.rajawalibasicproject.renderer;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.MotionEvent;

import com.websoft.trial.rajawalibasicproject.R;
import com.websoft.trial.rajawalibasicproject.util.FileUtils;

import org.rajawali3d.Object3D;
import org.rajawali3d.cameras.ArcballCamera;
import org.rajawali3d.cameras.Camera;
import org.rajawali3d.lights.DirectionalLight;
import org.rajawali3d.loader.LoaderOBJ;
import org.rajawali3d.renderer.RajawaliRenderer;

import java.io.File;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by vaibhav.jani on 4/21/2015.
 */

public class ExperimentObjectRenderer extends RajawaliRenderer {

    public DirectionalLight light;

    public Object3D object3D;

    public Context context;

    public Camera camera;

    public ExperimentObjectRenderer(Context context) {

        super(context);
        this.context = context;
        setFrameRate(60);
    }

    public void initScene() {

        light = new DirectionalLight(1f, 0.2f, -1.0f); // set the direction
        light.setColor(1.0f, 1.0f, 1.0f);
        light.setPower(2);

        try {
            /*Material material = new Material();
            material.addTexture(new Texture("earthColors", R.drawable.earthtruecolor_nasa_big));
            material.setColorInfluence(0);

            object3D = new Sphere(1, 24, 24);
            object3D.setMaterial(material);*/

            /*LoaderOBJ objParser = new LoaderOBJ(mContext.getResources(), mTextureManager, R.raw.dolfin);

            objParser.parse();
            object3D = objParser.getParsedObject();
            object3D.setZ(4);
            object3D.setRotY(180);*/

            File file = new File(FileUtils.getFilePathForFile("dolfin.obj", "car2"));

            if(file.exists()) {

                LoaderOBJ objParser = new LoaderOBJ(this, file.getAbsoluteFile());

                objParser.parse();
                object3D = objParser.getParsedObject();
                object3D.setZ(4);
                object3D.setRotY(60);
            }

            /*Material roadMaterial = new Material();
            roadMaterial.enableLighting(true);
            roadMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
            roadMaterial.addTexture(new Texture("roadTex", R.drawable.road));
            roadMaterial.setColorInfluence(0);
            object3D.getChildByName("Road").setMaterial(roadMaterial);

            Material signMaterial = new Material();
            signMaterial.enableLighting(true);
            signMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
            signMaterial.addTexture(new Texture("rajawaliSign", R.drawable.sign));
            signMaterial.setColorInfluence(0);
            object3D.getChildByName("WarningSign").setMaterial(signMaterial);

            Material warningMaterial = new Material();
            warningMaterial.enableLighting(true);
            warningMaterial.setDiffuseMethod(new DiffuseMethod.Lambert());
            warningMaterial.addTexture(new Texture("warning", R.drawable.warning));
            warningMaterial.setColorInfluence(0);
            object3D.getChildByName("Warning").setMaterial(warningMaterial);*/

            getCurrentScene().addLight(light);
            getCurrentScene().addChild(object3D);
            getCurrentScene().setBackgroundColor(Color.LTGRAY);

        } catch (Exception e) {
            e.printStackTrace();
        }

        //getCurrentCamera().setZ(4.2f);

        //getCurrentCamera().setLookAt(0, 0, 0);
       // getCurrentCamera().setZ(6);

        ArcballCamera arcball = new ArcballCamera(mContext, ((Activity)mContext).findViewById(R.id.frame_layout));
        arcball.setPosition(4, 4, 4);
        getCurrentScene().replaceAndSwitchCamera(getCurrentCamera(), arcball);
    }

    @Override
    public void onRenderFrame(GL10 gl) {

        super.onRenderFrame(gl);

        //object3D.rotate(Vector3.Axis.Y, 1.0);
    }

    @Override
    public void onOffsetsChanged(float v, float v2, float v3, float v4, int i, int i2) {

    }

    @Override
    public void onTouchEvent(MotionEvent motionEvent) {

    }
}