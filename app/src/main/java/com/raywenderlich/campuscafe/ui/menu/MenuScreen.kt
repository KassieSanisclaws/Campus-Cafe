package com.raywenderlich.campuscafe.ui.menu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raywenderlich.campuscafe.ui.dataclasses.MenuItem
import com.raywenderlich.campuscafe.ui.model.Category

@Composable
fun MenuScreen(
    menuItems: List<MenuItem>,
    onItemClick: (MenuItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "☕ Campus Café Menu"
        )

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(menuItems) { item ->

                MenuItemCard(
                    item = item,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

@Composable
fun MenuItemCard(
    item: MenuItem,
    onItemClick: (MenuItem) -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = item.name
            )

            Text(
                text = "Category: ${item.category}"
            )

            Text(
                text = "$${item.price}"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {

                Button(
                    onClick = {
                        onItemClick(item)
                    }
                ) {
                    Text(
                        text = "View Details"
                    )
                }
            }
        }
    }
}

