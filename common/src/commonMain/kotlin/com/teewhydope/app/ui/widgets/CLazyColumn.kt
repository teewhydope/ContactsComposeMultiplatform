package com.teewhydope.app.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.teewhydope.app.ui.util.rememberForeverScrollState

@Composable
fun CLazyColumn(
    key: String,
    modifier: Modifier,
    contentPadding: PaddingValues,
    verticalArrangement: Arrangement.Vertical,
    content: LazyListScope.() -> Unit,
) {
    val scrollState = rememberForeverScrollState(key)
    Column(
        modifier = Modifier
            .verticalScroll(scrollState),
    ) {
        LazyColumn(
            modifier = modifier,
            content = content,
            contentPadding = contentPadding,
            verticalArrangement = verticalArrangement,

        )
    }
}
