package com.example.dev_agro.ui.screens.farm

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dev_agro.R
import com.example.dev_agro.logic.FarmViewModel
import com.example.dev_agro.ui.common.MyOutlinedTextField
import com.example.dev_agro.ui.common.OutlinedTextFieldsProps
import com.example.dev_agro.ui.common.TwoUpUploadCarousel
import com.example.dev_agro.ui.common.CarouselPhoto
import com.example.dev_agro.ui.theme.Green700
import com.example.dev_agro.ui.theme.Green900
import com.example.dev_agro.ui.theme.Grey
import com.example.dev_agro.ui.theme.OffWhite
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.dev_agro.navigation.Screen

@Composable fun FarmScreen(navController:NavController) {

    val vm: FarmViewModel = androidx.hilt.navigation.compose.hiltViewModel()

    FarmContent(
        onNext = { location, description, photos ->
            // TODO: call your backend to create Farm, then navigate
            navController.navigate(Screen.ProductInfo.route)
        },
        onGenerateWithAI = { ctx, photos, setDescription ->
            vm.generateDescriptionFromFirstPhoto(
                context = ctx,
                photoUris = photos.map { it.uri },
                onResult = setDescription
            )
        },
        loadingFlow = vm.loading,
        toastFlow = vm.toast
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FarmContent(
    onNext: (location: String, description: String, photos: List<CarouselPhoto>) -> Unit = { _, _, _ -> },
    onGenerateWithAI: (context: android.content.Context, photos: List<CarouselPhoto>, setDescription: (String) -> Unit) -> Unit = { _, _, _ -> },
    loadingFlow: StateFlow<Boolean>? = null,
    toastFlow: StateFlow<String?>? = null
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val loading by (loadingFlow ?: MutableStateFlow(false)).collectAsState(initial = false)
    val toast by (toastFlow ?: MutableStateFlow<String?>(null)).collectAsState(initial = null)

    val location = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val photos = remember { mutableStateListOf<CarouselPhoto>() }
    val canContinue = location.value.isNotBlank() && description.value.isNotBlank()

    // ✅ Restore the pickers
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: android.net.Uri? ->
        if (uri != null) photos.add(0, CarouselPhoto(uri = uri))
    }

    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: android.net.Uri? ->
        if (uri != null) photos.add(0, CarouselPhoto(uri = uri))
    }

    val launchPicker = remember {
        {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                photoPicker.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            } else {
                getContent.launch("image/*")
            }
        }
    }

    LaunchedEffect(toast) {
        toast?.let {
            android.widget.Toast.makeText(context, it, android.widget.Toast.LENGTH_SHORT).show()
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
                        text = stringResource(R.string.my_farm),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    TextButton(
                        onClick = { onNext(location.value, description.value, photos) },
                        enabled = canContinue && !loading
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
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .background(OffWhite)
                .verticalScroll(rememberScrollState())
        ) {
            // ✅ Upload tile now launches the picker
            TwoUpUploadCarousel(
                photos = photos,
                onUploadClick = launchPicker,
                onPhotoClick = { /* preview / delete if you want */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )

            SectionSpacer()

            LabeledField(label = stringResource(R.string.location)) {
                MyOutlinedTextField(
                    OutlinedTextFieldsProps(
                        value = location,
                        placeholder = stringResource(R.string.location),
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
                modifier = Modifier.padding(horizontal = 20.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
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
                    color = Grey
                )
                Icon(Icons.Rounded.ChevronRight, contentDescription = null, tint = Grey)
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



@Composable
private fun LabeledField(label: String, content: @Composable () -> Unit) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp)) {
        Text(label, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp, color = Green900)
        Spacer(Modifier.height(8.dp))
        content()
    }
}

@Composable
fun SectionSpacer() = Spacer(Modifier.height(40.dp))

@Preview(showBackground = true)
@Composable fun FarmPreview() { FarmContent() }
