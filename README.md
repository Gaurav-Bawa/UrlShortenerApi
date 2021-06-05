# UrlShortenerApi
Below is the tech stack used in URL Shortener
- Spring Boot
- AWS DynamoDB

List of endpoints - 
- /create - Post request to create the list of short urls
  - Sample Post Request - 
    {
      "url" : "https://github.com/Gaurav-Bawa/",
      "numberOfUrl" : 10
    }

- /{shortUrl} - Get request to redirect to the actual long url
