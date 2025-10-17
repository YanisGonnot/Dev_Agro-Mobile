package com.example.dev_agro.ui.common

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FileUpload
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import android.net.Uri
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri
import com.example.dev_agro.ui.theme.GreyLight
import com.example.dev_agro.ui.theme.Grey800


data class CarouselPhoto(
    val id: Long = System.currentTimeMillis(),
    val uri: android.net.Uri
)

private val TileBg = GreyLight
private val TileShape = RoundedCornerShape(20.dp)
private val TileSize  = 140.dp

@JvmOverloads
@Composable
fun TwoUpUploadCarousel(
    photos: List<CarouselPhoto>,
    modifier: Modifier = Modifier,
    onUploadClick: () -> Unit,
    onPhotoClick: (CarouselPhoto) -> Unit = {}
) {
    val ordered: List<Tile> = when {
        photos.isEmpty() -> listOf(Tile.Upload)
        else -> listOf(Tile.Photo(photos.first()), Tile.Upload) +
                photos.drop(1).map(Tile::Photo)
    }

    val pairs = ordered.chunked(2)

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pairs) { pair ->
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                TileComposable(pair[0], onUploadClick, onPhotoClick)
                if (pair.size > 1) {
                    TileComposable(pair[1], onUploadClick, onPhotoClick)
                } else {
                    Spacer(Modifier.size(TileSize))
                }
            }
        }
    }
}

private sealed class Tile {
    data object Upload : Tile()
    data class Photo(val item: CarouselPhoto) : Tile()
}

@Composable
private fun TileComposable(
    tile: Tile,
    onUploadClick: () -> Unit,
    onPhotoClick: (CarouselPhoto) -> Unit
) {
    when (tile) {
        Tile.Upload -> Box(
            modifier = Modifier
                .size(TileSize)
                .clip(TileShape)
                .background(TileBg)
                .clickable { onUploadClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.FileUpload,
                contentDescription = "Upload",
                tint = Grey800
            )
        }

        is Tile.Photo -> Box(
            modifier = Modifier
                .size(TileSize)
                .clip(TileShape)
                .clickable { onPhotoClick(tile.item) }
        ) {
            AsyncImage(
                model = tile.item.uri,
                contentDescription = "Photo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun PhotosSection() {
    val photos = remember { mutableStateListOf<CarouselPhoto>() }

    // Modern photo picker (no permission needed). Works on Android 13+, and
    // is backported on many devices via Google Play services.
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            // Put newest first so it appears before the Upload tile
            photos.add(0, CarouselPhoto(uri = uri))
        }
    }

    // Fallback to GetContent (also no storage permission)
    val getContent = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) photos.add(0, CarouselPhoto(uri = uri))
    }

    val launchPicker = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            photoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        } else {
            getContent.launch("image/*")
        }
    }

    TwoUpUploadCarousel(
        photos = photos,
        onUploadClick = launchPicker,
        onPhotoClick = { /* open full screen, delete, etc. */ }
    )
}

@Preview(showBackground = true)
@Composable
private fun TwoUpUploadCarousel_Preview() {
    val ctx = LocalContext.current
    // Helper to build an android resource URI for previews
    fun resUri(resId: Int): Uri =
        "android.resource://${ctx.packageName}/$resId".toUri()

    val sample = listOf(
        CarouselPhoto(uri = resUri(android.R.drawable.ic_menu_gallery)),
        CarouselPhoto(uri = resUri(android.R.drawable.ic_menu_gallery)),
        CarouselPhoto(uri = resUri(android.R.drawable.ic_menu_gallery))
    )

    // Preview with photos (Upload tile will appear second)
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        TwoUpUploadCarousel(
            photos = sample,
            onUploadClick = {},       // no-op in preview
            onPhotoClick = {}
        )
        Spacer(Modifier.height(24.dp))
        // Preview with NO photos (only Upload tile)
        TwoUpUploadCarousel(
            photos = emptyList(),
            onUploadClick = {},
            onPhotoClick = {}
        )
    }
}
