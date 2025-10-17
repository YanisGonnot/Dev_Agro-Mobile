package com.example.dev_agro.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dev_agro.R

private val AppPrimary = Color(0xFF204D34)

data class OnBoardingCard(
    val title: String,
    val underTitle: String,
    val drawable: Int
)

@Composable
fun OnBoarding(
    onFinish: () -> Unit
) {
    val cards = remember {
        listOf(
            OnBoardingCard(
                title = "Créez automatiquement de belles fiches produits.",
                underTitle = "Mettez en avant vos animaux les plus sains et productifs.",
                drawable = R.drawable.dashboard_products
            ),
            OnBoardingCard(
                title = "Prenez une photo de vos animaux ou produits.",
                underTitle = "Descriptions, prix et présentation générés par l’IA.",
                drawable = R.drawable.dashboard_ia
            ),
            OnBoardingCard(
                title = "Faites grandir votre ferme et vos ventes.",
                underTitle = "Gagnez en visibilité et touchez plus de clients.",
                drawable = R.drawable.dashboard_stonks
            )
        )
    }

    var index by rememberSaveable { mutableIntStateOf(0) }
    val isLast = index == cards.lastIndex

    OnBoardingContent(
        card = cards[index],
        index = index,
        total = cards.size,
        onNext = {
            if (isLast) onFinish() else index++
        },
    )
}

@Composable
private fun OnBoardingContent(
    card: OnBoardingCard,
    index: Int,
    total: Int,
    onNext: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = 90.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = card.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = card.underTitle,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF8A8A8A),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Image(
            painter = painterResource(id = card.drawable),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 220.dp, max = 360.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(Modifier.height(16.dp))

        DotsIndicator(
            selectedIndex = index,
            count = total,
            activeColor = AppPrimary
        )

        Spacer(Modifier.weight(1f))

        Button(
            onClick = onNext,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = AppPrimary,
                contentColor = Color.White
            )
        ) {
            Text(if (index == total - 1) "Commencer" else "Suivant",
                fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
        }
    }
}

@Composable
private fun DotsIndicator(
    selectedIndex: Int,
    count: Int,
    activeColor: Color,
    inactiveColor: Color = activeColor.copy(alpha = .25f)
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        repeat(count) { i ->
            val color = if (i == selectedIndex) activeColor else inactiveColor
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(if (i == selectedIndex) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .then(Modifier)
                    .background(color)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    OnBoarding(onFinish = {})
}
