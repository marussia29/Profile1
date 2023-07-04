package com.ingeniovirtual.profile

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.ingeniovirtual.profile.ui.theme.ProfileTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProfileTheme {
                perfil()
            }
        }
    }
}
@Preview
@Composable
fun ProfileScreen(){
    val notification = rememberSaveable{ mutableStateOf("") }
    if (notification.value.isNotEmpty()){
        Toast.makeText(LocalContext.current, notification.value, Toast.LENGTH_LONG).show()
        notification.value=""
    }
    var name by rememberSaveable { mutableStateOf("default name") }
    var username by rememberSaveable { mutableStateOf("default username") }
    var perfilp by remember { mutableStateOf(false) }
    val colportorLevel by remember { mutableStateOf(ColportorLevel.INTERMEDIO) }

    Column(modifier = Modifier
        .background(color = when (colportorLevel) {
            ColportorLevel.AVANZADO -> Color(0xFF9E2121)
            ColportorLevel.INTERMEDIO -> Color(0xFF7B94A8)
            ColportorLevel.PRINCIPIANTE -> Color(0xFF99DA44)
        })
        .size(450.dp,250.dp)
        .clip(shape= RoundedCornerShape(50.dp))
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(8.dp),
    horizontalAlignment = Alignment.CenterHorizontally)
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Cancelar",
                modifier = Modifier.clickable { perfilp = true },
                color =  when (colportorLevel) {
                    com.ingeniovirtual.profile.ColportorLevel.AVANZADO -> Color(0xFFFFFFFF)
                    com.ingeniovirtual.profile.ColportorLevel.INTERMEDIO -> Color(0xFFFFFFFF)
                    com.ingeniovirtual.profile.ColportorLevel.PRINCIPIANTE -> Color(0xFF406313)
                })
            Text(text = "Guardar",
                modifier = Modifier.clickable { notification.value = "Perfil Guardado" },
                color =  when (colportorLevel) {
                    com.ingeniovirtual.profile.ColportorLevel.AVANZADO -> Color(0xFFFFFFFF)
                    com.ingeniovirtual.profile.ColportorLevel.INTERMEDIO -> Color(0xFFFFFFFF)
                    com.ingeniovirtual.profile.ColportorLevel.PRINCIPIANTE -> Color(0xFF406313)
                })
        }
        profileImage()
    }

}
@Composable
fun profileImage() {
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty { R.drawable.person }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
       uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally)
    {
    Card (
        shape = CircleShape,
    modifier = Modifier
        .padding(8.dp)
        .size(100.dp)) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .clickable { launcher.launch("image/*") },
        contentScale = ContentScale.Crop)
        }
    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfileTheme {
    profileImage()
    }
}
