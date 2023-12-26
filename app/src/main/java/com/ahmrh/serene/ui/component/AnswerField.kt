package com.ahmrh.serene.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ahmrh.serene.ui.theme.SereneTheme


@Composable 
fun AnswerField(
    selected: Boolean = false
){


//    OutlinedTextField(
//        value = "Answer",
//        onValueChange = {},
//        trailingIcon = {
//            RadioButton(
//                selected = selected, onClick = {  }
//            )
//
//        },
//        readOnly = true,
//        colors = TextFieldDefaults.colors(
//            disabledTextColor = MaterialTheme.colorScheme.onSurface,
//            disabledLabelColor = MaterialTheme.colorScheme.onSurface,
//        ),
//    )

//    Row(
//        Modifier
//            .padding(horizontal = 16.dp, vertical = 4.dp)
//            .size(width = 210.dp, height = 56.dp)
//            .clip(),
//        verticalAlignment = Alignment.CenterVertically
//    ){
//        Text("Answer", modifier = Modifier.weight(1f))
//
//    }


}

@Preview(showBackground = true)
@Composable 
fun AnswerFieldPreview(){
    SereneTheme{
        AnswerField()
    }
}