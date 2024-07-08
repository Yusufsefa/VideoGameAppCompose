package com.yyusufsefa.videogameappcompose.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yyusufsefa.videogameappcompose.ui.theme.ColorOnBoardingButtonColor
import com.yyusufsefa.videogameappcompose.ui.theme.Dimens

@Composable
fun VideoGameButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorOnBoardingButtonColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = Dimens.MarginS)
    ) {
        Text(
            modifier = Modifier.padding(Dimens.MarginXXS),
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}