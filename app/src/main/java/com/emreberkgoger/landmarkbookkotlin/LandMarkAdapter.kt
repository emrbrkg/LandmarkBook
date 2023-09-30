package com.emreberkgoger.landmarkbookkotlin

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emreberkgoger.landmarkbookkotlin.databinding.RecyclerRowBinding


// Layout ve verileri bağlamak için gerekli olan Adapter sınıfını recyclerView kullanırken kendimiz oluşturuyoruz.
class LandMarkAdapter(var landmarkList : ArrayList<LandMark>) : RecyclerView.Adapter<LandMarkAdapter.LandmarkHolder>()  // Adapter sınıfından kalıtım alıyoruz. Ve aşağıdaki 3 metodu override etmemiz gerrekiyor.
{
    class LandmarkHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)  // ViewHolder bizden görünüm istiyor.
    {
    }

    // ViewHolder'ın ilk oluşturulduğunda ne olacağını yazdığımız fonksiyon
    // recycler_row layoutu ile kodu bağlama işlemini yapacağız. (view binding kullanarak.)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder
    {
        // inflate() metodu içinde doğrudan layoutInflater'ı çağıramıyoruz çünkü bir aktivite içerisinde değiliz. Bunun yerine hangi aktiviteye bağlı olduğumuzu belirleyen "parent" ile parent.context ile istediğimiz contexte ulaşıyoruz.
        // İkinci parametre view group yani nereye bağlanacağını belirler. Doğrudan parent diyebiliriz.
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LandmarkHolder(binding)
    }

    // Bu metot bağlandıktan sonra olacaklar için. Hangi textte hangi veri kullanılacak gibi.
    // Bağlandıktan sonra olanları yazacağımız için setOnClickListener ile tıklandığı zaman olacakları da burada yazıyoruz.
    override fun onBindViewHolder(holder: LandmarkHolder, position: Int)
    {
        holder.binding.recyclerViewTextView.setText(landmarkList.get(position).name)    // Ekranda ilgili pozisyondaki kent simgesinin ismini gösterir.
        holder.itemView.setOnClickListener {            // Görsele tıklandığında ne olacağını yazdığımız metot. intenti buranın içinde çağırarak ekranlar arası veri aktarımını sağlayabiliriz.
            // Oluşturduğumuz intent DetailsActivity sınıfına gidecek.
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)  // holder.itemView.context kullanılan görünümün contextini verir.
            intent.putExtra("landmark", landmarkList.get(position))         // intente ilgili pozisyondaki kent simgesi nesnesini diğer ekrana aktarmak üzere ekliyoruz.

            holder.itemView.context.startActivity(intent)  // Aktiviteyi başlatmak için.
        }
    }

    // Kaç tane item olacak:
    override fun getItemCount(): Int
    {
        return landmarkList.size
    }


}