package com.example.dev_agro.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_agro.ui.common.LabeledField
import com.example.dev_agro.ui.common.MyOutlinedTextField
import com.example.dev_agro.ui.common.OutlinedTextFieldsProps

@Composable
fun Profile() { /* reserved for VM + nav later */ }

private val GreenBg     = Color(0xFFF1F8E9)
private val Green200    = Color(0xFFC8E6C9)
private val Green300    = Color(0xFFA5D6A7)
private val Green400    = Color(0xFF81C784)
private val Green700    = Color(0xFF388E3C)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    onNext: (name: String, lastName: String, email: String, phone: String) -> Unit = { _, _, _, _ -> }
) {
    var name by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    val canContinue = name.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()
    val OffWhite = Color(0xFFF2F0EF)


    Scaffold(
        containerColor = GreenBg,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OffWhite,
                    titleContentColor = Color.Black,
                ),
                title = { Text("Profile", fontSize = 22.sp, fontWeight = FontWeight.Bold) },
                actions = {
                    TextButton(
                        enabled = canContinue,
                        onClick = { onNext(name, lastName, email, phone) }
                    ) { Text("Next", fontWeight = FontWeight.SemiBold, color = Green700, fontSize = 25.sp) }
                }
            )
        }
    ) { inner ->
        Column(
            Modifier
                .padding(inner)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color(0xFFF2F0EF))
                .padding(top = 40.dp)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Green200, Green300)
                        )
                    )
                    .padding(20.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    LabeledField(label = "Name") {
                        MyOutlinedTextField(OutlinedTextFieldsProps(value = name, onChange = { name = it }))
                    }
                    LabeledField(label = "Password") {
                        MyOutlinedTextField(OutlinedTextFieldsProps(value = lastName, onChange = { lastName = it }))
                    }
                    LabeledField(label = "Email") {
                        MyOutlinedTextField(OutlinedTextFieldsProps(value = email, onChange = { email = it }))
                    }
                    LabeledField(label = "Phone") {
                        MyOutlinedTextField(OutlinedTextFieldsProps(value = phone, onChange = { phone = it }))
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            Text(
                "We’ll use this info to personalize your experience.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF2E2E2E).copy(alpha = 0.7f)
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { onNext(name, lastName, email, phone) },
                enabled = canContinue,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Green200,
                    contentColor = Color.White,
                    disabledContainerColor = Green400.copy(alpha = 0.5f),
                    disabledContentColor = Color.White.copy(alpha = 0.7f)
                ),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Text("Continue", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileContentPreview() {
    ProfileContent()
}
