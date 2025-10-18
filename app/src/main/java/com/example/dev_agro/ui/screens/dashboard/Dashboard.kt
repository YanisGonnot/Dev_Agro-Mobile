package com.example.dev_agro.ui.screens.dashboard

import android.os.Build
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.dev_agro.R
import com.example.dev_agro.ui.common.TwoUpUploadCarousel
import com.example.dev_agro.ui.common.CarouselPhoto
import com.example.dev_agro.ui.screens.farm.SectionSpacer
import com.example.dev_agro.ui.theme.Dev_AgroTheme
import com.example.dev_agro.ui.theme.Green200
import com.example.dev_agro.ui.theme.Green400
import com.example.dev_agro.ui.theme.Green700
import com.example.dev_agro.ui.theme.OffWhite

@Composable fun DashboardScreen(
    navController: NavController
) {
/* reserved for VM + nav later */
    DashboardContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardContent(//navController: NavController
) {
    var description = remember { mutableStateOf("") }
    val photosFarm = remember { mutableStateListOf<CarouselPhoto>() }
    val photosProducts = remember { mutableStateListOf<CarouselPhoto>() }
    val isEditableDescription = remember { mutableStateOf(false) }
    var editableDescription = false

    val photoFarmPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) photosFarm.add(0, CarouselPhoto(uri = uri))
    }
    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) photosFarm.add(0, CarouselPhoto(uri = uri))
    }
    val launchPicker = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            photoFarmPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        } else {
            getContent.launch("image/*")
        }
    }

    fun editDescription(){ editableDescription = true}

    //fun editListProducts(){navController.navigate(Screen.Product.route)}

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
                        stringResource(R.string.my_farm),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = Green700
                    )
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
                photos = photosFarm,
                onUploadClick = launchPicker,
                onPhotoClick = { /* preview */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            )

            SectionSpacer()

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.description),
                    color = Green400
                )
                TextButton(
                    onClick = {editDescription()}
                ) {
                    Text(
                        stringResource(R.string.edit),
                        color = Green400
                    )
                }
            }

            OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .heightIn(min = 200.dp),
                    singleLine = false,
                    minLines = 5,
                    shape = MaterialTheme.shapes.large,
                    enabled = editableDescription
            )

            Spacer(Modifier.height(40.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.my_products),
                    color = Green400
                )
                TextButton(
                    onClick = {/*editListProducts()*/}
                ) {
                    Text(
                        stringResource(R.string.edit),
                        color = Green400
                    )
                }
            }
            LazyRow {
                items(items = photosProducts.toList()) {
                    ProductPhoto(it)
                }
            }
        }
    }
}

@Composable()
fun ProductPhoto(item: CarouselPhoto){
    Column {
        AsyncImage(
            model = item.uri,
            contentDescription = item.id.toString(),
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(16.dp))
                .height(50.dp)
                .width(50.dp),
            placeholder = painterResource(R.drawable.logo_team),
            error = BitmapPainter(ImageBitmap.imageResource(android.R.drawable.stat_notify_error))
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
private fun ProductPhotoPreview(){
    val ctx = LocalContext.current
    // Helper to build an android resource URI for previews
    fun resUri(resId: Int): Uri =
        "android.resource://${ctx.packageName}/$resId".toUri()

    val sample = listOf(
        CarouselPhoto(uri = resUri(android.R.drawable.ic_menu_gallery)),
        CarouselPhoto(uri = resUri(android.R.drawable.ic_menu_gallery)),
        CarouselPhoto(uri = resUri(android.R.drawable.ic_menu_gallery))
    )

    Dev_AgroTheme {
        ProductPhoto(
            sample.get(0)
        )
    }
}
*/

@Preview(showBackground = true)
@Composable
private fun DashboardContentPreview(){
    Dev_AgroTheme {
        DashboardContent()
    }
}