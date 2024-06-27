# Projeto de Testes de API com JsonPlaceholder

Este projeto contém testes de API para o serviço [JsonPlaceholder](https://jsonplaceholder.typicode.com/), utilizando Java, RestAssured, JUnit 5, ExtentReports para relatórios e SonarQube para análise de qualidade de código.

## Pré-requisitos

- Java 11+
- Maven 3.6+
- SonarQube 8.9+ (instalado e configurado localmente)
- IDE (recomendado: Visual Studio Code)

## Instalação

### 1. Clonar o Repositório

```sh
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio
```

### 2. Configurar o SonarQube

- Baixe e instale o SonarQube a partir do site oficial: [SonarQube Downloads](https://www.sonarqube.org/downloads/).
- Siga as instruções de instalação para configurar e iniciar o SonarQube.
- Acesse o painel do SonarQube em `http://localhost:9000` (usuário padrão: `admin`, senha padrão: `admin`).
- Crie um novo projeto no SonarQube e anote o `Project Key` e o `Token` gerado.

### 3. Configurar o Maven

Adicione as seguintes dependências no arquivo `pom.xml`:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.example.jsonplaceholder</groupId>
    <artifactId>JsonPlaceholderTests</artifactId>
    <version>1.0-SNAPSHOT</version>
    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
    </properties>
    <dependencies>
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>4.4.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.7.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.7.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>com.aventstack</groupId>
            <artifactId>extentreports</artifactId>
            <version>5.0.9</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.12.1</version>
        </dependency>
    </dependencies>
    <build>
        <plugins>
            <plugin>
                <groupId>org.sonarsource.scanner.maven</groupId>
                <artifactId>sonar-maven-plugin</artifactId>
                <version>3.9.1.2184</version>
            </plugin>
        </plugins>
    </build>
</project>
```

Adicione as configurações do SonarQube no arquivo `settings.xml` do Maven:

```xml
<settings>
    <profiles>
        <profile>
            <id>sonar</id>
            <properties>
                <sonar.host.url>http://localhost:9000</sonar.host.url>
                <sonar.login>your_sonar_token</sonar.login>
            </properties>
        </profile>
    </profiles>
    <activeProfiles>
        <activeProfile>sonar</activeProfile>
    </activeProfiles>
</settings>
```

### 4. Executar os Testes e Análise do SonarQube

Para rodar os testes e a análise de qualidade do SonarQube, execute os seguintes comandos no terminal:

```sh
mvn clean verify sonar:sonar -Dsonar.projectKey=your_project_key -Dsonar.login=your_sonar_token
```

## Descrição dos Testes

Os testes estão localizados no arquivo `src/test/java/com/example/jsonplaceholder/JsonPlaceholderTests.java` e cobrem os seguintes cenários:

### Testes GET

- `testGetPostPositive`: Verifica se é possível obter um post com sucesso.
- `testGetPostNegative`: Verifica se a tentativa de obter um post inexistente retorna um status 404.

### Testes POST

- `testPostCreatePositive`: Verifica se é possível criar um post com sucesso.
- `testPostCreateNegative`: Verifica se a tentativa de criar um post com dados inválidos retorna um status 500.

### Testes PUT

- `testPutUpdatePositive`: Verifica se é possível atualizar um post com sucesso.
- `testPutUpdateNegative`: Verifica se a tentativa de atualizar um post com dados inválidos retorna um status 404.

### Testes DELETE

- `testDeletePostPositive`: Verifica se é possível deletar um post com sucesso.
- `testDeletePostNegative`: Verifica se a tentativa de deletar um post inexistente retorna um status 404.

## Relatório de Testes

Um relatório de testes em formato HTML é gerado após a execução dos testes. O arquivo `extent.html` será criado na raiz do projeto. Abra este arquivo em um navegador para visualizar o relatório detalhado dos testes.

## Boas Práticas de Qualidade

As boas práticas de qualidade são integradas ao relatório de testes utilizando ExtentReports e são validadas pelo SonarQube. Métricas como cobertura de testes, complexidade do código, duplicação de código e violação de regras são monitoradas para garantir a alta qualidade do código.

Para acessar essas métricas, acesse o painel do SonarQube e navegue até o seu projeto.
