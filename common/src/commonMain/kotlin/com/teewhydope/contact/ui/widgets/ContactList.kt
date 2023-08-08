package com.teewhydope.contact.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonAdd
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.teewhydope.contact.ui.model.ContactListUiModel
import com.teewhydope.contact.ui.model.ContactListUiModel.Contacts
import com.teewhydope.contact.ui.model.ContactUiModel

@Composable
fun ContactList(
    uiModel: ContactListUiModel,
    onSelectContact: (ContactUiModel, Int) -> Unit,
    onDelete: (Long) -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                shape = RoundedCornerShape(20.dp),
            ) {
                Icon(
                    imageVector = Icons.Rounded.PersonAdd,
                    contentDescription = "Add Contact",
                )
            }
        },
    ) {
        LazyColumn(
            modifier = Modifier,
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            when (uiModel) {
                is Contacts -> {
                    item {
                        RecentlyAddedContacts(
                            contacts = uiModel.recentContacts,
                            onClick = {},
                        )
                    }
                    item {
                        Text(
                            text = "My contacts (${uiModel.allContacts.size})",
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                            fontWeight = FontWeight.Bold,
                        )
                    }
                    itemsIndexed(uiModel.allContacts) { index, contact ->
                        ContactListItem(
                            contact = contact,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelectContact(contact, index)
                                }
                                .padding(horizontal = 16.dp),
                            onDelete = { onDelete(contact.id) },
                        )
                    }
                }

                else -> {}
            }
        }
    }
}
