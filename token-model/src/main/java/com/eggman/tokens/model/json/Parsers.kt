package com.eggman.tokens.model.json

import com.eggman.tokens.model.token.ButtonJsonToken
import com.eggman.tokens.model.token.ComponentJsonToken
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

object Parsers {
    fun json() : Json {
        val module = SerializersModule {
            polymorphic(ComponentJsonToken::class) {
                subclass(ButtonJsonToken::class, ButtonJsonToken.serializer())
            }
        }
        return Json {
            serializersModule = module
            ignoreUnknownKeys = true
        }
    }
}