package com.example.myvideoplayer.utils

import android.content.res.AssetManager
import org.json.JSONObject

fun AssetManager.readFile(fileName: String): String {
    return open(fileName).bufferedReader().readText()
}