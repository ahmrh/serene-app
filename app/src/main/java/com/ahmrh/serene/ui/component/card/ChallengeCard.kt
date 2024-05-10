package com.ahmrh.serene.ui.component.card

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.ui.theme.SereneTheme

@Composable
fun ChallengeCard(
    modifier: Modifier = Modifier,
    value: Long = 1,
    maxValue: Long = 10,
    isDone: Boolean = false,
    title: String = "For your mental",
    description: String = "Do 10 mental Self-care today"
) {

    Card(
        modifier = modifier
            .clip(
                RoundedCornerShape(12.dp)
            ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                2.dp
            ),

            ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(
                    description, style = MaterialTheme.typography.bodySmall
                )
            }

            ChallengeProgressIndicator(
                value = value,
                maxValue = maxValue,
                isDone = isDone
            )
        }
    }


}

@Composable
fun ChallengeProgressIndicator(
    value: Long = 10,
    maxValue: Long = 10,
    isDone: Boolean = false
) {

//    val sweepAngle = value / maxValue.toFloat() * 360
//    Box(
//        contentAlignment = Alignment.Center
//    ) {
//        Indicator(
//            sweepAngle = sweepAngle,
//        )
//
//        Text("$value/$maxValue", style = MaterialTheme.typography.titleSmall)
//    }

    val sweepAngle = 1F * 360
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Indicator(
            sweepAngle = sweepAngle,

            color = if (isDone) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.outlineVariant,
            size = 40.dp
        )
        Icon(
            Icons.Default.Check, contentDescription = null,
            tint = if (isDone) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier.size(30.dp)
        )

    }

}

@Composable
fun Indicator(
    size: Dp = 48.dp, // indicator size
    sweepAngle: Float = 180f, // angle (lenght) of indicator arc
    color: Color = MaterialTheme.colorScheme.primary, // color of indicator arc line
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth, //width of cicle and ar lines
) {

    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }
    Canvas(
        Modifier
            .progressSemantics() // (optional) for Accessibility services
            .size(size) // canvas size

            .padding(
                strokeWidth / 4
            ) //padding. otherwise, not the whole circle will fit in the canvas
    ) {
        drawArc(
            color,
            startAngle = -90f,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChallengeCardPreview() {
    SereneTheme {
        ChallengeCard()
    }

}