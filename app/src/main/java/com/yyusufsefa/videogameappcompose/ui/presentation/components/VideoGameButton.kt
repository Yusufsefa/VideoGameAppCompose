package com.yyusufsefa.videogameappcompose.ui.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yyusufsefa.videogameappcompose.R

@Composable
fun MovieButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.bg_on_boarding_button_color),
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        Text(
            modifier = Modifier.padding(4.dp),
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}