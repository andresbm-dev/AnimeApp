package com.example.animeapp.domain.models

import com.google.gson.annotations.SerializedName
data class AnimeTopScoreModelApi(
    @SerializedName("data") var animeData: List<AnimeTopScoreModel>? = null,

    )
data class AnimeTopScoreModel(
    @SerializedName("mal_id") var animeId : Int? = null,
    @SerializedName("images") var animeImages : ImagesAnime? = null,
    @SerializedName("title") var animeTitle : String? = null,
    @SerializedName("rating") var animeRating : String? = null,
    @SerializedName("synopsis") var animeSynopsis : String? = null,
    @SerializedName("airing") var animeAiring : Boolean? = null,
    @SerializedName("duration") var animeDuration : String? = null,
    @SerializedName("season") var animeSeason : String? = null,
    @SerializedName("score") var animeScore : String? = null,
)

data class ImagesAnime(
    @SerializedName("jpg") var animeJpg : AnimeJpg? = null
)

data class AnimeJpg(
    @SerializedName("image_url") var animeImage : String? = null
)