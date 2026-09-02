package com.raywenderlich.campuscafe.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.raywenderlich.campuscafe.ui.main.HomeScreen
import com.raywenderlich.campuscafe.ui.menu.MenuDetailsScreen
import com.raywenderlich.campuscafe.ui.menu.MenuScreen
import com.raywenderlich.campuscafe.ui.order.OrderScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raywenderlich.campuscafe.ui.checkout.CheckOutScreen
import com.raywenderlich.campuscafe.ui.profile.ProfileScreen
import com.raywenderlich.campuscafe.ui.viewModel.CampusCafeViewModel

object Routes {
    const val HOME = "home"
    const val MENU = "menu"
    const val MENU_DETAILS = "menu/{itemId}"
    const val ORDER = "order"
    const val PROFILE = "profile"
    const val CHECKOUT = "checkout"
}

@Composable
fun AppNavigation() {

    // Navigation controller manages movement between screens.
    val navController = rememberNavController()
    // ---------------------------------------------------------
    // VIEWMODEL
    // ---------------------------------------------------------
    // The ViewModel owns our application state.
    //
    // We do NOT create an OrderManager anymore.
    val viewModel: CampusCafeViewModel = viewModel()
    // ---------------------------------------------------------
    // OBSERVE ORDER ITEMS
    // ---------------------------------------------------------
    // Convert StateFlow into Compose state.
    //
    // When orderItems changes, Compose automatically
    // recomposes the screens using this value.
    val orderItems by viewModel.orderItems .collectAsStateWithLifecycle()
    // ---------------------------------------------------------
    // OBSERVE TOTAL
    // ---------------------------------------------------------
    val total by viewModel.total .collectAsStateWithLifecycle()

    // POINTS-TRACKER:
    val points by viewModel.points.collectAsStateWithLifecycle()

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
                points = points,
                // HomeScreen tells AppNavigation that
                // the user wants to see the menu.
                onMenuClick = {
                    navController.navigate(Routes.MENU)
                },
                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                }
            )
        }

        // -------------------------
        // MENU
        // -------------------------

        composable(Routes.MENU) {

            MenuScreen(
                // Give MenuScreen the menu data.
                menuItems = viewModel.menuItems,
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

            val selectedItem = viewModel.menuItems.find {
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
                        viewModel.addItem(
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
                // Current order from the ViewModel.
                orderItems = orderItems,
                // Current total from the ViewModel.
                total = total,

                // Increase quantity.
                onIncreaseQuantity = { orderItem ->

                    viewModel.increaseQuantity(
                        orderItem.menuItem
                    )
                },

                // Decrease quantity.
                onDecreaseQuantity = { orderItem ->

                    viewModel.decreaseQuantity(
                        orderItem.menuItem
                    )
                },

                // ---------------------------------------------
                // REMOVE ONE
                // ---------------------------------------------
                onRemoveItem = { orderItem ->

                    viewModel.removeItem(
                        orderItem.menuItem
                    )
                },

                onClearOrder = {

                    viewModel.clearOrder()
                },

                onCheckout = {
                    // Navigate to the Checkout screen.
                    //
                    // We do NOT clear the order yet because
                    // Checkout still needs the current total.

                    navController.navigate(
                        Routes.CHECKOUT
                    )
                }
            )
        }

        // CHECKOUT
        composable(Routes.CHECKOUT) {

            CheckOutScreen(
                total = total,
                pointsEarned = total.toInt(),

                onDoneClick = {
                    // Award the points.
                    viewModel.addPoints(
                        total.toInt()
                    )

                    // Clear the completed order.
                    viewModel.clearOrder()

                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.HOME) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        // PROFILE [Screen]
        composable(Routes.PROFILE) {
            ProfileScreen(
                points = points,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

    }
}