#### kotlinUsers
Android MVVM with Dagger 2, Retrofit, RxJava,, Architecture Components

When opening the app, show a list of all Kotlin developers.

* Use the GitHub API to load the users
  * You can either use the [v3](https://developer.github.com/v3/search/#search-users) or [v4](https://developer.github.com/v4/object/user/) version of their API
* Load maximum 10 users at once
* Only show Kotlin users
* Each displayed user item should contain at least:
  * username
  * avatar
  * registration date
  * order by username

#### User Detail

When clicking on a user item, show a detail screen.

* When clicking on the email address, the default email app, installed on the device should open up
