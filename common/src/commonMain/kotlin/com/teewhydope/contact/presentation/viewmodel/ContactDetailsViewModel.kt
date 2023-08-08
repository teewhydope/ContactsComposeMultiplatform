package com.teewhydope.contact.presentation.viewmodel

import com.teewhydope.architecture.domain.UseCaseExecutor
import com.teewhydope.architecture.presentation.model.NoNotification
import com.teewhydope.architecture.presentation.navigation.PresentationDestination
import com.teewhydope.architecture.presentation.viewmodel.BaseViewModel
import com.teewhydope.contact.presentation.model.ContactDetailsViewState

class ContactDetailsViewModel(
    useCaseExecutor: UseCaseExecutor,
) : BaseViewModel<ContactDetailsViewState, NoNotification>(useCaseExecutor) {
    override val initialViewState = ContactDetailsViewState.Loading

    fun onBackAction() {
        navigate(PresentationDestination.Back)
    }
}
