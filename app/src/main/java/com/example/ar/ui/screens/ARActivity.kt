package com.example.ar

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode

class ARActivity : AppCompatActivity() {

    private lateinit var arFragment: ArFragment
    private var sofaRenderable: ModelRenderable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arFragment = ArFragment()

        supportFragmentManager.beginTransaction()
            .add(android.R.id.content, arFragment)
            .commitNow()

        ModelRenderable.builder()
            .setSource(this, Uri.parse("sofa.glb"))
            .build()
            .thenAccept { renderable ->
                sofaRenderable = renderable
                Toast.makeText(this, "Modèle sofa chargé", Toast.LENGTH_SHORT).show()
            }
            .exceptionally { throwable ->
                Toast.makeText(this, "Erreur chargement sofa.glb", Toast.LENGTH_LONG).show()
                throwable.printStackTrace()
                null
            }

        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->

            val renderable = sofaRenderable
            if (renderable == null) {
                Toast.makeText(this, "Le sofa n'est pas encore chargé", Toast.LENGTH_SHORT).show()
                return@setOnTapArPlaneListener
            }

            val anchor = hitResult.createAnchor()
            val anchorNode = AnchorNode(anchor)
            anchorNode.setParent(arFragment.arSceneView.scene)

            val node = TransformableNode(arFragment.transformationSystem)
            node.renderable = renderable
            node.setParent(anchorNode)
            node.select()
        }
    }
}