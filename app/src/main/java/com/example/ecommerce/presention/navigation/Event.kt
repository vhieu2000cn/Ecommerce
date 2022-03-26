package com.example.ecommerce.presention.navigation

open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false

    /**
     * khi xoay man hinh livedata se goi lai nen can ngan chan
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }
}