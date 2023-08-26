package stack

class BasicCalculator {
    fun calculate(s: String): Int {
        return internalCalculate(TokenBuffer(s))
    }

    class TokenBuffer(private val str: String) {
        private var position = 0

        fun hasNext(): Boolean = position < str.length

        fun moveForward() = ++position

        fun moveBackward() = --position

        fun peek(): Char = str[position]
    }

    private fun internalCalculate(buffer: TokenBuffer): Int {
        var result = 0
        var operation: (Int, Int) -> Int = Int::plus

        while (buffer.hasNext()) {
            var current = buffer.peek()

            when (current) {
                '+' -> operation = Int::plus
                '-' -> operation = Int::minus
                '(' -> {
                    buffer.moveForward()
                    result = operation(result, internalCalculate(buffer))
                }
                ')' -> return result
                else -> {
                    if (current in '0'..'9') {
                        val sb = StringBuilder()

                        do {
                            sb.append(current)
                            buffer.moveForward()

                            if (!buffer.hasNext()) break

                            current = buffer.peek()
                        } while (current in '0'..'9')

                        result = operation(result, sb.toString().toInt())
                        buffer.moveBackward()
                    }
                }
            }

            buffer.moveForward()
        }

        return result
    }
}
