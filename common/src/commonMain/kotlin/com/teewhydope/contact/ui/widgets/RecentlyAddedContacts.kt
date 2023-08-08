package com.teewhydope.contact.ui.widgets

import ContactPreviewItem
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.teewhydope.contact.ui.model.ContactUiModel

@Composable
fun RecentlyAddedContacts(
    contacts: List<ContactUiModel>,
    onClick: (ContactUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        if (contacts.isNotEmpty()) {
            Text(
                text = "Recently Added",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(16.dp))
        }
        LazyRow(
            modifier = Modifier,
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(contacts) { contact ->
                ContactPreviewItem(
                    contact = contact,
                    onClick = { onClick(contact) },
                )
            }
        }
    }
}
