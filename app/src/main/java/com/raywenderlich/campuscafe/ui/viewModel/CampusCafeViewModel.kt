package com.raywenderlich.campuscafe.ui.viewModel

import androidx.lifecycle.ViewModel
import com.raywenderlich.campuscafe.ui.dataclasses.MenuItem
import com.raywenderlich.campuscafe.ui.dataclasses.OrderItem
import com.raywenderlich.campuscafe.ui.model.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CampusCafeViewModel : ViewModel() {
// =========================================================
// MENU //
// =========================================================
// The cafe menu is application data.
//
// Keeping it here means the UI does not have to create
// the menu itself.
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
        category = Category.DRINK ),
    MenuItem(
        id = 3,
        name = "Donut",
        price = 1.75,
        category = Category.DESSERT ),
    MenuItem(
        id = 4,
        name = "Sandwich",
        price = 6.50,
        category = Category.FOOD )
)
// ========================================================= \
// ORDER STATE
// =========================================================
// MutableStateFlow is private.
//
// The ViewModel can change the order, but screens cannot
// directly modify the order.
private val _orderItems = MutableStateFlow<List<OrderItem>>(emptyList())
// StateFlow is the read-only version of MutableStateFlow.
//
// The UI observes this value.
val orderItems: StateFlow<List<OrderItem>> = _orderItems.asStateFlow()

// =========================================================
// TOTAL STATE
// =========================================================
    // This stores the current order total.
    //
    // The UI can observe this value, but cannot modify it.
    private val _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total.asStateFlow()

// =========================================================
// ADD ITEM
// =========================================================
fun addItem(
    menuItem: MenuItem
) {
    val currentOrder = _orderItems.value
    val existingItem = currentOrder.find {
        it.menuItem.id == menuItem.id
    }

    if (existingItem != null) {
        val updatedOrder = currentOrder.map { orderItem ->
            if (orderItem.menuItem.id == menuItem.id) {
                orderItem.copy( quantity = orderItem.quantity + 1 )
            } else {
                orderItem
            }
        }
        _orderItems.value = updatedOrder
    } else {
        val updatedOrder = currentOrder + OrderItem(
            menuItem = menuItem,
            quantity = 1
        )
        _orderItems.value = updatedOrder
    }
}
// =========================================================
// REMOVE ONE
// =========================================================
fun removeItem(
    menuItem: MenuItem
) {
    val currentOrder = _orderItems.value
    val existingItem = currentOrder.find {
        it.menuItem.id == menuItem.id
    }
    if (existingItem != null) {
        if (existingItem.quantity > 1) {
            val updatedOrder = currentOrder.map { orderItem ->
                if (orderItem.menuItem.id == menuItem.id) {
                    orderItem.copy( quantity = orderItem.quantity - 1 )
                } else {
                    orderItem
                }
            }
            _orderItems.value = updatedOrder
        } else {
            val updatedOrder = currentOrder.filter {
                it.menuItem.id != menuItem.id
            }
            _orderItems.value = updatedOrder
        }
    }
}
// =========================================================
// CLEAR ORDER
// =========================================================
fun clearOrder() {
    _orderItems.value = emptyList()
}
// =========================================================
// TOTAL
// =========================================================
fun getTotal(): Double {
    return _orderItems.value.sumOf { orderItem ->
        orderItem.menuItem.price * orderItem.quantity
    }
  }
}