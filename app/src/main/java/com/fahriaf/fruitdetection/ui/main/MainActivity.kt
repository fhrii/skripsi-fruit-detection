package com.fahriaf.fruitdetection.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.fahriaf.fruitdetection.core.utils.createImageTempFile
import com.fahriaf.fruitdetection.R
import com.fahriaf.fruitdetection.databinding.ActivityMainBinding
import com.fahriaf.fruitdetection.ui.detail.DetailActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val getImageLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent(), this::onGetImage)
    private val getPictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture(), this::onGetPicture)
    private var tmpImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupLayout()
    }

    private fun setupLayout() {
        binding.run {
            btnGallery.setOnClickListener { getImage() }
            btnCamera.setOnClickListener { getPicture() }
        }
    }

    private fun getImage() {
        getImageLauncher.launch("image/*")
    }

    private fun getPicture() {
        tmpImageUri = cacheDir.createImageTempFile(this)
        getPictureLauncher.launch(tmpImageUri)
    }

    private fun onGetImage(uri: Uri?) {
        uri?.let {
            val intent = Intent(
                this,
                DetailActivity::class.java
            ).apply {
                putExtra(DetailActivity.EXTRA_IMAGE, it)
                putExtra(DetailActivity.EXTRA_ACTION, DetailActivity.ACTION_DETECT)
            }
            startActivity(intent)
        }
    }

    private fun onGetPicture(isSuccess: Boolean) {
        when {
            isSuccess -> {
                tmpImageUri?.let { uri ->
                    val intent = Intent(
                        this,
                        DetailActivity::class.java
                    ).apply {
                        putExtra(DetailActivity.EXTRA_IMAGE, uri)
                        putExtra(DetailActivity.EXTRA_ACTION, DetailActivity.ACTION_DETECT)
                    }
                    startActivity(intent)
                }
            }
            else -> {
                tmpImageUri = null
            }
        }
    }
}