package com.example.jetnotes.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetnotes.room.NotesEntity
import com.example.jetnotes.viewModels.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(modifier: Modifier = Modifier ,navController: NavController, viewModel: NotesViewModel) {
    val notesList = viewModel.notes.collectAsState(initial = emptyList())
    val searchText = viewModel.searchQuery.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedNote by remember { mutableStateOf<NotesEntity?>(null) }

    Scaffold(
        floatingActionButton = {
            ElevatedButton(
                onClick = {navController.navigate("addScreen/-1")},
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = Color.White
                ),
                shape = CircleShape
            ) {
                Icon(imageVector = Icons.Default.Add , contentDescription = "Add_notes" , Modifier.size(25.dp))
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("Notes" , color = MaterialTheme.colorScheme.onSurface , fontSize = 40.sp , fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {innerpadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerpadding)
        ){

                NotesSearchBar(
                    query = searchText.value,
                    onQueryChange = { viewModel.updateSearch(it) }
                )


            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(notesList.value) {note ->
                    NotesCard(notes = note , navController, onLongClick = {
                        showDialog = true
                        selectedNote = note
                    })
                }
            }
            if (showDialog && selectedNote != null) {
                CustomDialog(
                    onConfirm = {
                        viewModel.deleteNotes(selectedNote!!)
                        showDialog = false
                        selectedNote = null
                    },
                    onDismiss = {
                        showDialog = false
                        selectedNote = null
                    }
                )
            }

        }
    }
}