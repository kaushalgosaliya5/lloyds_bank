package com.example.lloydsbank.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.lloydsbank.presentation.viewmodel.ItemDetailsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(itemId : String, navController: NavController){

      val viewModel : ItemDetailsViewModel = hiltViewModel()
      val item by viewModel.item.collectAsState()

      LaunchedEffect(itemId) {
            viewModel.fetchItem(itemId)
      }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Fruit Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Material Icon for back
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        // Screen content
        item?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AsyncImage(
                    model = it.imageUrl,
                    contentDescription = it.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(256.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = it.name ?: "",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally)
                )
                Text( text = "Price: ${it.price}  ",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface)
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = it.description ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
        } ?: run {
            // Show a loading indicator or an error message
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

}