## Description

After we've planned how our project will be set up, we need to work on the part that helps students. We're going to
focus on three main areas:

1. Let students sign up easily.
2. Provide a way for them to fetch their personal information.
3. Making it possible for them to join various groups.

This way, students can easily become part of the system, share who they are, and join different groups that interest
them.

## Objectives

Implement the REST API with such endpoints:

1. `POST /students` - create a new student
    - Request body:
      ```json
      {
          "name": "str",
          "email": "str"
      }
      ```
    - Response body:
      ```json
      {
          "id": "int",
          "name": "str",
          "email": "str"
      }
      ```

2. `GET /students` - list all students
    - Response body:
      ```json
      [
          {
              "id": "int",
              "name": "str",
              "email": "str"
          }
      ]
      ```

3. `GET /students/:id` - get detailed info about a student
    - Response body:
      ```json
      {
          "id": "int",
          "name": "str",
          "groups": [
              {
                  "id": "int",
                  "name": "str"
              }
          ]
      }
      ```

4. `POST /groups` - create a new group
    - Request body:
      ```json
      {
          "name": "str"
      }
      ```
    - Response body:
      ```json
      {
          "id": "int",
          "name": "str"
      }
      ```

5. `GET /groups` - list all groups
    - Response body:
      ```json
      [
          {
              "id": "int",
              "name": "str"
          }
      ]
      ```

6. `POST /groups/:id/join` - add a student to the group members
    - Request body:
      ```json
      {
          "studentId": "int"
      }
      ```
    - Response body:
      ```json
      {
          "joined": true
      }
      ```

## Examples

<table>
  <thead>
    <tr>
      <th>Endpoint</th>
      <th>Request body</th>
      <th>Response body</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>POST 127.0.0.1:8000/students</code></td>
      <td><pre><code class="language-json">{
  "name": "John Doe",
  "email": "john@mail.com"
}</code></pre></td>
      <td><pre><code class="language-json">{
  "id": 1,
  "name": "John Doe",
  "email": "john@mail.com"
}</code></pre></td>
    </tr>
    <tr>
      <td><code>GET 127.0.0.1:8000/students</code></td>
      <td> <br></td>
      <td><pre><code class="language-json">[
  {
    "id": 0,
    "name": "Onie",
    "email": "quia@mail.com"
  },
  {
    "id": 1,
    "name": "John Doe",
    "email": "john@mail.com"
  },
  {
    "id": 2,
    "name": "Bernice",
    "email": "illo@mail.com"
  }
]</code></pre></td>
    </tr>
    <tr>
      <td><code>GET 127.0.0.1:8000/students/1</code></td>
      <td> <br></td>
      <td><pre><code class="language-json">{
  "id": 1,
  "name": "John Doe",
  "email": "john@mail.com",
  "groups": [
    {
      "id": 1,
      "name": "id-qui-voluptates"
    }
  ]
}</code></pre></td>
    </tr>
    <tr>
      <td><code>POST 127.0.0.1:8000/groups</code></td>
      <td><pre><code class="language-json">{
  "name": "id-qui-voluptates"
}</code></pre></td>
      <td><pre><code class="language-json">{
  "id": 1,
  "name": "id-qui-voluptates"
}</code></pre></td>
    </tr>
    <tr>
      <td><code>GET 127.0.0.1:8000/groups</code></td>
      <td> <br></td>
      <td><pre><code class="language-json">[
  {
    "id": 0,
    "name": "eius-et-alias"
  },
  {
    "id": 1,
    "name": "id-qui-voluptates"
  }
]</code></pre></td>
    </tr>
    <tr>
      <td><code>POST 127.0.0.1:8000/groups/1/join</code></td>
      <td><pre><code class="language-json">{
  "studentId": 1
}</code></pre></td>
      <td><pre><code class="language-json">{
  "joined": true
}</code></pre></td>
    </tr>
  </tbody>
</table>
