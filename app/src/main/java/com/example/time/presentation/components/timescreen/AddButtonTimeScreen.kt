package com.example.time.presentation.components.timescreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.time.R
import com.example.time.navigation.Screens
import com.example.time.presentation.common.Dimens.SmallPadding4

@Composable
fun AddButton(
    modifier: Modifier,
    navController: NavController
){

    IconButton(
        onClick = {
            navController.navigate(Screens.SearchScreen.route)
        },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add_button),
            modifier = Modifier
                .padding(SmallPadding4),
            contentDescription = "addButton",
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}