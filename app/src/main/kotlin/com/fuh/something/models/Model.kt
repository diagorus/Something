package com.fuh.something.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

/**
 * Created by nick on 15.09.17.
 */

@Entity
data class Model(
        @Id var id: Long = 0,
        var timestamp: Long = 0,
        var text: String? = null
)