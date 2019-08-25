
# Path Following Algorithm #

Path following algorithm works as follows:

 - Takes the ASCII map as a String
 - Transforms it into 2d character array
 - Finds the @ which serves as a starting point for the path traversal
 - Follows the path and caches the visited fields
 - Saves all the visited fields and only contains the Set of letters


## Architecture ##

The app is written with clean architecture in mind (Model-View-ViewModel) and Kotlin as a programming language of choice. Repository provides the data for viewModel calculations, which in return signals the view using liveData. Unit tests are covered 100% in ViewModel and UI tests are written also.
Extensions are used 

Test are written using Junit and Mockito. 

The code is structured in packages by feature. There are 3 root packages
* data (contains repository which provides data)
* shared (shared classes in the app)
* ui (UI features)