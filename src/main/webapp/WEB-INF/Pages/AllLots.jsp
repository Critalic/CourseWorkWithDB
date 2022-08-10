<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../JS/EmptyFormValidator.js"></script>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Available lots</title>
</head>
<body>
<c:if test="${user==null}">
    <div class="bg-gradient-to-r from-yellow-400 via-red-500 to-pink-500 ...">
        <div class="relative py-3 sm:max-w-xl sm:mx-auto">
            <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-light-blue-500 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl"></div>
            <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
                <div class="max-w-md mx-auto">
                    <p class="text-2xl">Active lots</p>
                    <form class="bg-gray-100 flex justify-left">
                        <table class="border-separate border border-green-800 ...">
                            <thead>
                            <tr>
                                <th class="border border-green-600 ...">Lot name</th>
                                <th class="border border-green-600 ...">Info</th>
                                <th class="border border-green-600 ...">Owner</th>
                                <th class="border border-green-600 ...">Last offered price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="lot" items="${lots}">
                                <c:if test="${lot.getIsActive()}">
                                    <tr>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${lot.getName()}"/></td>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${lot.getDescription()}"/></td>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${lot.getCustomer().getName()}"/></td>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${lot.getStartPrice()} $"/></td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                    </form>
                    <form method="get" action="${pageContext.request.contextPath}/lots/searchLot">
                        <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                type="submit">Search lot
                        </button>
                    </form>
                    <a href="<c:url value="/index.jsp"/>" class="text-cyan-600 hover:text-cyan-700">
                        &larr; Back to main</a>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${user!=null}">

    <div class="bg-gradient-to-r from-yellow-400 via-red-500 to-pink-500 ...">
        <div class="relative py-3 sm:max-w-xl sm:mx-auto">
            <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-light-blue-500 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl"></div>
            <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
                <p class="text-xl">Hello, ${user.getName()}!</p><br>
                <p class="text-2xl"><b>Active lots</b></p>
                <form class="max-w-md mx-auto" method="get"
                      action="${pageContext.request.contextPath}/lots/viewLot">
                    <div class="bg-gray-100 flex justify-left">

                        <table class="border-separate border border-green-800 ...">
                            <thead>
                            <tr>
                                <th class="border border-green-600 ...">Lot name</th>
                                <th class="border border-green-600 ...">Info</th>
                                <th class="border border-green-600 ...">Owner</th>
                                <th class="border border-green-600 ...">Last offered price</th>
                                <th class="border border-green-600 ...">Select to view</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="lot" items="${lots}">
                                <c:if test="${lot.getIsActive()}">
                                    <tr>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${lot.getName()}"/></td>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${lot.getDescription()}"/></td>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${lot.getCustomer().getName()}"/></td>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${lot.getStartPrice()} $"/></td>
                                        <td class="border border-green-600 ...">
                                            <input required type="radio" id="myCheckbox" name="lotID"
                                                   value="${lot.getId()}"/>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                            </tbody>
                        </table>
                        <br/>
                        <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                type="submit">View
                        </button>
                    </div>
                </form>
                <form action="${pageContext.request.contextPath}/lots/mainPage" method="get">
                    <p>
                        <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                type="submit">Your profile
                        </button>
                    </p>
                </form>
                <form method="get" action="${pageContext.request.contextPath}/lots/searchLot">
                    <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                            type="submit">Search lot
                    </button>
                </form>
            </div>
        </div>
    </div>
</c:if>

</body>
</html>
