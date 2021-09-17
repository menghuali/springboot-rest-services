# Social Media Application
## User API
API | Method | Path
--- | --- | ---
Retrieve all users | GET | /users
Create a user | POST  | /users
Retreive a user | GET | /users/{id}
Delete a user | DELETE | /users/{id}

## Post API
API | Method | Path
--- | --- | ---
Retrieve all posts of a user | GET | /users/{id}/posts
Create a post for a user | POST | /users/{id}/posts
Delete a post for a user | DELETE | /users/{id}/posts/{post_id}

