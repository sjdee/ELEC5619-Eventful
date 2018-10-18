# Master2.0

I’ve done some work into getting posts/comments working which includes getting a basic event view working. The following are a few steps into getting the database working as well as creating some dummy data to see how event pages are supposed to work:

## Getting Started

1. Install MYSQL server as per tut notes  
2. Initialise database with username ‘root’ and password ‘rootroot’ (this saves us having to change and update the project config for everyone). NOTE: Make sure you use the legacy password option if you want to use the tool in the next part.
3. I’d recommend getting this tool https://sequelpro.com/builds/Sequel-Pro-Build-3477d22387.zip to visualise the database. To connect, go to the ‘Socket’ tab and enter your server username and password (‘root’ and ‘rootroot’). 
4. Either using this tool or command line, create a database called ‘springapp’
5. Back to the spring project, run it. We’re going to initialise some dummy data so visit ‘localhost:8080/eventful/createFake/fake’ which will create a dummy event. NOTE: This redirects us to the create post page, just ignore this.
6. You can see these changes to the database in Sequel Pro. We should have created an event with index 1. Visit ‘http://localhost:8080/eventful/event/1' to look at it. Here you can create posts and comments. 
