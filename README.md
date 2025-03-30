# Decola Tech 2025

Java RESTful API criada para o Decola Tech 2025.

## Principais Tecnologias
 - **Java 17**
 - **Spring Boot 3**
 - **Spring Data JPA**
 - **OpenAPI (Swagger)**
 - **Railway**

## Diagrama de Classes (Domínio da API)

```mermaid
classDiagram
  direction TB

  class User {
    -Long id
    -String name
    -Account account
    -List~Feature~ features
    -Card card
    -List~News~ news
    +getId() Long
    +getName() String
    +getAccount() Account
    +getFeatures() List~Feature~
    +getCard() Card
    +getNews() List~News~
    +addFeature(Feature) void
    +removeFeature(Feature) void
    +addNews(News) void
    +removeNews(News) void
  }

  class Account {
    -Long id
    -String number
    -String agency
    -BigDecimal balance
    -BigDecimal limit
    +deposit(BigDecimal) void
    +withdraw(BigDecimal) void
    +getAvailableBalance() BigDecimal
  }

  class BaseItem {
    <<abstract>>
    -Long id
    -String icon
    -String description
    -Boolean active
    +getIcon() String
    +getDescription() String
    +isActive() Boolean
  }

  class Feature {
    -Integer priority
    -FeatureCategory category
    -String url
    -Boolean premium
    +getPriority() Integer
    +getCategory() FeatureCategory
    +getUrl() String
    +isPremium() Boolean
  }

  class Card {
    -Long id
    -String number
    -BigDecimal limit
    -CardType type
    -String expirationDate
    -String cvv
    -Boolean active
    +increaseLimit(BigDecimal) void
    +decreaseLimit(BigDecimal) void
    +isExpired() Boolean
  }

  class News {
    -String content
    -LocalDateTime publishDate
    -String author
    -String source
    -String imageUrl
    -NewsCategory category
    -Boolean featured
    +getSummary() String
    +isRecent() Boolean
  }

  User "1" *-- "1" Account
  User "1" *-- "1" Card
  User "1" *-- "0..*" Feature
  User "1" *-- "0..*" News
  Feature --|> BaseItem
  News --|> BaseItem

  class FeatureCategory {
    <<enum>>
    GENERAL
    PAYMENT
    INVESTMENT
    PROMOTION
    NEWS
    SUPPORT
  }

  class CardType {
    <<enum>>
    CREDIT
    DEBIT
    MULTIPLE
  }

  class NewsCategory {
    <<enum>>
    GENERAL
    FINANCIAL
    TECHNOLOGY
    PROMOTION
    SECURITY
    INVESTMENT
  }
```

# Referência Bibliográfica

Foi utilizada como referência para esse projeto os conteúdos da Digital Innovation One, principalmente o projeto da API RESTful da Santander Dev Week 2023, disponível no link abaixo:

https://github.com/falvojr/santander-dev-week-2023
