package com.ingeniovirtual.profile

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview
@Composable
fun perfil() {
    var nombre by rememberSaveable { mutableStateOf("Mariel Barrientos") }
    val colportorLevel by remember { mutableStateOf(ColportorLevel.INTERMEDIO) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var volveralperfil by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = when (colportorLevel) {
                    ColportorLevel.AVANZADO -> Color(0xFFffbf00)
                    ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                    ColportorLevel.PRINCIPIANTE -> Color(0xFF003A08)
                }),
        contentAlignment = Alignment.TopCenter
    )
    { Circle()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(ScrollState(0)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column( //TARJETA DE PERFIL
                modifier = Modifier
                    .padding(0.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    Box(modifier = Modifier     //BOX REDONDO IMAGEN DE PERFIL
                        .padding(20.dp)
                        .size(80.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(50.dp))
                        .clickable { },
                        contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Filled.Person,
                            contentDescription = "icono de perfil",
                            modifier = Modifier
                                .size(60.dp))

                    }
                    Column() {
                        Text(
                            text = "¡Hola!",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 20.sp,
                        )
                        Text(
                            text = "$nombre",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 30.sp,
                        )
                    }
                }

            }
            //MEDALLAS
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(200.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = painterResource(
                            id = when (colportorLevel) {
                                ColportorLevel.AVANZADO -> R.drawable.nivel_3
                                ColportorLevel.INTERMEDIO -> R.drawable.nivel_2
                                ColportorLevel.PRINCIPIANTE -> R.drawable.nivel_1
                            }
                        ),
                        contentDescription = null,
                    )
                }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp, 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Button(
                        onClick = { showPrivacyDialog = true },
                        colors = ButtonDefaults.buttonColors(backgroundColor = when (colportorLevel) {
                            ColportorLevel.AVANZADO -> Color(0xFF673AB7)
                            ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                            ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                        }),
                        shape = CircleShape
                    ) {
                        Text(text = "Política y Privacidad",
                            style = MaterialTheme.typography.h6,
                            fontWeight = FontWeight.Medium,
                            color =  when (colportorLevel) {
                                ColportorLevel.AVANZADO -> Color(0xFFFFFFFF)
                                ColportorLevel.INTERMEDIO -> Color(0xFFFBFAFB)
                                ColportorLevel.PRINCIPIANTE -> Color(0xFF99DA44)
                            })
                    }
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp, 0.dp)
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
                            modifier = Modifier.padding(10.dp) .clip(shape = RoundedCornerShape(10.dp)),
                        )
                    }
                }
            }
            if (volveralperfil) {
                perfil()
            }
        }
    }

}


@Composable
fun Circle(){
    val colportorLevel by remember { mutableStateOf(ColportorLevel.PRINCIPIANTE) }

    Box(modifier = Modifier
        .fillMaxSize()){

        Box(modifier = Modifier
            .size(450.dp,380.dp)
            .background(
                color = Color.White, RoundedCornerShape(0.dp, 300.dp)
            )
            .shadow(500.dp, RoundedCornerShape(0.dp, 550.dp))
            .align(alignment = Alignment.BottomStart)) {

        }
    }
}