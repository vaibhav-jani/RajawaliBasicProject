package com.websoft.trial.rajawalibasicproject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.websoft.trial.rajawalibasicproject.renderer.EarthObjectRenderer;

import org.rajawali3d.IRajawaliDisplay;
import org.rajawali3d.surface.IRajawaliSurface;
import org.rajawali3d.surface.IRajawaliSurfaceRenderer;


public class RajawaliExampleActivity extends ActionBarActivity implements IRajawaliDisplay {

    protected IRajawaliSurface mRajawaliSurface;

    protected IRajawaliSurfaceRenderer mRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(getLayoutID());

        mRenderer = createRenderer();

        initViews();

        setupDisplay();
    }

    private void setupDisplay() {

        mRajawaliSurface.setSurfaceRenderer(mRenderer);
    }

    private void initViews() {

        mRajawaliSurface = (IRajawaliSurface) findViewById(R.id.rajwali_surface);
    }

    @Override
    public IRajawaliSurfaceRenderer createRenderer() {

        return new EarthObjectRenderer(this);
    }

    @Override
    public int getLayoutID() {

        return R.layout.rajawali_texture_view;
    }
}
