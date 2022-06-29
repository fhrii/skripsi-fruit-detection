package com.fahriaf.fruitdetection.ui.detail

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.fahriaf.core.utils.toBitmap
import com.fahriaf.fruitdetection.R
import com.fahriaf.fruitdetection.core.data.Resource
import com.fahriaf.fruitdetection.core.domain.model.Fruit
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
        const val EXTRA_ACTION = "extra_action"
        const val EXTRA_FRUIT = "extra_fruit"

        const val ACTION_DETECT = "action_detect"
        const val ACTION_VIEW = "action_view"

        @StringRes
        private const val FRUIT_NOT_FOUND_RES = R.string.fruit_not_found
        @StringRes
        private const val FRUIT_PICTURES_WITH_NAME_RES = R.string.fruit_pictures_with_name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpLayout()
    }

    private fun setUpLayout() {
        binding.run {
            val action = intent.getStringExtra(EXTRA_ACTION)
            if (action == ACTION_DETECT) {
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
                            val otherFruits = resource.data.otherDetection

                            if (fruit == null) finish()
                            else {
                                tvTitle.text = fruit.name
                                tvTitleSci.text = fruit.scientificName
                                tvDescription.text = fruit.description
                                tvFruitPictures.text = String.format(getString(FRUIT_PICTURES_WITH_NAME_RES), fruit.name)

                                val itemFruitNameAdapter = ItemFruitNameAdapter()
                                val itemFruitPictureAdapter = ItemFruitPictureAdapter()

                                itemFruitNameAdapter.setFruits(otherFruits)
                                itemFruitPictureAdapter.setFruitImages(fruit.images)
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
                            if(otherFruits.isEmpty()) {
                                tvOtherFruit.setGone()
                            }
                        }
                        is Resource.Loading -> setLoading(true)
                        is Resource.Error -> {
                            finish()
                            Toast.makeText(this@DetailActivity, getString(FRUIT_NOT_FOUND_RES), Toast.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                setLoading(false, ACTION_VIEW)
                val fruit = intent.getParcelableExtra<Fruit>(EXTRA_FRUIT) as Fruit

                Glide.with(this@DetailActivity)
                    .load(fruit.images[1].url)
                    .centerCrop()
                    .into(ivUserImage)

                tvTitle.text = fruit.name
                tvTitleSci.text = fruit.scientificName
                tvDescription.text = fruit.description

                val itemFruitPictureAdapter = ItemFruitPictureAdapter()
                itemFruitPictureAdapter.setFruitImages(fruit.images)
                rvFruitPictures.adapter = itemFruitPictureAdapter
                rvFruitPictures.setHasFixedSize(true)
                rvFruitPictures.layoutManager =
                    GridLayoutManager(this@DetailActivity, 2)
                rvFruitPictures.addItemDecoration(GridMarginItemDecoration(16, 2))
            }
        }
    }

    private fun setLoading(isLoading: Boolean, action: String = ACTION_DETECT) {
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
                    tvFruitPictures.setVisible()
                    tvOtherFruit.setVisible()
                    rvOtherFruit.setVisible()
                    rvFruitPictures.setVisible()

                    if (action == ACTION_VIEW) {
                        tvOtherFruit.setGone()
                    }
                }
            }
        }
    }
}