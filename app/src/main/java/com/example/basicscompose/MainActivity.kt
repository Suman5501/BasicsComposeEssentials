package com.example.basicscompose


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.basicscompose.ui.theme.BasicsComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheApp()
        }
    }

}


// Step: MySoothe App
@Composable
fun MySootheApp() {
    // Implement composable here
}



@Preview(showBackground = true)
@Composable
fun MyAppPreview() {
    BasicsComposeTheme {
        MySootheApp()
    }
}
