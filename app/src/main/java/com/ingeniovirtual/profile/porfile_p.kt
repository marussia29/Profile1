package com.ingeniovirtual.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ColportorLevel {
    AVANZADO,
    INTERMEDIO,
    PRINCIPIANTE
}
@Preview
@Composable
fun ProfileScreeen() {
    val userName by remember { mutableStateOf("Barrientos Mariel") }
    val userPhoto by remember { mutableStateOf(R.drawable.person) }
    val userCambiar by remember { mutableStateOf(R.drawable.edit) }
    val colportorLevel by remember { mutableStateOf(ColportorLevel.PRINCIPIANTE) }
    var showPrivacyDialog by remember { mutableStateOf(false) }
    var volveralperfil by remember { mutableStateOf(false) }
    var editarperfil by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)

    ) {
            Image(painter = painterResource(id = when (colportorLevel) {
                ColportorLevel.AVANZADO -> R.drawable.rectangle_48
                ColportorLevel.INTERMEDIO -> R.drawable.intermedioazul
                ColportorLevel.PRINCIPIANTE -> R.drawable.principianteverde}),
                contentDescription = null,
            modifier = Modifier
                .size(450.dp, 270.dp)
                .align(alignment = Alignment.TopCenter))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = ScrollState(0)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //CAJA DEL PERFIL(FOTO, EDITAR, NOMBRE)
        Column(modifier = Modifier .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(50.dp))
        // Foto de usuario
        Button(onClick = {editarperfil = true },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFFFFFF)),
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)) {
            Image(
                painter = painterResource(id = userPhoto),
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .clickable { editarperfil = true }
                    .padding(0.dp, 0.dp),
                contentScale = ContentScale.Crop
            )
        }

        // Nombre de usuario
        Text(
            text = userName,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color =  when (colportorLevel) {
                ColportorLevel.AVANZADO -> Color(0xFF9E2121)
                ColportorLevel.INTERMEDIO -> Color(0xFFFBFAFB)
                ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
            },
            modifier = Modifier
                .padding(10.dp)
        )
}


            Spacer(modifier = Modifier.height(50.dp))
           //CAJA DE NIVEL COLPORTOR
           Column(
                modifier = Modifier
                    .fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally

            ) {
                // Nivel de colportor
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {

                    Column(
                        modifier = Modifier
                            .padding(1.dp)
                            .clip(shape = RoundedCornerShape(40.dp)),
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = when (colportorLevel) {
                                ColportorLevel.AVANZADO -> R.drawable.avanzado
                                ColportorLevel.INTERMEDIO -> R.drawable.intermedio
                                ColportorLevel.PRINCIPIANTE -> R.drawable.principiante}), contentDescription = null
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Botón de Política y Privacidad
            Button(
                onClick = { showPrivacyDialog = true },
                colors = ButtonDefaults.buttonColors(backgroundColor = when (colportorLevel) {
                    ColportorLevel.AVANZADO -> Color(0xFFE8CA32)
                    ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                    ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                }),
            ) {
                Text(text = "Política y Privacidad",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                color =  when (colportorLevel) {
                    ColportorLevel.AVANZADO -> Color(0xFF9E2121)
                    ColportorLevel.INTERMEDIO -> Color(0xFFFBFAFB)
                    ColportorLevel.PRINCIPIANTE -> Color(0xFF99DA44)
                })
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Cerrar Sesión
            Button(
                onClick = { /* Lógica para cerrar sesión */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = when (colportorLevel) {
                    ColportorLevel.AVANZADO -> Color(0xFFE8CA32)
                    ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                    ColportorLevel.PRINCIPIANTE -> Color(0xFF94D740)
                })
            ) {
                Text(text = "Cerrar Sesión",
                    style = MaterialTheme.typography.h6,
                    fontWeight = FontWeight.Medium,
                color =  when (colportorLevel) {
                    ColportorLevel.AVANZADO -> Color(0xFF9E2121)
                    ColportorLevel.INTERMEDIO -> Color(0xFFFBFAFB)
                    ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                })
            }
        }
    }

    // Diálogo de Política y Privacidad
    if (showPrivacyDialog) {

        Box(
            modifier = Modifier
                .padding(20.dp, 40.dp)
                .clip(shape = RectangleShape)
                .shadow(25.dp, shape = RectangleShape)
                .background(color = Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Column() {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .background(
                            color = when (colportorLevel) {
                                ColportorLevel.AVANZADO -> Color(0xFFE8CA32)
                                ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                                ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                            }
                        ),
                    contentAlignment = Alignment.TopStart
                ) {
                    Button(onClick = { volveralperfil = true },
                        colors = ButtonDefaults.buttonColors(backgroundColor = when (colportorLevel) {
                            ColportorLevel.AVANZADO -> Color(0xFFE8CA32)
                            ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                            ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                        }), ) {
                        Icon(painter = painterResource(id = R.drawable.out)
                            ,contentDescription = null,
                            modifier = Modifier
                                .size(30.dp)
                                .clip(CircleShape),
                        tint =  when (colportorLevel) {
                            ColportorLevel.AVANZADO -> Color(0xFF000000)
                            ColportorLevel.INTERMEDIO -> Color(0xFF789FBD)
                            ColportorLevel.PRINCIPIANTE -> Color(0xFFFFFFFF)
                        }
                            )
                    }
                }
                Column(
                    modifier = Modifier .verticalScroll(state = ScrollState(0))
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
                            ColportorLevel.AVANZADO -> Color(0xFF8B6100)
                            ColportorLevel.INTERMEDIO -> Color(0xFF14466D)
                            ColportorLevel.PRINCIPIANTE -> Color(0xFF10831F)
                        },
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            }

    }
    if(volveralperfil){
        ProfileScreeen()
    }
    if(editarperfil){
        ProfileScreen()
    }
}