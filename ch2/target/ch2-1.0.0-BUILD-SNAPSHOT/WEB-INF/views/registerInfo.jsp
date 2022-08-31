<%--
  Created by IntelliJ IDEA.
  User: kwah
  Date: 2022/08/30
  Time: 11:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>registerInfo</title>
</head>
<body>
<h1>id=${param.id}</h1>
<h1>pwd=${param.pwd}</h1>
<h1>name=${param.name}</h1>
<h1>email=${param.email}</h1>
<h1>birth=${param.birth}</h1>
<%--
input 이름이 sns로 전부 같기 때문에, 모든 sns를 체크 해도 하나만 나온다.
여러개일 경우에는 request.getParmeterValues("sns")를 사용한다.

EL로 쓰는 방법은 ${paramValues}를 사용한다.
  --%>
<%--<h1>sns=${param.sns}</h1>--%>
<h1>sns=${paramValues.sns[0]}</h1> <%-- facebook --%>
<h1>sns=${paramValues.sns[1]}</h1> <%-- kakaotalk --%>
<h1>sns=${paramValues.sns[2]}</h1> <%-- instagram --%>
</body>
</html>
