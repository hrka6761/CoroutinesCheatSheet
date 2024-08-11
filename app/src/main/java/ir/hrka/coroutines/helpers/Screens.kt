package ir.hrka.coroutines.helpers

import ir.hrka.coroutines.helpers.Constants.MAIN_SCREEN

enum class Screens(private val destination: String) {

    Main(MAIN_SCREEN);


    operator fun invoke() = destination

    fun appendArg(vararg arg: String): String {
        return buildString {
            append(destination)

            arg.forEach { arg ->
                append("/$arg")
            }
        }
    }
}