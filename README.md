# ContactsComposeMultiplatform

This project is inspired by [@EranBoudjnah ](https://github.com/EranBoudjnah)'s book, [Clean Architecture for Android](https://amzn.to/43cUuhb) and readaptation of [ContactsComposeMultiplatform](https://github.com/philipplackner/ContactsComposeMultiplatform) by [philipplackner](https://github.com/philipplackner). It is a Kotlin Multi platform project written almost entirely in Kotlin.

It demonstrates the key principles presented in the book and how they apply to a real life project.
I will endeavour to keep this project up to date and use it to demonstrate the strengths of the
architecture: scalability, testability and flexibility when it comes to choosing 3rd party
solutions.

**[Get Started Here>>>](https://github.com/teewhydope/ContactsComposeMultiplatform/tree/main/common/src)**

### Features

- [Feature separation](https://github.com/teewhydope/ContactsComposeMultiplatform/tree/main/common/src/commonMain/kotlin/com/teewhydope)
- [Layer separation](https://github.com/teewhydope/ContactsComposeMultiplatform/tree/main/common/src/commonMain/kotlin/com/teewhydope/contact)
    - UI
    - Presentation
    - Domain
    - Data
    - Data Source
- [Unit tests](https://github.com/teewhydope/ContactsComposeMultiplatform/tree/main/common/src/jvmTest/kotlin/com/teewhydope)
- End-to-end tests
- Demonstrates use of [Jetbrain's Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- Demonstrates use of [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
  including [Flow](https://kotlinlang.org/docs/flow.html)
- Demonstrates <abbr title="Model View ViewModel">MVVM</abbr>
- Code quality checks using [ktlint](https://github.com/pinterest/ktlint)
- [Manual DI](https://github.com/teewhydope/ContactsComposeMultiplatform/tree/main/common/src/commonMain/kotlin/com/teewhydope/app/di)
- Database [SqlDelight](https://github.com/cashapp/sqldelight)
- KMP Compose MultiPlatform (Android/Ios)
- [Shared Viewmodel](https://github.com/teewhydope/ContactsComposeMultiplatform/tree/main/common/src/commonMain/kotlin/com/teewhydope/architecture/presentation/viewmodel) e.t.c

### Choices

- **Mappers as Classes** vs. **Mapping Extension Functions**
  When mapping between models, we have several options. The primary decision is between mapper
  classes and mapping extension functions.

  While extension functions are more concise, using them for mapping limits our choices of testing
  frameworks ([Mockito](https://site.mockito.org/), for example, cannot stub static functions).

  How about injecting the mapper extension functions? We could do that. However, this removes the
  benefits of conciseness almost entirely. It also makes navigation to the implementation harder.

  And so, I opted for the slightly more verbose concrete mapper classes.

- **Mocking Framework**
  I used [Mockito-Kotlin](https://github.com/mockito/mockito-kotlin). I find the code easier to read and follow when
  using it. 

- **Dependency Injection Framework**
  Decided to opt-in for Manual Dependency Injection because hilt only supports android for now and i found Koin a little bit complicated to setup and also requires many lines of code than necessary. Refer to ([covpass-android](https://github.com/Digitaler-Impfnachweis/covpass-android)) for a real world complete app using Manual DI

- **Navigation**
  I used [Precompose](https://github.com/Tlaster/PreCompose). I find it lightweight and easy to use. It is also very similar to jetpack navigation, and it's multiplatform.. 

### Links

[Clean Architecture for Android on Amazon](https://amzn.to/43cUuhb "Clean Architecture for Android")

[Clean Architecture on the Clean Coder Blog](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html "Clean Architecture")

[covpass-android](https://github.com/Digitaler-Impfnachweis/covpass-android)

[ContactsComposeMultiplatform](https://github.com/philipplackner/ContactsComposeMultiplatform)




