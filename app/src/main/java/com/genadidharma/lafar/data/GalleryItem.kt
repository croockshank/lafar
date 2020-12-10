import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.genadidharma.lafar.R
import com.genadidharma.lafar.data.*

object GalleryItem {
    val galleries = listOf(
            Gallery(
                    RestaurantItem.getRestaurant(1),
                    listOf(
                            Asset(
                                    GalleryType.ALBUM,
                                    1,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_16,
                                    null,
                                    "November 11, 2020"
                            ),
                            Asset(
                                    GalleryType.ALBUM,
                                    2,
                                    AssetType.VIDEO,
                                    R.drawable.img_restaurant_17,
                                    null,
                                    "October 21, 2020"
                            ),
                            Asset(
                                    GalleryType.ALBUM,
                                    3,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_18,
                                    null,
                                    "November 15, 2020"
                            ),
                            Asset(
                                    GalleryType.POSTINGAN_TEMAN,
                                    4,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_19,
                                    PersonItem.getPerson(4),
                                    "September 8, 2020"
                            ),
                            Asset(
                                    GalleryType.POSTINGAN_TEMAN,
                                    5,
                                    AssetType.VIDEO,
                                    R.drawable.img_restaurant_20,
                                    PersonItem.getPerson(2),
                                    "December 3, 2020"
                            ),
                            Asset(
                                    GalleryType.POSTINGAN_TEMAN,
                                    6,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_21,
                                    PersonItem.getPerson(3),
                                    "November 10, 2020"
                            )
                    )
            )
    )

    val _galleries: MutableLiveData<List<Gallery>> = MutableLiveData()

    fun getGalleries(restaurant: Restaurant?, type: GalleryType): LiveData<List<Asset>>? {
        return Transformations.map(_galleries) { gallery ->
            gallery.find {
                it.restaurant == restaurant
            }?.assets?.filter {
                it.galleryType == type
            }?.toList()
        }
    }

    fun getGalleries(restaurant: Restaurant?): LiveData<List<Asset>>? {
        return Transformations.map(_galleries) { gallery ->
            gallery.find {
                it.restaurant == restaurant
            }?.assets?.toList()
        }
    }

    fun getGalleries(): LiveData<List<Asset>>? {
        return Transformations.map(_galleries) { gallery ->
            gallery[0].assets
        }
    }

    init {
        _galleries.value = galleries
    }
}