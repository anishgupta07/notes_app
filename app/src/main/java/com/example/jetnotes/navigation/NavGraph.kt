package com.example.jetnotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetnotes.screens.AddNotesScreen
import com.example.jetnotes.screens.NotesScreen

@Composable
fun NavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "noteScreen"
    ){
        composable(
            "noteScreen"
        ) {
            NotesScreen(navController = navController , viewModel = hiltViewModel())
        }

        composable("addScreen/{noteId}") { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId")?.toInt() ?: -1
            AddNotesScreen(
                navController = navController,
                noteId = noteId
            )
        }
    }
}