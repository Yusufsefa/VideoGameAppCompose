package com.yyusufsefa.videogameappcompose.presentation.navigation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yyusufsefa.videogameappcompose.R
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme

@Composable
fun VideoGameBottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = modifier
            .padding(vertical = 8.dp)
            .height(56.dp)
            .fillMaxWidth(),
        containerColor = colorResource(R.color.bg_bottom_menu_bar),
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon), contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(text = item.title)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = colorResource(R.color.bg_bottom_menu_bar)
                ),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }
}


data class BottomNavItem(
    @DrawableRes val icon: Int,
    val title: String
)

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MovieBottomNavigationPreview() {
    VideoGameAppComposeTheme {
        VideoGameBottomNavigationBar(items = listOf(
            BottomNavItem(icon = R.drawable.ic_home, title = "Home"),
            BottomNavItem(icon = R.drawable.ic_search_bottom_menu_bar, title = "Search"),
            BottomNavItem(icon = R.drawable.ic_favorite_border, title = "Favorite"),
        ), selectedItem = 1, onItemClick = {})
    }
}
