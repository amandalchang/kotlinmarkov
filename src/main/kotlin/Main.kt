// Using a Markov process to generate random sentences

// This lambda function splits a string into an ordered list of strings with
// spaces as the delimiter.
val listWords =
    { sourceText: String ->
        sourceText.split(" ").map { it.replace("\n", "") } }
fun buildNextWords(wordList: List<String>): Map<String, List<String>> {
    /**
    Produces a map with mappings to predicted words based upon the order
    of words in a given input string list. Words ending in punctuation marks
    map to empty strings.

    Args:
    wordList: an ordered list of strings containing words as individual
    entities

    Returns:
    nextWords: a map where the keys are words and the values
    are string lists of predicted next words/empty strings if the key ends
    in a punctuation mark
     */
    val nextWords: MutableMap<String, List<String>> =
        mutableMapOf("" to listOf(wordList[0]))
    for ((i, word) in wordList.withIndex()) {
        when {
            word.last() in setOf('?', '!', '.') -> {
                nextWords[word] = listOf("")
                if (i + 1 == wordList.size) {
                    return nextWords
                }
                nextWords[""] =
                    (nextWords[""] ?: emptyList()) + listOf(wordList[i + 1])
            }

            else -> {
                if (i + 1 == wordList.size) {
                    return nextWords
                }
                nextWords[word] =
                    (nextWords[word] ?: emptyList()) + listOf(wordList[i + 1])
            }
        }
    }
    return nextWords
}

fun generateSentence(nextWords: Map<String, List<String>>): String {
    /**
    Generates a single random sentence given a map of next words

    Args:
    nextWords: a map where each key is a unique word from a sample phrase
    and the values are lists of possible next words based upon the order
    of words in the sample phrase; all words are strings

    Returns:
    A randomly generated string sentence
     */
    val sentenceStorage: MutableList<String> = mutableListOf("")
    var currentWord = ""
    var newWord: String
    do {
        val suggestions = nextWords[currentWord] ?: break
        newWord = suggestions.random()
        sentenceStorage += newWord
        currentWord = newWord
    } while (newWord.last() !in setOf('?', '!', '.'))
    return sentenceStorage.joinToString(" ")
}

fun generateText(nextWords: Map<String, List<String>>, numSentences: Int): String {
    /**
    Generates numSentences random sentences given a map of next words

    Args:
    nextWords: a map where each key is a unique word from a sample phrase
    and the values are lists of possible next words based upon the order
    of words in the sample phrase; all words are strings

    numSentences: an integer representing the number of desired sentences
    generateText will output

    Returns:
    A single string with numSentences number of random sentences.
     */
    val storage = mutableListOf("")
    for (i in 1..numSentences) {
        storage += generateSentence(nextWords)
    }
    return storage.joinToString("")
}

fun main() {
    val source = "I am sam.\n\n\n Sam I am."
    val numSentences = 4
    println(generateText(buildNextWords(listWords(source)), numSentences))

}