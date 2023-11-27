package com.example.pertemuan13

import com.google.firebase.firestore.Exclude

data class Note(

    @set:Exclude @get:Exclude @Exclude var id: String = "",

    var title: String? = "",
    var description: String? = "",

)
