## Description

Now we need to make the second part of our business logic, which is the schedule service. We need to do two main things:

1. Make a way to create lessons.
2. Get information about student groups from the student service.

Thus, students will be able to see which lessons and which groups are present in the schedule.

## Objectives

Implement the REST API with such endpoints:

1. `POST /lessons` - create a new lesson. The action can only be performed by the curator.
    - Request body:
    ```json
    {
        "name": "str",
        "groupIds": ["int"]
    }
    ```
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

2. `GET /lessons/:id` - get detailed information about a lesson
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

## Examples

<table>
	<tbody>
		<tr>
			<td>Endpoint</td>
			<td>Request body</td>
			<td>Response body</td>
		</tr>
		<tr>
			<td>POST 127.0.0.1:8001/lessons</td>
			<td>
				<pre>
<code class="language-json">{
    "name": "Lesson",&nbsp;
    "groupIds": [1, 2]
}</code>
				</pre>
			</td>
			<td>
				<pre>
<code class="language-json">{
    "id": 1,&nbsp;
    "name": "Lesson",&nbsp;
    "groups": [&nbsp;
        {&nbsp;
            "id": 1,&nbsp;
            "name": "id-qui-voluptates"&nbsp;
        },&nbsp;
        {&nbsp;
            "id": 2,&nbsp;
            "name": "repudiandae-neque-est"&nbsp;
        }&nbsp;
    ]&nbsp;
}</code>
				</pre>
			</td>
		</tr>
		<tr>
			<td>GET 127.0.0.1:8001/lessons/1</td>
			<td>&nbsp;</td>
			<td>
				<pre>
<code class="language-json">{
    "id": 1,&nbsp;
    "name": "Lesson",&nbsp;
    "groups": [&nbsp;
        {&nbsp;
            "id": 1,&nbsp;
            "name": "id-qui-voluptates"&nbsp;
        },&nbsp;
        {&nbsp;
            "id": 2,&nbsp;
            "name": "repudiandae-neque-est"&nbsp;
        }&nbsp;
    ]&nbsp;
}</code>
				</pre>
			</td>
		</tr>
	</tbody>
</table>
