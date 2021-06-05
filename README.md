# UrlShortenerApi
URL Shortening (For e.g. bitly, tinyurl etc.) - A simple process to convert your long URL to small URL to accommodate the same in SMS, Emails etc. In some ways, it helps to reduce the cost by reducing the number of characters in marketing SMS campaigns. You can keep the count of number of hits to the URL as well. Here is a solution design on
- How to create a list of short URLs
- Redirect short URL to actual long URL
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
