# Markov Text Generation in Kotlin
### Purpose
This code uses a Markov process to generate random paragraphs. You feed it 
source text and the number of desired sentences, and it outputs a paragraph
of words in which each successive word is chosen randomly based off of what
word came before it. So using a source text like "I am Sam. Sam I am." it would
know that "am." or "am" could come after "I", and after "Sam" must come the
word "I". 

1. **How did you find the translation process?**
    
    I thought it was a lot of fun being able to learn Kotlin through a mini
    project that I got to choose the difficulty of. Most of my errors had to 
    do with type inferences, since I was less used to thinking about types in
    Python. One particularly confusing bug came from Kotlin categorizing a
    single character string from .last() as a Char instead, which was not 
    a distinction I was previously familiar with. That being said, it was 
    great being able to put Kotlin's more unique features into action.


### Kotlin Tour Questions
1. **What features do you like about Kotlin?** 

    Kotlin is super concise and has some great features like lambda statements,
  do-while loops, and the Elvis operator. I especially enjoy that you can feed
  a function into another function. Writing a .filter {} or .map {} line is are
  very satisfying.


3. **Are there things you were expecting to find that you haven’t?**

    I was surprised there wasn't a direct translation of the instanceof()
    function from Python, especially since Kotlin requires you to be a lot
    more explicit with your types.


4. **What questions do you have?**
    
    What is Kotlin most commonly used for in the real world? How has its
    compatibility with Java hindered and helped it succeed as a language?
