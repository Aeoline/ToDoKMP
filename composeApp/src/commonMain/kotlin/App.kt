import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import data.MongoDB
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin
import org.koin.dsl.module
import presentation.screen.home.HomeScreen
import presentation.screen.home.HomeViewModel


@Composable
@Preview
fun App() {
    initKoin()

    val lightColors = lightColorScheme(
        primary = Color(0xFF6200EE),
        onPrimary = Color(0xFF3700B3),
        primaryContainer = Color(0xFF3700B3),
        onPrimaryContainer = Color.White,
    )
    val darkColors = darkColorScheme(
        primary = Color(0xFFBB86FC),
        onPrimary = Color.Black,
        primaryContainer = Color(0xFF3700B3),
        onPrimaryContainer = Color.White,
    )
    val colors by mutableStateOf(
        if(isSystemInDarkTheme()) darkColors else lightColors
    )
    MaterialTheme(colorScheme = colors){
        Navigator(HomeScreen()){
            SlideTransition(it)
        }

    }
}

val mongoModule = module {
    single { MongoDB() }
    factory { HomeViewModel(get())}
}

fun initKoin(){
    startKoin {
        modules(mongoModule)
    }
}