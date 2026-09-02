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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raywenderlich.campuscafe.ui.dataclasses.MenuItem

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
            text = "☕ Campus Café Menu",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Choose something delicious!",
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 16.sp
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 16.dp
                ),
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
                text = item.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Category: ${item.category}",
                modifier = Modifier.padding(top = 4.dp)
            )

            Text(
                text = "$${"%.2f".format(item.price)}",
                modifier = Modifier.padding(top = 8.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
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

