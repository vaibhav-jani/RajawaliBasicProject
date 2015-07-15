package com.websoft.trial.rajawalibasicproject.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.websoft.trial.rajawalibasicproject.R;
import com.websoft.trial.rajawalibasicproject.renderer.EarthObjectRenderer;

import org.rajawali3d.IRajawaliDisplay;
import org.rajawali3d.surface.IRajawaliSurface;
import org.rajawali3d.surface.IRajawaliSurfaceRenderer;

/**
 * Created by vaibhav.jani on 4/21/2015.
 */
public class EarthFragment extends Fragment implements IRajawaliDisplay {

    protected IRajawaliSurface mRajawaliSurface;

    protected IRajawaliSurfaceRenderer mRenderer;

    private View mLayout;

    public static EarthFragment getInstance() {

        EarthFragment earthFragment = new EarthFragment();
        return earthFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mLayout = inflater.inflate(getLayoutID(), container, false);

        initViews();

        setupDisplay();

        return mLayout;
    }


    private void setupDisplay() {

        mRenderer = createRenderer();
        mRajawaliSurface.setSurfaceRenderer(mRenderer);
    }

    private void initViews() {

        mRajawaliSurface = (IRajawaliSurface) mLayout.findViewById(R.id.rajwali_surface);
    }

    @Override
    public IRajawaliSurfaceRenderer createRenderer() {

        return new EarthObjectRenderer(getActivity());
        //return new TouchEarthObjectRenderer(getActivity());
    }

    @Override
    public int getLayoutID() {

        return R.layout.rajawali_texture_view;
    }
}
