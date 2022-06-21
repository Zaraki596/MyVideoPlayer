package com.example.myvideoplayer.utils

import android.content.res.AssetManager

fun AssetManager.readFile(fileName: String) : String {
    return open(fileName).bufferedReader().readText()
}