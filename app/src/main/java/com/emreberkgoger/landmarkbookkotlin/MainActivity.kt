package com.emreberkgoger.landmarkbookkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.emreberkgoger.landmarkbookkotlin.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding       // view binding işlemi için.
    private lateinit var landMarkList : ArrayList<LandMark>

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        // view binding işlemleri:
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //LandMark tipindeki nesneleri tutacağımız arrayListin initialize edilmesi:
        landMarkList = ArrayList<LandMark>()

        //Data
        val eiffel = LandMark("Eiffel", "France", R.drawable.eiffeltower)
        val pisa = LandMark("Pisa", "Italy", R.drawable.pisa)
        val colosseum = LandMark("Colosseum", "Italy", R.drawable.collesium)
        val londonBridge  = LandMark("London Bridge", "England", R.drawable.towerlondon)


        landMarkList.add(pisa)
        landMarkList.add(eiffel)
        landMarkList.add(londonBridge)
        landMarkList.add(colosseum)


        readFromFile(landMarkList)
        // RecyclerView
        binding.recyclerview.layoutManager = LinearLayoutManager(this) // Seçeneklerin alt alta görünmesini sağlar.
        val landmarkAdapter = LandMarkAdapter(landMarkList)        // Adapter yapısının kullanımı. Veriler ile layoutu bağlamak için kullanılır.
        binding.recyclerview.adapter = landmarkAdapter
    }


    fun readFromFile(landmarkList : ArrayList<LandMark>)
    {
        val inputStream = resources.openRawResource(R.raw.bilgiler)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val symbolInfos = ArrayList<String>()

        var line: String

        try
        {
            while (reader.readLine().also { line = it } != null) {
                symbolInfos.add(line) // Her satırı 'symbolInfos' listesine ekler.
            }
        }
        catch (e : Exception)
        {
            println("Dosya hatası!")
        }

        for (i in 0..landmarkList.size - 1)
        {
            landmarkList.get(i).infos.add(symbolInfos.get(i))
        }


    }
}