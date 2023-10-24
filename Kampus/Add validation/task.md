## Description

After we have implemented the services, we need to configure user input validation. To do this, we need to validate the
following parameters:

1. Path parameters for endpoints
2. Request body sent by the user
3. Requests to non-existent resources

## Objectives

Add the following checks to the project:

1. The name of the user, group, and lesson cannot be empty.
2. The user's email cannot be empty.
3. If an incorrect data type is passed as a path parameter or in the request body, the service should return the
   status `400 Bad Request` with a message indicating an error. You can describe the message at your want.
4. If a user requests a resource that does not exist, the service should return the `404 Not Found` status with a
   message indicating an error. You can describe the message at your want.

## Examples

<table>
    <tr>
        <th>Endpoint</th>
        <th>Request body</th>
        <th>Response body</th>
    </tr>
    <tr>
        <td><code>POST 127.0.0.1:8000/students</code></td>
        <td>
            <pre>
<code class="language-json">{
     "name": "",
     "email": "john@mail.com"
}</code></pre>
        </td>
        <td>
            <code>400 Bad Request</code><br>
            <pre>
<code class="language-json">{
     "message": "Student name cannot be empty"
}</code></pre>
        </td>
    </tr>
    <tr>
        <td><code>GET 127.0.0.1:8000/students/999</code></td>
        <td></td>
        <td>
            <code>404 Not Found</code><br>
            <pre>
<code class="language-json">{
     "message": "Resource not found"
}</code></pre>
        </td>
    </tr>
</table>
