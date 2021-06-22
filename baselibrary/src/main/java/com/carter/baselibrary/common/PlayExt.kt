package com.carter.baselibrary.common

import android.content.ContentUris
import android.net.Uri

/**
 * 播放相关扩展方法
 */

/**
 * 通过id获取专辑图片uri
 */
fun albumById(albumId:Long):Uri{
    return ContentUris.withAppendedId(
        Uri.parse("content://media/external/audio/albumart"),
        albumId
    )
}