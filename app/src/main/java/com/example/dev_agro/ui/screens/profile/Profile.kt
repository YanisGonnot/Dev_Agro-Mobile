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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_agro.R
import com.example.dev_agro.ui.common.LabeledField
import com.example.dev_agro.ui.common.MyOutlinedTextField
import com.example.dev_agro.ui.common.OutlinedTextFieldsProps
import com.example.dev_agro.ui.theme.GreyDark
import com.example.dev_agro.ui.theme.Green200
import com.example.dev_agro.ui.theme.Green300
import com.example.dev_agro.ui.theme.Green400
import com.example.dev_agro.ui.theme.Green700
import com.example.dev_agro.ui.theme.GreenBg
import com.example.dev_agro.ui.theme.OffWhite

@Composable
fun Profile() { /* reserved for VM + nav later */ }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    onNext: (name: String, lastName: String, email: String, phone: String) -> Unit = { _, _, _, _ -> }
) {
    var name = remember { mutableStateOf("") }
    var lastName = remember { mutableStateOf("") }
    var email = remember { mutableStateOf("") }
    var phone = remember { mutableStateOf("") }
    val canContinue = name.value.isNotBlank() && lastName.value.isNotBlank() && email.value.isNotBlank()

    Scaffold(
        containerColor = GreenBg,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OffWhite,
                    titleContentColor = Color.Black,
                ),
                title = {
                    Text(
                    stringResource(R.string.profile_title),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    TextButton(
                        enabled = canContinue,
                        onClick = { onNext(name.value, lastName.value, email.value, phone.value) }
                    ) {
                        Text(
                            stringResource(R.string.nextstep),
                            fontWeight = FontWeight.SemiBold,
                            color = Green700,
                            fontSize = 25.sp
                        )
                    }
                }
            )
        }
    ) { inner ->
        Column(
            Modifier
                .padding(inner)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
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
                    LabeledField(label = stringResource(R.string.firstname)) {
                        MyOutlinedTextField(
                            OutlinedTextFieldsProps(
                                value = name,
                                variant = "TEXT",
                                placeholder = stringResource(R.string.firstname),
                                modifier = Modifier,
                            )
                        )
                    }
                    LabeledField(label = stringResource(R.string.lastname)) {
                        MyOutlinedTextField(
                            OutlinedTextFieldsProps(
                                value = name,
                                variant = "TEXT",
                                placeholder = stringResource(R.string.lastname),
                                modifier = Modifier,
                            )
                        )
                    }
                    LabeledField(label = stringResource(R.string.email)) {
                        MyOutlinedTextField(
                            OutlinedTextFieldsProps(
                                value = name,
                                variant = "TEXT",
                                placeholder = stringResource(R.string.email),
                                modifier = Modifier,
                            )
                        )
                    }
                    LabeledField(label = stringResource(R.string.phone)) {
                        MyOutlinedTextField(
                            OutlinedTextFieldsProps(
                                value = name,
                                variant = "TEXT",
                                placeholder = stringResource(R.string.phone),
                                modifier = Modifier,
                            )
                        )
                    }
                }
            }

            Spacer(Modifier.height(18.dp))

            Text(
                stringResource(R.string.personalize_experience),
                style = MaterialTheme.typography.bodyMedium,
                color = GreyDark.copy(alpha = 0.7f)
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { onNext(name.value, lastName.value, email.value, phone.value) },
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
                Text(
                    stringResource(R.string.continue_step),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
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
