docker build -f docker/Dockerfile -t trendyol-selenium:latest .
docker run --rm -it --volume $(pwd)/allure-results:/usr/src/allure-results trendyol-selenium:latest mvn test -DsuiteXmlFile=UItestng.xml

allure serve ./allure-results
