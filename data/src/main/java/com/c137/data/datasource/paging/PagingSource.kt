package com.c137.data.datasource.paging

import android.net.Uri

fun String.pageFromUrl(): Int? = Uri.parse(this).getQueryParameter("page")?.toIntOrNull()