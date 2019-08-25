[![CircleCI](https://circleci.com/gh/marijansikic1992/Path-Following-Algorithm.svg?style=svg&circle-token=ac1dce423c2dd8e5c1536d790aed4166e811e8e0)](https://circleci.com/gh/marijansikic1992/Path-Following-Algorithm)

# Path Following Algorithm #

Path following algorithm works as follows:

 - Takes the ASCII map as a String
 - Transforms it into 2d character array
 - Finds the @ which serves as a starting point for the path traversal
 - Follows the path and caches the visited fields
 - Saves all the visited fields and only contains the Set of letters
 - Prints the path traversed and only unused letters

# EXAMPLE 1 #

```
 @---A---+ 
         |
 x-B-+   C
     |   |
     +---+
```
Path : @---A---+|C|+---+|+-B-x

Letters : ACB

# EXAMPLE 2 #

```
@
| C----+
A |    |
+---B--+
  |      x
  |      |
  +---D--+
```
Path : @|A+---B--+|+----C|-||+---D--+|x

Letters : ABCD

# EXAMPLE 3 #
```
  @---+
      B  
K-----|--A 
|     |  |
|  +--E  | 
|  |     | 
+--E--Ex C 
   |     | 
   +--F--+ 
```

Path : @---+B||E--+|E|+--F--+|C|||A--|-----K|||+--E--Ex

Letters : BEEFCAKE

# BONUS EXAMPLE #
```
    @----+               
         |               
     E---H               
     |                   
     |   x               
     L   D    +---------+
     |   |    |         |
     +--------L         |
         |          +---O
       +-+          W    
       |            O    
       |            R    
       +------------L    
```

Path : @----+|H---E||L|+--------L|+---------+||O---+WORL------------+||+-+|-|Dx

Letters : HELLOWORLD


## Architecture ##

The app is written with clean architecture in mind (Model-View-ViewModel) and Kotlin as a programming language of choice. Repository provides the data for viewModel calculations, which in return signals the view using liveData. Unit tests are covered 100% in ViewModel and written using Junit. UI tests are written using Espresso.

The code is structured in packages by feature. There are 3 root packages
* data (contains repository which provides data)
* shared (shared classes in the app)
* ui (UI features)
