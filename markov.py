"""
Use a Markov process to generate random sentences based on a source text.
"""

import random


def build_word_list(source_text):
    """
    Converts a string with whitespace separating words into an ordered list
    containing each word individually with attached punctuation

    Args:
        source_text: the string with whitespace that needs to be broken down
        into words with attached punctuation

    Returns:
        An ordered list of strings containing each word from the input string
        as an individual entity
    """
    return source_text.split()


def build_next_words(word_list):
    """
    Produces a dictionary with mappings to predicted words based upon the order
    of words in a given input string list. Words ending in punctuation marks
    map to empty strings.

    Args:
        word_list: an ordered list of strings containing words as individual
        entities

    Returns:
        next_words: a dictionary where the keys are words and the values
        are string lists of predicted next words/empty strings if the key ends
        in a punctuation mark
    """

    # Initialization
    next_words = {"": [word_list[0]]}

    for index, word in enumerate(word_list):
        if word[-1] in [".", "?", "!"]:
            next_words[word] = [""]
            if index + 1 == len(word_list):
                return next_words
            next_words[""].append(word_list[index + 1])
        else:
            if index + 1 == len(word_list):
                return next_words
            if word not in next_words:
                next_words[word] = [word_list[index + 1]]
            else:
                next_words[word].append(word_list[index + 1])
    return next_words


def generate_sentence(next_words):
    """
    Generates a single random sentence given a dictionary of next words

    Args:
        next_words: a dictionary of where each key is a unique word from a
        sample phrase and the values are lists of possible next words based
        upon the order of words in the sample phrase; all words are strings

    Returns:
        A randomly generated string sentence
    """
    storage = []
    for suggestions in next_words.values():
        new_word = random.choice(suggestions)
        storage += [new_word]
        if new_word[-1] in [".", "?", "!"]:
            sentence = " ".join(storage)
            return sentence


def generate_text(next_words, num_sentences):
    """
    Generates num_sentences random sentences given a dictionary of next words

    Args:
        next_words: a dictionary of where each key is a unique word from a
        sample phrase and the values are lists of possible next words based
        upon the order of words in the sample phrase; all words are strings

        num_sentences: an integer representing the number of desired sentences
        generate_text will output

    Returns:
    A single string with num_sentences number of random sentences.
    """
    storage = []
    for _ in range(num_sentences):
        storage += [generate_sentence(next_words)]
    return " ".join(storage)
