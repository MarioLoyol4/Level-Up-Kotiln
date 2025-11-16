package com.example.level_up.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.collectAsState
import com.example.level_up.viewmodel.PostViewModel
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(viewModel: PostViewModel){

    val posts = viewModel.posts.collectAsState()
    Scaffold(
        topBar = { TopAppBar(title = { Text("Listado de Posts") }) }
    ){ padding ->
        LazyColumn (
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ){
            items(posts.value){ posts ->
                Card (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ){
                    Column (modifier = Modifier.padding(12.dp)){
                        Text(text = posts.title, style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = posts.body)
                    }
                }
            }
        }
    }
}