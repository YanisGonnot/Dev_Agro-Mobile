package com.example.dev_agro.ui.screens.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dev_agro.R
import com.example.dev_agro.navigation.Screen
import com.example.dev_agro.ui.common.MyTitle
import com.example.dev_agro.ui.common.StepButton
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.dev_agro.dto.ProductDto
import com.example.dev_agro.logic.ProductsViewModel
import com.example.dev_agro.ui.theme.Dev_AgroTheme
import com.example.dev_agro.utils.monToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductsScreen(navController: NavController, viewModel: ProductsViewModel){
    val context = LocalContext.current
    val takePhoto = {}
    val listProducts = ArrayList<ProductDto>()
    //val listProducts by viewModel
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.messageToShowSharedFloat.collect{
            context.monToast(it)
        }
    }

    ProductsContent(
        listProducts = listProducts,
        navigateToNextStep = {
            navController.navigate((Screen.ProductInfo.route))
        },
        isRefreshing = isRefreshing,
        onRefresh = {
            viewModel.refresh(listProducts)
        },
        takePhoto = takePhoto,
    )
}


@ExperimentalMaterial3Api
@Composable
fun ProductsContent(
    listProducts : MutableList<ProductDto>,
    navigateToNextStep : () -> Unit,
    isRefreshing: Boolean,
    onRefresh : () -> Unit,
    takePhoto: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 40.dp), // laisse un peu d'espace en bas pour l'icône
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 40.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MyTitle(
                    idText = R.string.my_products,
                    modifier = Modifier
                )

                StepButton(
                    navigateToNextStep = navigateToNextStep,
                    text = R.string.nextstep,
                    modifierText = Modifier,
                    modifierTextButton = Modifier,
                )
            }

            Spacer(modifier = Modifier.padding(vertical = 30.dp))

            //CAROUSSEL
        }

        Icon(
            imageVector = Icons.Filled.AddCircle,
            contentDescription = stringResource(R.string.add_photo),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 20.dp)
                .clickable { takePhoto() }
                .size(60.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun ListProductsPreview(){
    Dev_AgroTheme {
        val fakeProducts = MutableList(6) { index ->
            ProductDto(
                idPhoto = index.toLong(),
                titre = "Produit $index",
                description = "Ceci est la description du produit $index",
                urlImg = "https://via.placeholder.com/150", // image factice
                createdAt = "2025-10-16",
                idUser = 1L
            )
        }
        ProductsContent(
            listProducts = fakeProducts,
            navigateToNextStep = {},
            isRefreshing = false,
            onRefresh = {},
            takePhoto = {  }
        )
    }
}
