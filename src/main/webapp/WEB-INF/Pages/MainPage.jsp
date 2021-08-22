<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Main</title>
</head>
<body>
<div class="bg-gradient-to-r from-yellow-400 via-red-500 to-pink-500 ...">
    <div class="relative py-3 sm:max-w-xl sm:mx-auto">
        <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-light-blue-500 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl"></div>
        <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
            <div class="max-w-md mx-auto">
                <form action="${pageContext.request.contextPath}/lots/editLot" method="get">
                    <div class="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                        <p><h1><b>Welcome!</p> <p>Your lots are displayed here</p></b></h1>
                        <table class="border-separate border border-green-800 ...">
                            <thead>
                            <tr>
                                <th class="border border-green-600 ...">Lot name</th>
                                <th class="border border-green-600 ...">Info</th>
                                <th class="border border-green-600 ...">Number of bids</th>
                                <th class="border border-green-600 ...">Last offered price </th>
                                <th class="border border-green-600 ...">Is active</th>
                                <th class="border border-green-600 ...">Edit</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="lot" items="${ownersLots}" >
                                    <tr>
                                        <td class="border border-green-600 ..."><c:out value="${lot.getName()}"/></td>
                                        <td class="border border-green-600 ..."><c:out value="${lot.getDescription()}"/></td>
                                        <td class="border border-green-600 ..."><c:out value="${lot.getOffersQuantity()}"/></td>
                                        <td class="border border-green-600 ..."><c:out value="${lot.getStartPrice()} $"/></td>
                                        <td class="border border-green-600 ..."><c:out value="${lot.isActive()}"/></td>
                                        <td class="border border-green-600 ...">
                                            <input type="radio" id="myCheckbox" name="lotID" value="${lot.getId()}"/>
                                        </td>
                                    </tr>
                            </c:forEach>
                            </tbody>
                        </table><br/>
                        <button class = "bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white " type="submit">View details</button>
                    </div>
                </form>
                <form action="${pageContext.request.contextPath}/lots/viewAll" method="get">
                    <button class = "bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white " type="submit">View all lots</button>
                </form>
                <form action="${pageContext.request.contextPath}/lots/createLot" method="get">
                    <button class = "bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white " type="submit">Create a new lot</button>
                </form>
                <form action="${pageContext.request.contextPath}/lots/logOut" method="get">
                    <button class = "bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white " type="submit">Log out</button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>