package com.yyusufsefa.videogameappcompose.domain.model

enum class PlatformStatus {
    PC, PlayStation, Xbox;

    companion object {
        fun fromString(value: String): PlatformStatus? {
            return values().find { it.name.equals(value, ignoreCase = true) }
        }
    }
}