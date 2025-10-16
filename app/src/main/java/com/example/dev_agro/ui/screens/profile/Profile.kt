package com.example.dev_agro.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable()
fun Profile() {

}

@Composable()
fun ProfileContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween

        ){
            Text(text = "Profile", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp)
            Text(text = "Next", fontWeight = FontWeight.ExtraBold, fontSize = 30.sp, textAlign = TextAlign.End)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedCard(modifier = Modifier
                .size(320.dp,460.dp),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = Color(0xFFD9D9D9) // your color here
                )
            ) {
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Name", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        shape = RoundedCornerShape(15.dp),
                        colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color(0xFFACACAC) )
                    )
                    Text(text = "Last name", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        shape = RoundedCornerShape(15.dp),
                        colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color(0xFFACACAC) )
                    )
                    Text(text = "Email", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        shape = RoundedCornerShape(15.dp),
                        colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color(0xFFACACAC) )
                    )
                    Text(text = "Last name", fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        shape = RoundedCornerShape(15.dp),
                        colors = OutlinedTextFieldDefaults.colors( unfocusedContainerColor = Color(0xFFACACAC) )
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ProfilePreviewPreview(){
    ProfileContent()
}
