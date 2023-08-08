package com.teewhydope.contact.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.teewhydope.app.di.Graph
import com.teewhydope.app.navigation.mapper.ContactDetailsDestinationToUiMapper
import com.teewhydope.architecture.ui.navigation.BackHandler

@Composable
fun ContactDetailScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        val viewModel = Graph.contactDetailsViewModel

        val navigation = viewModel.destination.collectAsState(initial = null)

        val navigationMapper: ContactDetailsDestinationToUiMapper =
            Graph.contactDetailsDestinationToUiMapper

        navigation.value?.apply {
            val uiDestination = navigationMapper.toUi(this)
            LaunchedEffect(navigation) {
                uiDestination.navigate()
            }
        }

        BackHandler(isEnabled = true) {
            viewModel.onBackAction()
        }

        Column {
            Text("ContactDetailScreen")
        }
    }
}
