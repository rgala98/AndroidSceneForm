package com.replon.www.androidsceneform;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ViewRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {



    ArFragment arFragment;
    private ModelRenderable tvRender, lionRender;

    View arrayView[];
    ViewRenderable name_animal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_ux_fragment);

        setupModel();


        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                // when user taps on plane, add model
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());

                createModel(anchorNode);


            }
        });


    }

    private void createModel(AnchorNode anchorNode){

        TransformableNode tvSet = new TransformableNode(arFragment.getTransformationSystem());
        tvSet.setParent(anchorNode);
        tvSet.setRenderable(tvRender);
        tvSet.select();
    }

    private void setupModel(){
        ModelRenderable.builder()
                .setSource(this, R.raw.tv)
                .build().thenAccept(renderable -> tvRender = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unable to load TV MODEL", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );

    }
}
