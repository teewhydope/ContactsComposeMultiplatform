package com.teewhydope.app.ui.widgets

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teewhydope.app.ui.util.rememberForeverScrollState

@Composable
fun CLazyRow(
    key: String,
    modifier: Modifier,
    contentPadding: PaddingValues,
    horizontalArrangement: Arrangement.Horizontal,
    content: LazyListScope.() -> Unit,
) {
    val scrollState = rememberForeverScrollState(key)

    Row(
        modifier = Modifier
            .horizontalScroll(scrollState),
    ) {
        LazyRow(
            modifier = modifier,
            content = content,
            contentPadding = contentPadding,
            horizontalArrangement = horizontalArrangement,

        )
    }
}
