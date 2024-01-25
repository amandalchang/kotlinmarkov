import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class MainKtTest {

    @Test
    fun getListWords() {
        val sourceText = "I will split sentences properly. Punctuation included!"
        val result = listWords(sourceText)
        assertTrue(result ==
                listOf("I", "will", "split", "sentences",
                    "properly.", "Punctuation", "included!"))
    }

    @Test
    fun buildNextWords() {
        // A single word sentence
        val wordList = listOf("Pneumonoultramicroscopicsilicovolcaniconiosis.")
        val actualNextWords =
            mapOf("" to listOf("Pneumonoultramicroscopicsilicovolcaniconiosis."),
                "Pneumonoultramicroscopicsilicovolcaniconiosis." to listOf(""))
        assertTrue(buildNextWords(wordList) == actualNextWords)

        // A sentence with returns
        val source = "I am Sam.\n\n I am Sam.\n Sam I am."
        val wordList2 = listWords(source)
        val realMap = mapOf(
            "" to listOf("I", "I", "Sam"),
            "I" to listOf("am", "am", "am."),
            "am" to listOf("Sam.", "Sam."),
            "Sam." to listOf(""),
            "Sam" to listOf("I"),
            "am." to listOf("")
        )
        assertTrue(buildNextWords(wordList2) == realMap)


    }

    @Test
    fun generateSentence() {
        // Sentence with punctuation in the middle
        val source =
            "My linkedin is " +
                    "www.linkedin.com/in/amanda-l-chang/, which is cool!"
        val aNewSentence =
            generateSentence(buildNextWords(listWords(source)))
        // The sentence ending in ! means that it must have ended in cool!,
        // since my LinkedIn link doesn't have any ! in it.
        assertTrue(aNewSentence.last() == '!')

    }

    @Test
    fun generateText() {
        // Make sure the function creates the correct number of sentences
        val trials = 5
        val source =
            "I am Sam. I am Sam. Sam I am."
        for (numSentences in 1..trials) {
            val aNewText =
                generateText(buildNextWords(listWords(source)), numSentences)
            // Checking that there are a proper number of sentences
            assertTrue(aNewText.count { it == '.' } == numSentences)
            // Checking that the last sentence ends in a period
            // (the only punctuation I gave it in this test)
            assertTrue(aNewText.last() == '.')
        }
    }
}