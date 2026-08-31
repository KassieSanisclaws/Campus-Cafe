package com.raywenderlich.campuscafe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.raywenderlich.campuscafe.ui.dataclasses.MenuItem
import com.raywenderlich.campuscafe.ui.main.HomeScreen
import com.raywenderlich.campuscafe.ui.menu.MenuDetailsScreen
import com.raywenderlich.campuscafe.ui.menu.MenuScreen
import com.raywenderlich.campuscafe.ui.model.Category
import com.raywenderlich.campuscafe.ui.order.OrderManager
import com.raywenderlich.campuscafe.ui.order.OrderScreen
import androidx.compose.runtime.remember

object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val MENU_DETAILS = "menu/{itemId}"
    const val ORDER = "order"
}

@Composable
fun AppNavigation() {

    // Navigation controller manages movement between screens.
    val navController = rememberNavController()
    // OrderManager stores and manages the current order.
    val orderManager = remember {
        OrderManager()
    }


    val menuItems = listOf(

        MenuItem(
            id = 1,
            name = "Coffee",
            price = 2.50,
            category = Category.DRINK
        ),

        MenuItem(
            id = 2,
            name = "Tea",
            price = 2.00,
            category = Category.DRINK
        ),

        MenuItem(
            id = 3,
            name = "Donut",
            price = 1.75,
            category = Category.DESSERT
        ),

        MenuItem(
            id = 4,
            name = "Sandwich",
            price = 6.50,
            category = Category.FOOD
        )
    )

    // NavHost contains all of the screens in our application.
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {

        // -------------------------
        // HOME
        // -------------------------

        composable(Routes.HOME) {

            HomeScreen(
                // HomeScreen tells AppNavigation that
                // the user wants to see the menu.
                onMenuClick = {
                    navController.navigate(Routes.MENU)
                }
            )
        }

        // -------------------------
        // MENU
        // -------------------------

        composable(Routes.MENU) {

            MenuScreen(
                // Give MenuScreen the menu data.
                menuItems = menuItems,
                // When the user selects an item,
                // navigate to the details screen.
                onItemClick = { item ->

                    navController.navigate(
                        "menu/${item.id}"
                    )
                }
            )
        }

        // -------------------------
        // MENU DETAILS
        // -------------------------

        composable(
            route = Routes.MENU_DETAILS,

            arguments = listOf(
                navArgument("itemId") {
                    type = NavType.IntType
                }
            )

        ) { backStackEntry ->

            val itemId = backStackEntry
                .arguments
                ?.getInt("itemId")

            val selectedItem = menuItems.find {
                it.id == itemId
            }

            if (selectedItem != null) {

                MenuDetailsScreen(
                    // Give the selected menu item to the screen.
                    item = selectedItem,

                    onBackClick = {
                        // Remove the current screen from
                        // the navigation back stack.
                        navController.popBackStack()
                    },

                    onAddToOrder = {
                        // Add the selected item to the order.
                        orderManager.addItem(
                            selectedItem
                        )

                        // After adding the item,
                        // navigate to the order screen.
                        navController.navigate(
                            Routes.ORDER
                        )
                    }
                )
            }
        }

        // -------------------------
        // ORDER
        // -------------------------

        composable(Routes.ORDER) {

            OrderScreen(
                // Give OrderScreen the current order items.
                orderItems = orderManager.items,
                // Give OrderScreen the current total.
                total = orderManager.getTotal(),

                onRemoveItem = { orderItem ->

                    orderManager.removeItem(
                        orderItem.menuItem
                    )
                },

                onClearOrder = {

                    orderManager.clearOrder()
                },

                onCheckout = {
                    // Clear the current order.
                    orderManager.clearOrder()
                    // Return to the home screen.
                    navController.navigate(
                        Routes.HOME
                    ) {
                        // Remove previous navigation
                        // destinations from the back stack.
                        popUpTo(Routes.HOME) {
                            inclusive = false
                        }
                    }
                }
            )
        }
    }
}