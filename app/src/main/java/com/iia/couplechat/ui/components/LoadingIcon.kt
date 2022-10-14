package com.iia.couplechat.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun LoadingIcon(
    loading: Boolean,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    strokeWidth: Dp = LoadingIconDefaults.StrokeWidth,
    color: Color = LoadingIconDefaults.Color
) {
    val stroke =
        with(LocalDensity.current) { Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square) }
    val topLeft =
        with(LocalDensity.current) { Offset(x = strokeWidth.toPx(), y = strokeWidth.toPx()) }

    val transition = rememberInfiniteTransition()
    val startAngle = transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing)
        )
    )

    if (loading) {
        Canvas(
            modifier = modifier
                .width(LoadingIconDefaults.Size)
                .height(LoadingIconDefaults.Size)
        ) {
            drawCircle(
                color = color.copy(alpha = .6f),
                radius = size.minDimension / 2 - strokeWidth.toPx(),
                style = stroke
            )
            drawArc(
                color = color,
                startAngle = startAngle.value,
                sweepAngle = 90f,
                useCenter = false,
                topLeft = topLeft,
                style = stroke,
                size = Size(
                    width = size.width - 2 * strokeWidth.toPx(),
                    height = size.height - 2 * strokeWidth.toPx()
                )
            )
        }
    } else {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = color,
            modifier = modifier
        )
    }
}


@Preview
@Composable
fun LoadingIconPreview() {
    LoadingIcon(
        loading = true,
        imageVector = Icons.Default.Done,
    )
}

object LoadingIconDefaults {
    val Size = 28.dp
    val StrokeWidth = 3.dp
    val Color @Composable get() = MaterialTheme.colorScheme.onPrimary
}