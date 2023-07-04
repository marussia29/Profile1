package com.ingeniovirtual.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
enum class ColportorLevel {
    AVANZADO,
    INTERMEDIO,
    PRINCIPIANTE
}
@Preview
@Composable
fun perfil() {
    var nombre by rememberSaveable { mutableStateOf("Mariel Barrientos") }
    val colportorLevel by remember { mutableStateOf(ColportorLevel.INTERMEDIO) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var volveralperfil by remember { mutableStateOf(false) }
    val imageUri = rememberSaveable { mutableStateOf("") }
    val painter = rememberAsyncImagePainter(
        imageUri.value.ifEmpty { R.drawable.person }
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()){
            uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = when (colportorLevel) {
                    ColportorLevel.AVANZADO -> Color(0xFFCB9AD4)
                    ColportorLevel.INTERMEDIO -> Color(0xFFFFFFFF)
                    ColportorLevel.PRINCIPIANTE -> Color(0xFF88CE92)
                }
            ),
        contentAlignment = Alignment.TopCenter
    )
    {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(ScrollState(0)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(750.dp, 310.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(750.dp, 250.dp)
                        .background(
                            color = when (colportorLevel) {
                                ColportorLevel.AVANZADO -> Color(0xFF49346D)
                                ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                                ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                            }, RoundedCornerShape(0.dp, 0.dp, 180.dp, 180.dp)
                        )
                        .shadow(
                            1.dp,
                            RoundedCornerShape(0.dp, 0.dp, 200.dp, 200.dp),
                            spotColor = when (colportorLevel) {
                                ColportorLevel.AVANZADO -> Color(0xFF49346D)
                                ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                                ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Box(contentAlignment = Alignment.Center) {

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "¡Hola!",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = when (colportorLevel) {
                                    ColportorLevel.AVANZADO -> Color(0XFFE5C23B)
                                    ColportorLevel.INTERMEDIO -> Color(0xFFFFFFFF)
                                    ColportorLevel.PRINCIPIANTE -> Color(0xFFFFFFFF)
                                },
                                fontSize = 30.sp,
                            )
                            Text(
                                text = "$nombre",
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = when (colportorLevel) {
                                    ColportorLevel.AVANZADO -> Color(0xFFE5C23B)
                                    ColportorLevel.INTERMEDIO -> Color(0xFFFFFFFF)
                                    ColportorLevel.PRINCIPIANTE -> Color(0xFFFFFFFF)
                                },
                                fontSize = 35.sp,
                            )
                        }
                    }

                }
                Box( //TARJETA DE PERFIL
                    modifier = Modifier
                        .align(alignment = Alignment.BottomCenter)
                ) {
                    Box(
                        modifier = Modifier     //BOX REDONDO IMAGEN DE PERFIL
                            .padding(10.dp)
                            .size(100.dp)
                            .background(
                                color = when (colportorLevel) {
                                    ColportorLevel.AVANZADO -> Color(0xFF49346D)
                                    ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                                    ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                                }, shape = RoundedCornerShape(100.dp)
                            )
                            .clickable { },
                        contentAlignment = Alignment.Center
                    ) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(100.dp)
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = null,
                                modifier = Modifier
                                    .clickable { launcher.launch("image/*") },
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }

                }

            }

            //MEDALLAS
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ProgressBarDemo()
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp, 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Button(
                        onClick = { showPrivacyDialog = true },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = when (colportorLevel) {
                                ColportorLevel.AVANZADO -> Color(0xFF673AB7)
                                ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                                ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                            }
                        ),
                        shape = CircleShape
                    ) {
                        Text(
                            text = "Política y Privacidad",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Medium,
                            color = when (colportorLevel) {
                                ColportorLevel.AVANZADO -> Color(0xFFFFFFFF)
                                ColportorLevel.INTERMEDIO -> Color(0xFFFBFAFB)
                                ColportorLevel.PRINCIPIANTE -> Color(0xFF99DA44)
                            }
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp, 0.dp)
                        .clickable { },
                    contentAlignment = Alignment.BottomStart
                ) {
                    Row() {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "icono de salida",
                            tint = Color(0xFFF50556),
                            modifier = Modifier.size(30.dp)
                        )
                        Text(
                            text = "Cerrar Sesión", color = Color(0xFFF50556),
                            fontSize = 20.sp, fontWeight = FontWeight.Bold
                        )
                    }

                }
            }
        }
    }
        //parte de abrir politica y privacidad
        if (showPrivacyDialog) {

            Box(
                modifier = Modifier
                    .padding(20.dp, 40.dp)
                    .clip(shape = RectangleShape)
                    .shadow(25.dp, shape = RectangleShape)
                    .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Column() {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(
                                color = when (colportorLevel) {
                                    ColportorLevel.AVANZADO -> Color(0xFFCC9BD5)
                                    ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                                    ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                                }, shape = RoundedCornerShape(10.dp)
                            ),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Button(
                            onClick = { volveralperfil = true },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = when (colportorLevel) {
                                    ColportorLevel.AVANZADO -> Color(0xFFCC9BD5)
                                    ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                                    ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                                }
                            ),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.out),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape),
                                tint = when (colportorLevel) {
                                    ColportorLevel.AVANZADO -> Color(0xFF000000)
                                    ColportorLevel.INTERMEDIO -> Color(0xFF789FBD)
                                    ColportorLevel.PRINCIPIANTE -> Color(0xFFFFFFFF)
                                }
                            )
                        }
                    }
                    Column(
                        modifier = Modifier.verticalScroll(state = ScrollState(0))
                    ) {
                        // Contenido del cuadrado redondeado, como la foto del usuario y el nombre
                        Text(
                            text = "Política de privacidad para la aplicación IDEC \"Herramienta para colportores\":\n" +
                                    "\n" +
                                    "En IDEC, nos tomamos muy en serio la privacidad de nuestros usuarios. Esta Política de Privacidad describe cómo recopilamos, usamos, compartimos y protegemos la información personal de los usuarios de nuestra aplicación \"Herramienta para colportores\". Al utilizar nuestra aplicación, usted acepta las prácticas descritas en esta política.\n" +
                                    "\n" +
                                    "Recopilación de información:\n" +
                                    "\n" +
                                    "    Información personal: Podemos recopilar información personal, como su nombre, dirección de correo electrónico, número de teléfono y ubicación geográfica, cuando usted se registra en la aplicación IDEC. Esta información es necesaria para brindarle nuestros servicios y mejorar su experiencia de usuario.\n" +
                                    "\n" +
                                    "    Información del dispositivo: Podemos recopilar información sobre el dispositivo que utiliza para acceder a la aplicación, incluyendo el modelo del dispositivo, sistema operativo, identificadores únicos del dispositivo y datos de uso. Esta información nos ayuda a optimizar y mejorar la aplicación.\n" +
                                    "\n" +
                                    "Uso de la información:\n" +
                                    "\n" +
                                    "    Mejora de la aplicación: Utilizamos la información recopilada para mejorar la funcionalidad y el rendimiento de la aplicación IDEC, así como para desarrollar nuevas características y servicios.\n" +
                                    "\n" +
                                    "    Comunicaciones: Podemos utilizar su dirección de correo electrónico para enviarle actualizaciones sobre la aplicación, noticias relevantes y promociones. Si no desea recibir estas comunicaciones, puede optar por no recibirlas en cualquier momento.\n" +
                                    "\n" +
                                    "Compartir información:\n" +
                                    "\n" +
                                    "    Terceros de confianza: Podemos compartir su información personal con terceros de confianza que nos ayuden a brindar y mejorar nuestros servicios, como proveedores de servicios de alojamiento, análisis de datos y servicios de atención al cliente. Estos terceros están obligados a proteger su información y solo pueden utilizarla de acuerdo con nuestras instrucciones.\n" +
                                    "\n" +
                                    "    Cumplimiento legal: Podemos divulgar su información personal si así lo exige la ley, como en respuesta a una orden judicial o solicitud legal.\n" +
                                    "\n" +
                                    "Seguridad de la información:\n" +
                                    "Tomamos medidas razonables para proteger la información personal de nuestros usuarios contra accesos no autorizados, alteraciones, divulgaciones o destrucciones. Sin embargo, ninguna transmisión de datos por Internet o almacenamiento electrónico es completamente segura, por lo que no podemos garantizar la seguridad absoluta de su información.\n" +
                                    "\n" +
                                    "Cambios en la política de privacidad:\n" +
                                    "Podemos modificar esta Política de Privacidad ocasionalmente. Si realizamos cambios significativos, le notificaremos a través de la aplicación o por otros medios antes de que los cambios entren en vigencia. Le recomendamos que revise periódicamente esta política para estar informado sobre cómo protegemos su información.\n" +
                                    "\n" +
                                    "Si tiene alguna pregunta sobre esta Política de Privacidad, no dude en ponerse en contacto con nosotros a través de los canales de soporte proporcionados en la aplicación IDEC.\n" +
                                    "\n" +
                                    "Última actualización: [04/06/2023]",
                            fontSize = 20.sp,
                            color = when (colportorLevel) {
                                ColportorLevel.AVANZADO -> Color(0xFF673AB7)
                                ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                                ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                            },
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(shape = RoundedCornerShape(10.dp)),
                        )
                    }
                }
            }
            if (volveralperfil) {
                perfil()
            }
        }
    }


@Composable
fun ProgressBarDemo() {
    var progress by remember { mutableStateOf(0.5f) }

    Column(
        modifier = Modifier.padding(19.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Progreso: ${(progress * 100).toInt()}%", fontWeight = FontWeight.Bold,
        color = Color(0xff14466D)
        )

        LinearProgressIndicator(progress = progress, color = Color(0xff14466D),
            modifier = Modifier
                .size(320.dp, 20.dp)
                .clip(shape = RoundedCornerShape(20.dp)))

        Spacer(modifier = Modifier.height(16.dp))

    }
}

