package com.iia.couplechat.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun CoupleChatAppBar(
    title: String,
    modifier: Modifier = Modifier,
    containerColor: Color = CoupleChatAppBarDefaults.containerColor,
    contentColor: Color = CoupleChatAppBarDefaults.contentColor,
    navigationIcon: ImageVector? = null,
    navigationIconClick: ()-> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = CoupleChatAppBarDefaults.contentColor
            )
        },
        backgroundColor = containerColor,
        contentColor = contentColor,
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(
                    onClick = navigationIconClick,
                    colors = IconButtonDefaults.iconButtonColors(contentColor = contentColor)
                ) {
                    Icon(imageVector = navigationIcon, "")
                }
            }
        },
        actions = actions,
        modifier = modifier
    )


}

@Preview
@Composable
fun CoupleChatAppBarPreview() {
    CoupleChatAppBar(title = "Profile")
}


object CoupleChatAppBarDefaults {
    val contentColor
        @Composable get() = MaterialTheme.colorScheme.onPrimary
    val containerColor
        @Composable get() = MaterialTheme.colorScheme.primary
}