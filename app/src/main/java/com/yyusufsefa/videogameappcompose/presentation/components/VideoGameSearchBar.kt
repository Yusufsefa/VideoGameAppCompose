package com.yyusufsefa.videogameappcompose.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yyusufsefa.videogameappcompose.R
import com.yyusufsefa.videogameappcompose.ui.theme.ColorHintText
import com.yyusufsefa.videogameappcompose.ui.theme.ColorTintSearchBar
import com.yyusufsefa.videogameappcompose.ui.theme.Dimens
import com.yyusufsefa.videogameappcompose.ui.theme.VideoGameAppComposeTheme

@Composable
fun VideoGameSearchBar(
    modifier: Modifier = Modifier,
    hint: String,
    isEnabled: Boolean = true,
    height: Dp = Dimens.SearchBarSize,
    elevation: Dp = Dimens.MarginXXS,
    cornerShape: Shape = RoundedCornerShape(Dimens.MarginS),
    backgroundColor: Int = R.color.bg_search_bar,
    searchQuery: String = "",
    onSearchClicked: () -> Unit = {},
    onTextChange: (String) -> Unit = {},
) {
    var text by remember { mutableStateOf(searchQuery) }

    Row(
        modifier = modifier
            .height(height)
            .fillMaxWidth()
            .shadow(elevation = elevation, shape = cornerShape)
            .background(color = colorResource(id = backgroundColor), shape = cornerShape)
            .padding(start = Dimens.MarginM, end = Dimens.MarginXXS),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onTextChange(it)
            },
            enabled = isEnabled,
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            ),
            decorationBox = { innerTextField ->
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        color = ColorHintText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                innerTextField()
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked() }),
            singleLine = true,
            cursorBrush = SolidColor(ColorTintSearchBar),
            modifier = Modifier
                .weight(1f)
                .clickable {
                    if (text.isEmpty()) {
                        onSearchClicked()
                    }
                }
        )
        if (text.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .size(Dimens.SearchBarSize)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .clickable {
                        text = ""
                        onTextChange("")
                    },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clear),
                    contentDescription = null,
                    tint = ColorTintSearchBar,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimens.IconPadding)
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .size(Dimens.SearchBarSize)
                    .background(color = Color.Transparent, shape = CircleShape)
                    .clickable {
                        onSearchClicked()
                    },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                    tint = ColorTintSearchBar,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimens.MarginXXS)
                )
            }
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    VideoGameAppComposeTheme {
        VideoGameSearchBar(hint = "SEARCH")
    }
}
