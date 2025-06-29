package com.nutrivision.app.utils

object NutritionTips {
    val underweightTips = arrayOf(
        "Makan makanan padat gizi: lemak sehat, protein, karbohidrat kompleks setiap makan.",
        "Tingkatkan kalori sehat: masukkan alpukat, kacang-kacangan, biji-bijian, minyak zaitun.",
        "Makan lebih sering: targetkan 5-6 porsi kecil atau camilan setiap hari.",
        "Prioritaskan protein tanpa lemak: termasuk daging, unggas, ikan, telur, dan kacang-kacangan.",
        "Pilih minuman sehat: susu, jus buah 100%, atau smoothie kaya protein.",
        "Tambahkan lemak sehat: minyak zaitun di salad, kacang di oatmeal, alpukat di roti panggang.",
        "Gabungkan latihan kekuatan untuk membantu membangun massa otot yang sehat.",
        "Jangan lewatkan waktu makan; konsistensi penting untuk asupan kalori stabil.",
        "Pertimbangkan camilan sehat: yoghurt, buah, selai kacang, keju, dan biskuit.",
        "Konsultasikan ahli gizi untuk rencana penambahan berat badan sehat yang personal."
    )

    val normalWeightTips = arrayOf(
        "Pertahankan diet seimbang: buah, sayuran, biji-bijian utuh, protein tanpa lemak.",
        "Latih kontrol porsi: perhatikan ukuran sajian Anda.",
        "Tetap terhidrasi: minum banyak air sepanjang hari Anda.",
        "Batasi makanan olahan, minuman manis, dan camilan tidak sehat.",
        "Sertakan beragam makanan nabati: makan berbagai warna.",
        "Pilih biji-bijian utuh: pilih nasi merah, roti gandum, dan oat.",
        "Prioritaskan sumber protein tanpa lemak: ayam, ikan, kacang-kacangan, lentil, tahu.",
        "Batasi lemak sehat secukupnya: konsumsi kacang, biji-bijian, alpukat dalam porsi.",
        "Dengarkan isyarat lapar tubuh Anda: makan saat lapar, berhenti saat kenyang.",
        "Gabungkan diet sehat dengan aktivitas fisik yang konsisten."
    )

    val overweightTips = arrayOf(
        "Fokus pada makanan utuh, tanpa olahan: buah, sayuran, protein tanpa lemak.",
        "Kurangi minuman manis dan gula tambahan; itu menambahkan kalori kosong.",
        "Tingkatkan serat: sayuran, buah, biji-bijian utuh membantu Anda merasa kenyang.",
        "Latih makan dengan penuh perhatian: perhatikan makanan, makan perlahan, nikmati.",
        "Kontrol ukuran porsi: gunakan piring kecil, perhatikan sajian.",
        "Batasi lemak tidak sehat: kurangi lemak trans dan lemak jenuh.",
        "Tetap terhidrasi dengan baik: air membantu kenyang dan metabolisme.",
        "Rencanakan makanan Anda sebelumnya untuk menghindari pilihan impulsif tidak sehat.",
        "Prioritaskan protein tanpa lemak setiap makan untuk kenyang dan otot.",
        "Cari panduan profesional dari ahli gizi untuk manajemen berat badan."
    )

    val obesityTips = arrayOf(
        "Bekerja dengan tim medis untuk pendekatan yang komprehensif.",
        "Fokus pada perubahan bertahap, berkelanjutan, bukan diet drastis.",
        "Tekankan sayuran non-tepung: penuhi separuh piring Anda.",
        "Hilangkan minuman manis; beralih ke air atau teh tanpa gula.",
        "Masak di rumah lebih sering untuk kontrol bahan yang lebih baik.",
        "Batasi makanan olahan tinggi dan makanan cepat saji, tinggi kalori.",
        "Tingkatkan aktivitas fisik secara bertahap, mulailah dengan jalan santai.",
        "Lacak asupan makanan jika membantu kesadaran pola makan.",
        "Atasi makan emosional: identifikasi pemicu, temukan penanganan lebih sehat.",
        "Fokus pada peningkatan kesehatan secara keseluruhan di luar penurunan berat badan."
    )


    fun getRandomTipBasedOnBMI(bmi: Float): String {
        return when {
            bmi < 18.5 -> underweightTips.random()
            bmi >= 18.5 && bmi < 25.0 -> normalWeightTips.random()
            bmi >= 25.0 && bmi < 30.0 -> overweightTips.random()
            bmi >= 30.0 -> obesityTips.random()
            else -> "Invalid BMI value. Please provide a positive number for BMI."
        }
    }
}