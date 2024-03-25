## Convert File Service

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)
[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

<a href="https://imagemagick.org/index.php">
  <img src="https://imagemagick.org/image/wizard.png" align="left" height="60" width="60" >
</a>

<br/>
<br/>

### PT-BR
Projeto criado com o intuito de por em prática o conteúdo obtido no estudo de java. </br>
Esse projeto é uma API criada utilizando <b>Java</b>, <b>Java Spring</b>, <b>MongoDB</b> e <b>ImageMagick</b>.

ps: O uso da aplicação CLI ImageMagick é para a conversão de arquivos propriamente, sendo assim, a aplicação funciona como um gateway entre o usuario e a aplicação CLI.

### EN-US
Project created with the purpose of putting into practice the content obtained in the study of Java.</br>
This project is an API created using <b>Java</b>, <b>Java Spring</b>, <b>MongoDB</b>, and <b>ImageMagick</b>.

PS: The use of the ImageMagick CLI application is for file conversion purposes specifically; So, the application functions as a gateway between the user and the CLI application.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)

## Installation

1. Clone the repository:

```bash
git@github.com:PedroH183/SwiftConvert.git
```

2. Install dependencies with Maven

## Usage

1. Start the application with Maven 
2. The API will be accessible at http://localhost:8080

## API Endpoints
The API provides the following endpoints:

**Convert**
```markdown
PUT /api/convert/<ID_FILE> - Convert a file to other type.
```

**BODY**
```json
{
    "mimeType" : "text/plain"
}
```
<hr/>

**Get File Converted**
```markdown
GET /api/arquivo/convertedFile/<ID_FILE> - GET A FILE CONVERTED BY MIMETYPE
```

**BODY**
```json
{
    "mimeType" : "application/pdf"
}
```

<hr/>

**Get All Products**
```markdown
GET /api/arquivo - GET ALL FILES SENT TO API 
```

**GET ONE FILE**
```markdown
GET /api/arquivo/view/<ID_FILE> - GET VIEW OF A FILE SENT TO API
```

## Database

This project use mongodb to save reference of file paths, so create a database in your mongodb called `SwiftConverterDb` and enjoy !


**Tabel Arquivo**
```markdown
public class Arquivo {

    @Id
    public String id;
    public String filename;
    public String mimeType;
    public String location;
    public List<Arquivo> conversoes;

    public Arquivo(String filename, String location, String ty, List<Arquivo> arquivos){
        this.mimeType = ty;
        this.filename = filename;
        this.location = location;
        this.conversoes = arquivos;
    }
(...)
}
```

