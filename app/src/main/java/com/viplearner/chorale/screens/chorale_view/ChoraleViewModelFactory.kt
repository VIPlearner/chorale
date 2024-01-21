package com.viplearner.chorale.screens.chorale_view

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun choraleViewModelProvider(
    factory: ChoraleViewModel.Factory,
    choraleId: String,
): ChoraleViewModel {
    return viewModel(factory = ChoraleViewModel.provideFactory(
        factory,
        choraleId,
    ))
}