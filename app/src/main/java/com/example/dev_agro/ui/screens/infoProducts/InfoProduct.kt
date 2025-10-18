package com.example.dev_agro.ui.screens.infoProducts

import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dev_agro.R
import com.example.dev_agro.logic.InfoProductViewModel
import com.example.dev_agro.navigation.Screen
import com.example.dev_agro.ui.common.CarouselPhoto
import com.example.dev_agro.ui.common.LabeledField
import com.example.dev_agro.ui.common.MyOutlinedTextField
import com.example.dev_agro.ui.common.OutlinedTextFieldsProps
import com.example.dev_agro.ui.common.TwoUpUploadCarousel
import com.example.dev_agro.ui.screens.farm.SectionSpacer
import com.example.dev_agro.ui.theme.Dev_AgroTheme
import com.example.dev_agro.ui.theme.Green700
import com.example.dev_agro.ui.theme.Green900
import com.example.dev_agro.ui.theme.Grey
import com.example.dev_agro.ui.theme.OffWhite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable()
fun InfoProductSreen (navController: NavController, viewModel: InfoProductViewModel) {
    fun onNext(){
        navController.navigate(Screen.Dashboard.route)
    }
    InfoProductContent(
        onNext = {onNext()},
        onGenerateWithAI = { ctx, photos, setDescription ->
            viewModel.generateDescriptionFromFirstPhoto(
                context = ctx,
                photoUris = photos.map { it.uri },
                onResult = setDescription
            )
        },
        loadingFlow = viewModel.loading,
        toastFlow = viewModel.toast
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoProductContent(
    onNext: () -> Unit = {},
    onGenerateWithAI: (
        context: android.content.Context,
        photos: List<CarouselPhoto>,
        setDescription: (String) -> Unit) -> Unit = { _, _, _ -> },
    loadingFlow: StateFlow<Boolean>? = null,
    toastFlow: StateFlow<String?>? = null
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val loading by (loadingFlow ?: MutableStateFlow(false)).collectAsState(initial = false)
    val toast by (toastFlow ?: MutableStateFlow<String?>(null)).collectAsState(initial = null)

    LaunchedEffect(toast) {
        toast?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    var productName = remember { mutableStateOf("") }
    var description = remember { mutableStateOf("") }
    val photos = remember { mutableStateListOf<CarouselPhoto>() }
    val canContinue = description.value.isNotBlank()

    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) photos.add(0, CarouselPhoto(uri = uri))
    }
    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) photos.add(0, CarouselPhoto(uri = uri))
    }
    val launchPicker = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            photoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            getContent.launch("image/*")
        }
    }

    Scaffold(
        containerColor = OffWhite,
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = OffWhite,
                    titleContentColor = Color.Black
                ),
                title = {
                    Text(
                        stringResource(R.string.info_product),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                actions = {
                    TextButton(
                        onClick = { onNext() },
                        enabled = canContinue)
                    {
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
    ) { _ ->
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .background(OffWhite)
                .verticalScroll(rememberScrollState())
        ) {
            TwoUpUploadCarousel(
                photos = photos,
                onUploadClick = launchPicker,
                onPhotoClick = { /* preview */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )

            SectionSpacer()

            LabeledField(label = stringResource(R.string.product_name)) {
               MyOutlinedTextField(
                    OutlinedTextFieldsProps(
                        value = productName,
                        placeholder = stringResource(R.string.product_name),
                        modifier = Modifier.fillMaxWidth(),
                        variant = "TEXT"
                    ),
                   isPassword = false
               )
            }

            SectionSpacer()

            Text(
                stringResource(R.string.description),
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = Green900,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable(enabled = !loading) {
                        onGenerateWithAI(context, photos) { generated ->
                            description.value = generated
                        }
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.generate_by_ia),
                    fontWeight = FontWeight.SemiBold,
                    color = Grey,
                    modifier = Modifier.clickable(onClick = {})
                )
                Icon(
                    Icons.Rounded.ChevronRight,
                    contentDescription = null,
                    tint = Grey
                )
            }

            if (loading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }

            OutlinedTextField(
                value = description.value,
                onValueChange = { description.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .heightIn(min = 200.dp),
                singleLine = false,
                minLines = 8,
                shape = MaterialTheme.shapes.large
            )

            Spacer(Modifier.height(28.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoProductPreview() {
    Dev_AgroTheme {
        InfoProductContent()
    }
}