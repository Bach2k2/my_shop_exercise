<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: letro
  Date: 12/1/2022
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update product</title>
</head>
<body>
<h1>Update</h1>
<form action="/home" method="post" enctype="multipart/form-data">
    <input type="hidden" name="action" value="update">
    <table>
        <tr>
            <th>ID</th>
            <td><input type="text" name="pId" value="${pId}" readonly/></td>
            <td></td>
        </tr>
        <tr>
            <th>Product name</th>
            <td><input type="text" name="name" value="${name}"/></td>
            <td></td>
        </tr>
        <tr>
            <th>Price</th>
            <td><input type="text" name="price" value="${price}"/></td>
            <td></td>
        </tr>
<%--        <tr>--%>
<%--            <th>Change image </th>--%>
<%--            <td><input type="file" name="image"/></td>--%>
<%--            <td><img src="${image}" alt="image canoot show"></td>--%>
<%--        </tr>--%>
        <tr>
            <th>Category</th>
            <td><select name="cId">
                <c:forEach items="${cList}" var="c">
                    <c:choose>
                        <c:when test="${c.cId}.equals(${thisId})">
                            <option value="${c.cId}" selected>${c.cName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${c.cId}">${c.cName}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select></td>
            <td></td>
        </tr>
        <tr>
            <th>Description</th>
            <td><textarea row="4" name="description" ><c:out value="${description}"></c:out></textarea></td>
            <td></td>
        </tr>
        <tr>
            <td><input type="submit" value="Update product"></td>
        </tr>
    </table>
</form>

</body>
</html>
