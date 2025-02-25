package com.example.lloydsbank.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.data.utils.Constant
import com.example.domain.model.Item
import com.example.lloydsbank.R
import com.example.lloydsbank.presentation.state.ItemListState
import com.example.lloydsbank.presentation.viewmodel.ItemListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(viewModel: ItemListViewModel = hiltViewModel(), navController: NavController) {

    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(
                stringResource(R.string.fruits_list),
                textAlign = TextAlign.Center
            )
        })

        when (uiState) {

            is ItemListState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = 4.dp
                    )
                }
            }

            is ItemListState.Success -> {
                val items = (uiState as ItemListState.Success).items
                LazyColumn {
                    items(items) { item ->
                        ItemRow(item = item, navController = navController)
                    }
                }
            }

            is ItemListState.Error -> {
                Text(
                    text = (uiState as ItemListState.Error).message,
                    color = Color.Red,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun ItemRow(item: Item, navController: NavController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)
        .background(
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(8.dp)
        )
        .shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            ambientColor = Color.Blue,
            spotColor = Color.Red
        )
        .clickable {
            navController.navigate("${Constant.ITEM_DETAILS}/${item.id}")
        }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = item.name ?: "",
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = stringResource(R.string.price) + " : ${item.price}  ",
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.description ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

    }
}