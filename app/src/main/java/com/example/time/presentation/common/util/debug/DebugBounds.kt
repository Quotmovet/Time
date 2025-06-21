package com.example.time.presentation.common.util.debug

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.dp

fun Modifier.debugBounds(
    color: Color = Color.Red,
    strokeWidth: Float = 1.dp.value
): Modifier = this.drawBehind {
    drawIntoCanvas { canvas ->
        val paint = Paint().apply {
            this.color = color
            this.style = androidx.compose.ui.graphics.PaintingStyle.Stroke
            this.strokeWidth = strokeWidth
        }
        canvas.drawRect(size.toRect(), paint)
    }
}