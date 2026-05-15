package com.example.ar.ui.screens

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ar.R
import com.google.ar.core.Config
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class RealARActivity : AppCompatActivity() {

    private var arFragment: ArFragment? = null
    private var modelRenderable: ModelRenderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_real_ar)

        arFragment = supportFragmentManager.findFragmentById(R.id.ar_fragment) as? ArFragment

        if (arFragment == null) {
            Toast.makeText(this, "Erreur AR", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        arFragment?.setOnSessionConfigurationListener { session, config ->
            config.lightEstimationMode = Config.LightEstimationMode.DISABLED
            session.configure(config)
        }

        loadModel()

        arFragment?.setOnTapArPlaneListener { hitResult, _, _ ->
            val renderable = modelRenderable
            if (renderable == null) {
                Toast.makeText(this, "Modèle non chargé", Toast.LENGTH_SHORT).show()
                return@setOnTapArPlaneListener
            }

            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment?.arSceneView?.scene)

            val node = TransformableNode(arFragment?.transformationSystem)
            node.renderable = renderable
            node.setParent(anchorNode)
            node.select()
        }
    }

    private fun loadModel() {
        ModelRenderable.builder()
            .setSource(this, Uri.parse("sofa.glb"))
            .setIsFilamentGltf(true)
            .build()
            .thenAccept { renderable ->
                modelRenderable = renderable
                Toast.makeText(this, "Sofa prêt !", Toast.LENGTH_SHORT).show()
            }
            .exceptionally { throwable ->
                runOnUiThread {
                    Toast.makeText(this, "Erreur: ${throwable.message}", Toast.LENGTH_LONG).show()
                }
                null
            }
    }
}
