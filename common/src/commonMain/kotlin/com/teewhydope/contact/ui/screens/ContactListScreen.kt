package com.teewhydope.contact.ui.screens

import androidx.compose.foundation.layout.Box
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
import com.teewhydope.app.navigation.mapper.ContactHomeDestinationToUiMapper
import com.teewhydope.app.ui.widgets.LoadingScreen
import com.teewhydope.contact.presentation.model.ContactListViewState
import com.teewhydope.contact.ui.model.ContactListUiModel.Empty
import com.teewhydope.contact.ui.model.ContactListUiModel.Error
import com.teewhydope.contact.ui.model.ContactListUiModel.Loading
import com.teewhydope.contact.ui.widgets.ContactList

@Composable
fun ContactListScreen() {
    val viewModel = Graph.contactViewModel
    val viewState = viewModel.viewState.collectAsState(ContactListViewState.Loading)
    val navigation = viewModel.destination.collectAsState(initial = null)

    val contactListPresentationToUiMapper = Graph.contactListPresentationToUiMapper
    val navigationMapper: ContactHomeDestinationToUiMapper = Graph.contactHomeDestinationToUiMapper

    LaunchedEffect(key1 = null) {
        viewModel.onEnter()
        viewModel.onSelectContact(null, -1)
    }

    navigation.value?.apply {
        val uiDestination = navigationMapper.toUi(this)
        LaunchedEffect(navigation) {
            uiDestination.navigate()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        when (
            val viewStateValue =
                contactListPresentationToUiMapper.toUi(viewState.value)
        ) {
            is Loading -> {
                LoadingScreen()
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

            else -> {
                ContactList(
                    uiModel = viewStateValue,
                    onSelectContact = { contact, id ->
                        viewModel.onSelectContact(null, contact.id.toInt())
                        viewModel.onEditContactAction()
                    },
                    onDelete = { id -> viewModel.onDeleteContact(id) },
                )
            }
        }
    }
}
