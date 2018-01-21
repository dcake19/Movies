package com.example.android.movies.ui.movies


data class DiscoverQuery(var sortby:String = "",
                         var withGenres:String = "",
                         var withoutGenres: String = "",
                         var minVoteAverage: String = "",
                         var minVoteCount: String = "",
                         var primaryReleaseYear: String = "",
                         var minRuntime: String = "",
                         var maxRuntime: String = "")
