package com.teewhydope.contact.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.teewhydope.contact.ui.model.ContactUiModel

@Composable
fun ContactListItem(
    contact: ContactUiModel,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ContactPhoto(
            contact = contact,
            modifier = Modifier.size(50.dp),
        )

        Spacer(Modifier.width(16.dp))

        Text(
            text = "${contact.firstName} ${contact.lastName}",
            modifier = Modifier.weight(1f),
        )
        Icon(
            imageVector = Icons.Rounded.Delete,
            contentDescription = "",
            modifier = Modifier.clickable { onDelete() },
        )
    }
}
