# Master3.0

I’ve done some work into getting posts/comments working which includes getting a basic event view working. The following are a few steps into getting the database working as well as creating some dummy data to see how event pages are supposed to work:

## Getting Started

1. Install MYSQL server as per tut notes  
2. Initialise database with username ‘root’ and password ‘rootroot’ (this saves us having to change and update the project config for everyone). NOTE: Make sure you use the legacy password option if you want to use the tool in the next part.
3. I’d recommend getting this tool https://sequelpro.com/builds/Sequel-Pro-Build-3477d22387.zip to visualise the database. To connect, go to the ‘Socket’ tab and enter your server username and password (‘root’ and ‘rootroot’). 
4. Either using this tool or command line, create a database called ‘springapp’
5. Back to the spring project, run it. We’re going to initialise some dummy data so visit ‘localhost:8080/createFake/fake’ which will create a dummy event. NOTE: This redirects us to the create post page, just ignore this.
6. You can see these changes to the database in Sequel Pro. We should have created an event with index 1. Visit ‘http://localhost:8080/event/1' to look at it. Here you can create posts and comments. 

## New Changes for Master3.0
The majority of the code are the same, for example controller, services, domain, jsp all works the same. However, there are some minor changes to make this work:
1. The columns and attributes in your domains a.k.a. entities cannot have uppercase letters, for example if your column is called numLikes, it has to be (or was, if it was already committed) changed to numlikes (this is the easiest fix for this problem)
2. For Dao, instead of "SessionFactory", now we have "EntityManager", they essentially do the same thing with different method names, the current upload has made the changes to all the old commits accordingly, so you can refer to that as blueprint.
3. File structure are different, but it's overall the same, you will find the java mvc code in src/main/java/, jsp in src/main/webapp/WEB-INF/views/, and static files such as css and javascript in src/main/resources/static.
4. All the url now removed '/eventful/' so it will be 'localhost:8080/{whateveryoururlonthecontrolleris'
### New Set Up
If you have installed the MySQL server as Joseph metioned above, then just open the project on STS and that's it. 
### Running the server
Two options (same thing)
1. Press the green run button on the top bar
2. Right click on the project, select run as, select spring boot app

Note: Any problems, you know where to find me