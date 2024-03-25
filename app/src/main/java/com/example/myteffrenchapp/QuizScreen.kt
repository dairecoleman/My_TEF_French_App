/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.myteffrenchapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myteffrenchapp.data.AppUiState
import com.example.myteffrenchapp.data.DataSource
import com.example.myteffrenchapp.ui.theme.MyTEFFrenchAppTheme


/**
 * Composable that displays word to translate and the translation options
 * as large clickable buttons,
 * [onSelection] lambda that reveals answer and loads new question the parent composable when a new value is selected,
 * [onCancelButtonClicked] lambda that cancels the test when user clicks cancel and
 * [onNextButtonClicked] lambda that triggers the navigation to next screen
 */
@Composable
fun QuizScreen(
    uiState: AppUiState,
    totalScore: Int = uiState.totalScore,
    questionSetWordPairs: List<Pair<String, String>>,
    options: List<String>,
    onCancelButtonClicked: () -> Unit = {},
    onNextButtonClicked: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var currentScore = uiState.currentScore
    var answerWordPair = { DataSource.testOptions.random() }
    var selectedValue by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
                .height(150.dp)
                .width(300.dp)
                .fillMaxSize()
                .align(alignment = Alignment.CenterHorizontally),
            color = Color.Magenta,
            shape = RoundedCornerShape(20.dp)

        ) {
            Text(
                text = "Translate!",
                fontSize = 48.sp,
                textAlign = TextAlign.Center
            )
        }

        TwoByTwoGrid(
                button1Text = "Button 1",
                button2Text = "Button 2",
                button3Text = "Button 3",
                button4Text = "Button 4",
                onButton1Click = { /* Handle button 1 click */ },
                onButton2Click = { /* Handle button 2 click */ },
                onButton3Click = { /* Handle button 3 click */ },
                onButton4Click = { /* Handle button 4 click */ }
            )

        /*
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))) {
            options.forEach { item ->
                Row(
                    modifier = Modifier.selectable(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedValue == item,
                        onClick = {
                            selectedValue = item
                            onSelectionChanged(item)
                        }
                    )
                    Text(item)
                }
            }


            Divider(
                thickness = dimensionResource(R.dimen.thickness_divider),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
            )
            FormattedPriceLabel(
                subtotal = subtotal,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(
                        top = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_medium)
                    )
            )
        }

         */

        /*
        Bottom of App Screen Bar
         */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.Bottom
        ) {
            OutlinedButton(
                modifier = Modifier.weight(1f),
                onClick = onCancelButtonClicked
            ) {
                Text(stringResource(R.string.cancel))
            }
            Button(
                modifier = Modifier.weight(1f),
                // the button is enabled when the user makes a selection
                enabled = selectedValue.isNotEmpty(),
                onClick = onNextButtonClicked
            ) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

/*
Function to display 4 button options in a2x2 grid.
 */
@Composable
fun TwoByTwoGrid(
    modifier: Modifier = Modifier,
    button1Text: String,
    button2Text: String,
    button3Text: String,
    button4Text: String,
    onButton1Click: () -> Unit,
    onButton2Click: () -> Unit,
    onButton3Click: () -> Unit,
    onButton4Click: () -> Unit
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = onButton1Click,
                modifier = Modifier.weight(1f).padding(4.dp)
            ) {
                Text(text = button1Text)
            }

            Button(
                onClick = onButton2Click,
                modifier = Modifier.weight(1f).padding(4.dp)
            ) {
                Text(text = button2Text)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = onButton3Click,
                modifier = Modifier.weight(1f).padding(4.dp)
            ) {
                Text(text = button3Text)
            }

            Button(
                onClick = onButton4Click,
                modifier = Modifier.weight(1f).padding(4.dp)
            ) {
                Text(text = button4Text)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    MyTEFFrenchAppTheme {
        QuizScreen(
            uiState = AppUiState(),
            questionSetWordPairs = listOf(Pair("Answer A1", "Answer A2"), Pair("Answer B1","Answer B2")),
            options = listOf("Answer A", "Answer B", "Answer C"),
            modifier = Modifier.fillMaxHeight()
        )
    }
}
