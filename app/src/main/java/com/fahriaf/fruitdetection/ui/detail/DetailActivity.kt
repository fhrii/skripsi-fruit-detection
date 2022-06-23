package com.fahriaf.fruitdetection.ui.detail

import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.fahriaf.core.utils.toBitmap
import com.fahriaf.fruitdetection.R
import com.fahriaf.fruitdetection.core.data.Resource
import com.fahriaf.fruitdetection.core.utils.GridMarginItemDecoration
import com.fahriaf.fruitdetection.core.utils.LinearMarginItemDecoration
import com.fahriaf.fruitdetection.core.utils.setGone
import com.fahriaf.fruitdetection.core.utils.setVisible
import com.fahriaf.fruitdetection.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity(R.layout.activity_detail) {
    private val binding: ActivityDetailBinding by viewBinding()
    private val detailViewModel: DetailViewModel by viewModels()

    companion object {
        const val EXTRA_IMAGE = "extra_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpLayout()
    }

    private fun setUpLayout() {
        binding.run {
            val uri = intent.getParcelableExtra<Uri>(EXTRA_IMAGE) as Uri
            val image = uri.toBitmap(contentResolver)

            if (image == null) {
                finish()
                return
            }

            Glide.with(this@DetailActivity)
                .load(image)
                .centerCrop()
                .into(ivUserImage)

            detailViewModel.getFruitDetection(image).observe(this@DetailActivity) { resource ->
                when (resource) {
                    is Resource.Success -> {
                        val fruit = resource.data.fruit
                        val otherFruitNames = resource.data.otherDetection

                        if (fruit == null) finish()
                        else {
                            tvTitle.text = fruit.name
                            tvTitleSci.text = fruit.scientificName
                            tvDescription.text = fruit.description

                            val itemFruitNameAdapter = ItemFruitNameAdapter()
                            val itemFruitPictureAdapter = ItemFruitPictureAdapter()
                            itemFruitNameAdapter.setFruitNames(otherFruitNames)
                            itemFruitPictureAdapter.setFruitImagesUrl(fruit.images)
                            rvOtherFruit.adapter = itemFruitNameAdapter
                            rvOtherFruit.setHasFixedSize(true)
                            rvOtherFruit.addItemDecoration(
                                LinearMarginItemDecoration(16, LinearLayoutManager.HORIZONTAL)
                            )
                            rvFruitPictures.adapter = itemFruitPictureAdapter
                            rvFruitPictures.setHasFixedSize(true)
                            rvFruitPictures.layoutManager =
                                GridLayoutManager(this@DetailActivity, 2)
                            rvFruitPictures.addItemDecoration(GridMarginItemDecoration(16, 2))
                        }

                        setLoading(false)
                    }
                    is Resource.Loading -> setLoading(true)
                    is Resource.Error -> {
                        finish()
                    }
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.run {
            when {
                isLoading -> {
                    progressbar.setVisible()
                    tvTitle.setGone()
                    tvTitleSci.setGone()
                    tvDescriptionLabel.setGone()
                    tvDescription.setGone()
                    tvOtherFruit.setGone()
                    tvFruitPictures.setGone()
                    rvOtherFruit.setGone()
                    rvFruitPictures.setGone()
                }
                else -> {
                    progressbar.setGone()
                    tvTitle.setVisible()
                    tvTitleSci.setVisible()
                    tvDescriptionLabel.setVisible()
                    tvDescription.setVisible()
                    tvOtherFruit.setVisible(); tvFruitPictures.setVisible()
                    rvOtherFruit.setVisible()
                    rvFruitPictures.setVisible()
                }
            }
        }
    }
}