package com.teewhydope

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.teewhydope.app.di.Graph
import com.teewhydope.app.ui.AppTheme
import com.teewhydope.app.ui.widgets.LoadingScreen
import com.teewhydope.contact.presentation.model.ContactListNotification.ContactSelected
import com.teewhydope.contact.presentation.model.ContactListViewState.Loading
import com.teewhydope.contact.ui.model.ContactListUiModel
import com.teewhydope.contact.ui.model.ContactListUiModel.AllContacts
import com.teewhydope.contact.ui.model.ContactListUiModel.Empty
import com.teewhydope.contact.ui.model.ContactListUiModel.Error
import com.teewhydope.contact.ui.model.ContactListUiModel.RecentContacts
import com.teewhydope.contact.ui.screens.ContactListScreen
import org.lighthousegames.logging.logging

val logger = logging()

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    AppTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
    ) {
        val viewModel = Graph.contactViewModel
        val viewState = viewModel.viewState.collectAsState(Loading)
        val notification = viewModel.notification.collectAsState(initial = null)
        val contactListPresentationToUiMapper = Graph.contactListPresentationToUiMapper
        val contactUiToPresentationMapper = Graph.contactUiToPresentationMapper

        LaunchedEffect(key1 = null) {
            viewModel.onEnter()
            viewModel.onSelectContact(null, -1)
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column {
                when (
                    val viewStateValue =
                        contactListPresentationToUiMapper.toUi(viewState.value)
                ) {
                    is ContactListUiModel.Loading -> {
                        LoadingScreen()
                    }

                    is RecentContacts -> {}

                    is AllContacts -> {
                        ContactListScreen(
                            contacts = viewStateValue.contacts.sortedBy { it.id },
                            onSelectContact = { contact, id ->
                                viewModel.onSelectContact(
                                    contactUiToPresentationMapper.toPresentation(contact),
                                    id,
                                )
                                when (val notificationValue = notification.value) {
                                    null -> Unit
                                    is ContactSelected -> {}
                                    else -> {}
                                }
                            },
                            onDelete = { id -> viewModel.onDeleteContact(id) },
                        )
                    }

                    is Empty -> {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                "No contacts",
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }

                    is Error -> {
                        Text("Error")
                    }
                }
            }
        }
    }
}
