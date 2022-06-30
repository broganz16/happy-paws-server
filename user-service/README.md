# Happy Paws UI (<i>Frontend</i>)


## Requirements
* [Node.js version >= 16.2.0](https://nodejs.org/en/)
* [Visual Studio](https://www.jetbrains.com/webstorm/) or similar
* [Docker/Docker Desktop](https://www.docker.com/)

## Installation
* Download or clone the following repository
* Using WebStorm Javascript IDE (or similar IDEA),  open or import the project. 
 <br>For Webstorm IDE user ->  select File > Open > […/path/of/ happy-paws-frontend].
* Using the incorporated terminal run ```npm install``` to download and install dependencies. If error persists run:
 ```
npm install primevue@^3.12.5 --save
npm install primeicons –save
```


## Docker  
### Docker build 
```
docker build -t happypaws .
```


### Docker run 
```
docker run -it -p 8080:80 --rm --name happypaws happypaws
```


### Docker stop 
```
docker stop $(docker ps -a -q)
docker rm -vf $(docker ps -a -q)
docker rmi -f $(docker images -a -q) 
```

 
