package com.example.recipesapp.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Preview
@Composable
fun RecipeCard(
    title : String = "Arrooz",
    description : String = "Arroozuaiosdhfuoiasdfuiobasuoidhfbuaosybdfuhabsudhfbuashbdfuhasbdfuoyhagsudofybaush",
    image : String = "Arrooz",
    modifier: Modifier = Modifier
){

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = MaterialTheme.shapes.medium,
    ){
            Row(modifier = Modifier.fillMaxSize()){
                Column (
                    modifier = modifier
                        .fillMaxHeight()
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Text(
                        text = title,
                        maxLines = 1, // Máximo de líneas permitidas
                        overflow = TextOverflow.Ellipsis, // Mostrar puntos suspensivos
                        style = MaterialTheme.typography.bodyMedium// Opcional: estilo del texto
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    Text(
                        text = description,
                        maxLines = 2, // Máximo de líneas permitidas para descripción
                        overflow = TextOverflow.Ellipsis, // Mostrar puntos suspensivos
                        style = MaterialTheme.typography.bodySmall // Opcional: estilo del texto
                    )
                    Spacer(modifier = modifier.height(8.dp))
                    AssistChip(
                        onClick = { /*TODO*/ },
                        colors = AssistChipDefaults.assistChipColors(
                            leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = "Favourite Button",
                                tint = MaterialTheme.colorScheme.onPrimaryContainer)
                        },
                        label = {
                            Text(text = "Mark as favourite")

                        }
                    )
                }
                Box (modifier = modifier
                    .fillMaxHeight()
                    .width(120.dp),
                    contentAlignment = Alignment.Center){
                    AsyncImage(
                        model = image, // URL de la imagen
                        contentDescription = "Image of the meal",
                        modifier = modifier
                            .fillMaxSize(), // Opcional: redondear bordes
                        contentScale = ContentScale.Crop // Ajusta cómo se muestra la imagen
                    )
                }

            }

    }
}