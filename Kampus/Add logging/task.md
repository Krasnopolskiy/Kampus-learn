## Description

In addition to the business logic of the service, we need to implement a system to monitor the state of the system. To
do this, we implement logging. To make the system more transparent, we will also log the request id, which we will
receive from the header.

## Objectives

Implement logging in the student service and schedule service to a file in the format:

```text
%d{YYYY-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
```

Http request logs must contain the request id, which is passed in the `Request-Id` header, the request path and the user
ip in format:

```text
Request [%requestId%] from [%userIp%] to the path: [%endpoint%]
```

Also implement an endpoint in each service to get the latest logs as is: `GET /logs`

## Examples

`GET 127.0.0.1:8000/logs`

```text
2023-10-22 12:00:00.000 [eventLoopGroupProxy-4-1] INFO Application - Request [4495eafa-9e43-40b9-ba92-131e1777a53f] from [127.0.0.1] to the path: [/students]
2023-10-22 12:00:01.000 [eventLoopGroupProxy-4-1] INFO Application - Request [4495eafa-9e43-40b9-ba92-131e1777a53f] from [127.0.0.1] to the path: [/students/1]
```
