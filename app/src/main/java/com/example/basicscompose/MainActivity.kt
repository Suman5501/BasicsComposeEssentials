package com.example.basicscompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscompose.components.AlignYourBodyElement
import com.example.basicscompose.components.FavoriteCollectionCard
import com.example.basicscompose.components.SearchBar
import com.example.basicscompose.components.SootheBottomNavigation
import com.example.basicscompose.components.SootheNavigationRail
import com.example.basicscompose.data.DrawableStringPair
import com.example.basicscompose.data.alignYourBodyData
import com.example.basicscompose.data.favoriteCollectionsData
import com.example.basicscompose.ui.theme.BasicsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheApp()
        }
    }
}


// Step: Align your body row - Arrangements
@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){

        items(alignYourBodyData) {
                item ->
            AlignYourBodyElement(drawable = item.drawable, item.text)
        }
    }
}

// Step: Favorite collections grid - LazyGrid
@Composable
fun FavoriteCollectionsGrid(
    modifier: Modifier = Modifier
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.height(168.dp)
    ) {
        items(favoriteCollectionsData) { item ->
            FavoriteCollectionCard(
                drawable = item.drawable,
                text = item.text
            )
        }
    }
}

// Step: Home section - Slot APIs
@Composable
fun HomeSection(
    @StringRes title : Int,
    modifier: Modifier = Modifier,
    content : @Composable () -> Unit
) {

    Column(modifier) {
        Text(
            text = stringResource(title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 16.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }

}

// Step: Home screen - Scrolling
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body) {
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections) {
            FavoriteCollectionsGrid()
        }
        Spacer(Modifier.height(16.dp))

    }
}


// Step: MySoothe App - Scaffold
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySootheAppPortrait() {
    BasicsComposeTheme {
        Scaffold (
          bottomBar = { SootheBottomNavigation() }
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}



// Step: Landscape Mode
@Composable
fun MySootheAppLandscape(){
    BasicsComposeTheme {
        Surface (color = MaterialTheme.colorScheme.background) {
            Row {
                SootheNavigationRail()
                HomeScreen()
            }
        }
    }
}

// Step: MySoothe App
@Composable
fun MySootheApp() {
    // Implement composable here
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun FavoriteCollectionCardPreview() {
    BasicsComposeTheme {
        FavoriteCollectionCard(
            text = R.string.fc2_nature_meditations,
            drawable = R.drawable.fc2_nature_meditations,
            modifier = Modifier.padding(8.dp)
        )
    }
}


@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun AlignYourBodyRowPreview() {
    BasicsComposeTheme { AlignYourBodyRow() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun HomeSectionPreview() {
    BasicsComposeTheme {
        HomeSection(R.string.align_your_body) {
            AlignYourBodyRow()
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun ScreenContentPreview() {
    BasicsComposeTheme { HomeScreen() }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun BottomNavigationPreview() {
    BasicsComposeTheme { SootheBottomNavigation(Modifier.padding(top = 24.dp)) }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F0EE)
@Composable
fun NavigationRailPreview() {
    BasicsComposeTheme { SootheNavigationRail() }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun MySoothePortraitPreview() {
    MySootheAppPortrait()
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun MySootheLandscapePreview() {
    MySootheAppLandscape()
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    BasicsComposeTheme {
        MySootheApp()
    }
}

//@Composable
//private fun MyApp(modifier: Modifier = Modifier) {
//
//    val shouldShowOnboarding = rememberSaveable { mutableStateOf(true)}
//
//    Surface(modifier, color = MaterialTheme.colorScheme.background) {
//        if (shouldShowOnboarding.value) {
//            OnboardingScreen(onContinueClicked = { shouldShowOnboarding.value = false })
//        } else {
//            Greetings()
//        }
//    }
//}
//@Composable
//fun OnboardingScreen(
//    onContinueClicked: () -> Unit,
//    modifier: Modifier = Modifier
//){
//
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = "Welcome to the compose demo app")
//        Button(
//            modifier = Modifier.padding(vertical = 24 .dp),
//            onClick = onContinueClicked
//        ) {
//            Text(text = "Continue")
//
//        }
//    }
//}
//
//@Composable
//private fun Greetings(
//    modifier: Modifier = Modifier,
//    names : List<String> = listOf("there", "Compose")
//) {
//
//    Column(modifier = modifier.padding(vertical = 4 .dp)) {
//        for (name in names) {
//            Greeting(name = name)
//        }
//    }
//}
//
//@Composable
//fun Greeting(name: String) {
//
//    val expanded = remember { mutableStateOf(false) }
//    val extraPadding by animateDpAsState(
//        if (expanded.value) 48 .dp else 0 .dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )
//
//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = Modifier.padding(vertical = 4 .dp, horizontal = 8. dp)
//    ) {
//        Row(modifier = Modifier.padding(24 .dp)) {
//
//            Column(modifier = Modifier
//                .weight(1f)
//                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//            ) {
//                Text(text = "Hello!")
//                Text(text = "$name!")
//            }
//
//            ElevatedButton(
//                onClick = { expanded.value = !expanded.value }
//            ) {
//                Text(if (expanded.value) "Show less" else "Show more")
//            }
//        }
//    }
//}

//@Preview(showBackground = true, widthDp = 320, heightDp = 320)
//@Composable
//fun OnboardingPreview() {
//    BasicsComposeTheme {
//        OnboardingScreen(onContinueClicked = {})
//    }
//}
//
//@Preview(showBackground = true, widthDp = 320)
//@Composable
//fun GreetingPreview() {
//    BasicsComposeTheme {
//        Greetings()
//    }
//}
//
