package com.example.time.presentation.common.util.effects

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

fun noRippleInteractionSource(): MutableInteractionSource = object : MutableInteractionSource {
    override val interactions: Flow<Interaction> = emptyFlow()
    override suspend fun emit(interaction: Interaction) {
        // No-op: this interaction source does not emit any interactions
    }
    override fun tryEmit(interaction: Interaction): Boolean = true
}