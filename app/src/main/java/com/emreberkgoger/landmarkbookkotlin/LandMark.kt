package com.emreberkgoger.landmarkbookkotlin

import java.io.Serializable
import java.lang.StringBuilder

// Primary constructorda image'a integer verme sebebimiz android studionun görselleri integer şeklinde tutması.
class LandMark(val name : String, val country : String, val image : Int) : Serializable
{
    var infos = ArrayList<String>()


}