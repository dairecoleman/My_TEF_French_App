package com.example.myteffrenchapp

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myteffrenchapp.data.AppUiState
import com.example.myteffrenchapp.data.DataSource
import com.example.myteffrenchapp.ui.ResultsScreen
import com.example.myteffrenchapp.ui.theme.AppViewModel

enum class MyTEFFrenchAppScreen() {
    Start,
    Quiz,
    Results
}
/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTEFFrenchAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun MyTEFFrenchApp(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        topBar = {
            MyTEFFrenchAppBar(
                canNavigateBack = false,
        navigateUp = { /* TODO: implement back navigation */ }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = navController,
            startDestination = MyTEFFrenchAppScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = MyTEFFrenchAppScreen.Start.name) {
                StartScreen(
                    //testOptions = DataSource.testOptions,
                    onNextButtonClicked = {
                        viewModel.setQuantity(it)
                        navController.navigate(MyTEFFrenchAppScreen.Quiz.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.padding_medium))
                )
            }
            composable(route = MyTEFFrenchAppScreen.Quiz.name) {
                val context = LocalContext.current
                QuizScreen(
                    uiState= AppUiState(),
                    totalScore = uiState.totalScore,
                    questionSetWordPairs =  DataSource.testOptions,
                    onNextButtonClicked = { navController.navigate(MyTEFFrenchAppScreen.Results.name) },
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    //options = DataSource.flavors.map { id -> context.resources.getString(id) },
                    //onSelectionChanged = { viewModel.setFlavor(it) },
                    modifier = Modifier.fillMaxHeight()
                )
            }
            composable(route = MyTEFFrenchAppScreen.Results.name) {
                ResultsScreen(
                    orderUiState = uiState,
                    onCancelButtonClicked = { cancelOrderAndNavigateToStart(viewModel, navController) },
                    onSendButtonClicked = { subject: String, summary: String -> },
                    modifier = Modifier.fillMaxHeight()
                )
            }
        }
    }
}

private fun cancelOrderAndNavigateToStart(
    viewModel: AppViewModel,
    navController: NavHostController
) {
    viewModel.resetOrder()
    navController.popBackStack(MyTEFFrenchAppScreen.Start.name, inclusive = false)
}