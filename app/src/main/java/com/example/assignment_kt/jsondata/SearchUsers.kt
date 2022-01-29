package com.example.assignment_kt.jsondata

data class SearchUsers(
    val incomplete_results: Boolean,
    val total_count: Int,
    val items : ArrayList<ItemsResponse>
)