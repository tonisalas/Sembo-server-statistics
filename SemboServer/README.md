Server statistics application to get the hotels from Sembo.

Application port: 8080

Has been used a Springboot application.

Create server application to serve the statistics hotels by country from Sembo given a list of hotels of each country (es, it, fr).  

The problem with the low availability of the API has been solved retrying the request until it works. An ideas was to create a memory cache with the results won worked properly, but the data wont be always updated. So i leaved with retrys to the api.  