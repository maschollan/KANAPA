package com.chollan.kanapa.model

import com.chollan.kanapa.R

object DataKanapa {
    val storeList: List<NearStore>
        get() {
            return listOf(
                NearStore(
                    "Toko Ikan Hias Pak Manan",
                    "Jl. Pakubuwono, Jalaran, Semarang",
                    1094.0,
                    "Tersedia : Cupang,  Koi, Manfish, Discuss, dll",
                    -6.9991684f, 110.38984f
                ),
                NearStore(
                    "Fish Store Aquamarine",
                    "Jl. Kebahagian no. 19, Bumiwati, Semarang",
                    1734.0,
                    "Tersedia : Koi, Cupang, Discuss, Guppy, dll",
                    -6.9912257f, 110.43785f
                ),
                NearStore(
                    "Aquarium World & Pet",
                    "Jl. Pakubuwono, Jalaran, Semarang",
                    1356.0,
                    "Tersedia : Cupang, discuss, Lohan, Lemonfish, dll",
                    -7.053691f, 110.42466f
                ),
                NearStore(
                    "Oceanic Delights Jaya",
                    "Jl. Kebahagian no. 19, Bumiwati, Semarang",
                    2081.0,
                    "Tersedia : Ikan Cupang,  Koi, Moly, discuss, dll",
                    -7.033085f, 110.41644f
                )
            )
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

    val detailFishList = listOf(
        DetailFish(
            "Cupang",
            "Cupang, ikan laga, atau ikan adu siam (Betta sp.) adalah ikan air tawar yang habitat asalnya adalah beberapa negara di Asia Tenggara, antara lain Thailand, Malaysia, Brunei Darussalam, Singapura, Vietnam, dan Indonesia. Ikan ini mempunyai bentuk dan karakter yang unik dan cenderung agresif dalam mempertahankan wilayahnya. Di kalangan penggemar, ikan cupang umumnya terbagi atas tiga golongan, yaitu cupang hias, cupang aduan, dan cupang liar. Di Indonesia terdapat cupang asli, salah satunya adalah Betta channoides yang ditemukan di Pampang, Kalimantan Timur.",
        ),
        DetailFish(
            "Discus",
            "Ikan discus, adalah genus cichlids asli lembah sungai Amazon di Amerika Selatan. Karena bentuk, perilaku, dan warna serta polanya yang khas, discus populer sebagai ikan akuarium air tawar, dan budidayanya di beberapa negara di Asia merupakan industri besar."
        ),
        DetailFish(
            "Guppy",
            "ikan guppy adalah salah satu spesies ikan hias air tawar yang paling populer di dunia. Karena mudahnya menyesuaikan diri dan beranak-pinak, di banyak tempat di Indonesia ikan ini telah menjadi ikan liar yang memenuhi parit-parit dan selokan. Dalam perdagangan ikan hias dikenal sebagai guppy atau juga millionfish,[1] di berbagai daerah lokal seperti gepi (Btw.), bungkreung, serewu, atau sarewu (Sd.), cethul atau cithul (Jw.), klataw (Bjn.), dan lain-lain. ",
            ),
        DetailFish(
            "Lemonfish",
            "Ikan lemon adalah spesies cichlid endemik di wilayah pesisir barat tengah Danau Malawi di Afrika Timur. Varian berwarna kuning alami dari Lion's Cove adalah salah satu cichlid paling populer di kalangan penghobi akuarium. Ikan lemon dikenal ikan yang damai dibandingkan dengan kebanyakan cichlid Afrika lainnya. Meskipun demikian, seperti semua cichlid dari Danau Malawi, mereka paling baik dipelihara di akuarium spesialis cichlid bersama Mbuna lainnya."
        ),
        DetailFish(
            "Lohan",
            "ikan Lohan merupakan salah satu ikan hias termahal di dunia yang dipelihara di dalam akuarium karena warna sisik mereka yang hidup serta benjolan kepala mereka yang berbentuk khas berjuluk \"benjol kelam\". Aslinya mereka hanya berhabitat di Indonesia dan Malaysia namun saat ini banyak di oleh penggemar ikan di seluruh pelosok Indonesia",
        ),
        DetailFish(
            "Manfish",
            "Manfish adalah spesies ikan dari genus Pterophyllum yang paling umum dipelihara di penangkaran. Binatang ini berasal dari Lembah Amazon di Amerika Selatan. Khususnya di Sungai Ucayali di Peru, Sungai Oyapock di Guyana Prancis, Sungai Essequibo di Guyana, serta Sungai Solimões, Amapá, dan Amazon di Brasil.",
        ),
        DetailFish(
            "Maskoki",
            "Ikan mas adalah ikan air tawar dari familia Cyprinidae dan ordo Cypriniformes. Ikan ini adalah salah satu ikan yang pertama kali berhasil didomestikasi, dipelihara, dan dibudidayakan manusia. Kini ikan mas hias atau kadang disebut secara singkat sebagai ikan mas, adalah salah satu ikan hias akuarium yang paling populer. Varietas Carassius auratus auratus yang telah didomestikasi dan menampilkan mutasi tubuh bersirip ekor ganda dan berbentuk memampat bulat disebut ikan maskoki."
        ),
        DetailFish(
            "Molly",
            "Ikan Molly / Poecilia adalah genus ikan dalam keluarga Poeciliidae dari ordo Cyprinodontiformes. Pembawa hidup ini berasal dari air tawar, payau dan asin di Amerika, dan beberapa spesies dalam genus adalah euryhaline."
        ),
        DetailFish(
            "NeonTetra",
            "Neon Tetra adalah adalah Ikan air tawar dari famili characin (famili Characidae) dari ordo Characiformes. Jenis spesies dari genusnya, berasal dari sungai air hitam dan air jernih di Basin Amazon Amerika Selatan.[1][2] Warnanya yang cerah membuat ikan ini terlihat oleh ikan sejenis di aliran blackwater yang gelap,[3] dan juga merupakan alasan utama popularitasnya di kalangan penghobi ikan air tawar, dengan neon tetra menjadi salah satu ikan tropis yang paling banyak dipelihara di dunia."
        ),
        DetailFish(
            "Sapusapu",
            "Ikan sapu-sapu atau ikan bandaraya adalah sekelompok ikan air tawar yang berasal dari Amerika Selatan yang termasuk dalam famili Loricariidae, namun tidak semua anggota Loricariidae adalah sapu-sapu. Ikan ini dikenal sebagai pemakan alga/\"lumut\" dan sangat populer sebagai ikan pembersih akuarium. Dalam perdagangan ikan internasional ia dikenal sebagai plecostomus atau singkatannya, plecos dan plecs. Di Malaysia orang menyebutnya \"ikan bandaraya\" karena fungsinya seperti petugas pembersih kota (\"bandar\"). Dalam bahasa inggris dikenal sebagai \"Pleco\". Di Indonesia, analogi yang sama juga dipakai tetapi alatnya yang dipakai sebagai nama (sapu)."
        ),
        DetailFish(
            "Zebra",
            "Ikan zebra (Danio rerio) adalah salah satu spesies ikan bermarga Danio dari keluarga siprinide.[1] Penggunaan nama umum \"ikan zebra\" pada Danio rerio didasari oleh adanya garis-garis pigmen horizontal pada tubuh ikan zebra yang menyerupai garis-garis pada tubuh zebra.[2] Ukuran tubuh ikan zebra sekitar 3–5 sentimeter dengan kulit belang berwarna biru kehitaman dan jingga kekuningan.[3] Ikan zebra hidup di perairan yang tenang dengan permukaan tanah berpasir, berlanau, atau berkerikil di area persawahan, lahan basah, dan akuarium.[4] Habitat asli ikan zebra adalah di negara-negara yang termasuk dalam kawasan Asia Selatan."
        ),
    )
}