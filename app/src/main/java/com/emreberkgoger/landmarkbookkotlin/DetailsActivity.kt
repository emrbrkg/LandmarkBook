package com.emreberkgoger.landmarkbookkotlin

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.emreberkgoger.landmarkbookkotlin.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity()
{
    private lateinit var binding : ActivityDetailsBinding
    // Localde verileri saklamak için kullanılan yapı SharedPreferences
    private lateinit var sharedPreferencesAverage : SharedPreferences
    private lateinit var sharedPreferencesTotal : SharedPreferences
    private lateinit var sharedPreferencesCount : SharedPreferences
    var generalRate = 1
    var totalRate = 0
    var rateCount = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        // View binding:
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // SharedPreferences'ların oluşturulması:
        sharedPreferencesAverage = this.getSharedPreferences("com.emreberkgoger.LandMarkBookKotlin", MODE_PRIVATE)
        sharedPreferencesTotal = this.getSharedPreferences("com.emreberkgoger.LandMarkBookKotlin", MODE_PRIVATE)
        sharedPreferencesCount = this.getSharedPreferences("com.emreberkgoger.LandMarkBookKotlin", MODE_PRIVATE)

        // sharedPreferences'ların elde edilmesi. Ekrana yazdırmıyoruz sadece tutuyoruz.
        generalRate = sharedPreferencesAverage.getInt("rate", 0)
        totalRate = sharedPreferencesTotal.getInt("total", 0)
        rateCount = sharedPreferencesCount.getInt("count", 0)

        println("general rate is = " + generalRate)
        println("Total points is = " + totalRate)


        val intent = intent
        // LandMarkAdapter sınıfında intente eklediğimiz  verilerin bu ekranda çağırıldığı kısım:
        val landmark = intent.getSerializableExtra("landmark") as LandMark


        // Ekranda görsel, isim ve ülke adı bulunuyor:
        binding.NameText.setText(landmark.name)
        binding.CountryText.setText(landmark.country)
        binding.imageView.setImageResource(landmark.image)
        binding.scrolViewText.setText(landmark.infos.toString())
        binding.editText.setText(binding.editText.text)

    }

    // Kullanıcıdan istenen 1-10 arası puanlamayı kaydedeceğimiz butonun "onClick" fonksiyonu:
    // Uygulama kapandığında da verileri kaybetmemek için sharedPreferences yapıları kullandım.
    fun save(view : View)
    {
        // Inputa integer değer girilmemesi durumuna karşın önlem olarak try catch bloklarıyla yazdık.
        try {
            val rate = binding.editText.text.toString().toInt()     // editTextten okunan değerin integere çevrilip rate değişkenine atnması.
            if (rate >= 1 && rate <=10)
            {
                rateCount++
                totalRate += rate
                sharedPreferencesTotal.edit().putInt("total", totalRate).apply()
                sharedPreferencesCount.edit().putInt("count", rateCount).apply()
                val averageSoFar = sharedPreferencesTotal.getInt("total", 0) / sharedPreferencesCount.getInt("count", 1)
                sharedPreferencesAverage.edit().putInt("rate", averageSoFar).apply()
            }

        }catch (e : Exception) {
            println("Input is not integer")
        }
        // Butona arka arkaya tekrar tekrar basılmaması için bir kez tıklandıktan sonra buton kayboluyor.
        binding.saveButton.visibility = View.INVISIBLE
    }

}