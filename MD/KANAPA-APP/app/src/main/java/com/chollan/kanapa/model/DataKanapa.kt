package com.chollan.kanapa.model

import com.chollan.kanapa.R

object DataKanapa {
    val storeList: List<NearStore>
        get() {
            val list = listOf(
                NearStore(
                    "Toko Ikan Hias Pak Manan",
                    "Jl. Pakubuwono, Jalaran, Semarang",
                    1094,
                    "Tersedia : Cupang,  Koi, Manfish, dll",
                    -6.9991684f, 110.38984f
                ),
                NearStore(
                    "Fish Store Aquamarine",
                    "Jl. Kebahagian no. 19, Bumiwati, Kudus",
                    1734,
                    "Tersedia : Koi, Cupang, Guppy, dll",
                    -6.9912257f, 110.43785f
                ),
                NearStore(
                    "Aquarium World & Pet",
                    "Jl. Pakubuwono, Jalaran, Semarang",
                    1356,
                    "Tersedia : Cupang, Lohan, Lemonfish, dll",
                    -7.053691f, 110.42466f
                ),
                NearStore(
                    "Oceanic Delights Sunter Jaya",
                    "Jl. Kebahagian no. 19, Bumiwati, Kudus",
                    2081,
                    "Tersedia : Ikan Cupang,  Koi, Moly, dll",
                    -7.033085f, 110.41644f
                )
            )
            return list
        }

    val historyList: List<FishDetect> = listOf(
        FishDetect(
            "Cupang",
            87,
            "Cupang, ikan laga, atau ikan adu siam (Betta sp.) adalah ikan air tawar yang habitat asalnya adalah beberapa negara di Asia Tenggara, antara lain Thailand, Malaysia, Brunei Darussalam, Singapura, Vietnam, dan Indonesia. Ikan ini mempunyai bentuk dan karakter yang unik dan cenderung agresif dalam mempertahankan wilayahnya. Di kalangan penggemar, ikan cupang umumnya terbagi atas tiga golongan, yaitu cupang hias, cupang aduan, dan cupang liar. Di Indonesia terdapat cupang asli, salah satunya adalah Betta channoides yang ditemukan di Pampang, Kalimantan Timur.",
            R.drawable.cupang
        ),
        FishDetect(
            "Manfish",
            83,
            "Manfish adalah spesies ikan dari genus Pterophyllum yang paling umum dipelihara di penangkaran. Binatang ini berasal dari Lembah Amazon di Amerika Selatan. Khususnya di Sungai Ucayali di Peru, Sungai Oyapock di Guyana Prancis, Sungai Essequibo di Guyana, serta Sungai Solimões, Amapá, dan Amazon di Brasil.",
            R.drawable.manfish
        ),

        FishDetect(
            "Lohan",
            91,
            "ikan Lou han merupakan salah satu ikan hias termahal di dunia yang dipelihara di dalam akuarium karena warna sisik mereka yang hidup serta benjolan kepala mereka yang berbentuk khas berjuluk \"benjol kelam\". Aslinya mereka hanya berhabitat di Indonesia dan Malaysia namun saat ini banyak di oleh penggemar ikan di seluruh pelosok Indonesia",
            R.drawable.lohan
        ),

        FishDetect(
            "Guppy",
            89,
            "ikan guppy adalah salah satu spesies ikan hias air tawar yang paling populer di dunia. Karena mudahnya menyesuaikan diri dan beranak-pinak, di banyak tempat di Indonesia ikan ini telah menjadi ikan liar yang memenuhi parit-parit dan selokan. Dalam perdagangan ikan hias dikenal sebagai guppy atau juga millionfish,[1] di berbagai daerah lokal seperti gepi (Btw.), bungkreung, serewu, atau sarewu (Sd.), cethul atau cithul (Jw.), klataw (Bjn.), dan lain-lain. ",
            R.drawable.guppy
        )
    )
}